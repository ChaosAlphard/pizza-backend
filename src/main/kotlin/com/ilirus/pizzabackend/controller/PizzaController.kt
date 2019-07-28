package com.ilirus.pizzabackend.controller

import com.ilirus.pizzabackend.dto.Dto
import com.ilirus.pizzabackend.dto.enums.StatusEnum
import com.ilirus.pizzabackend.model.Pizza
import com.ilirus.pizzabackend.model.receive.RcPizza
import com.ilirus.pizzabackend.model.receive.UdPizza
import com.ilirus.pizzabackend.service.PizzaService
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.servlet.http.HttpSession
import javax.validation.Valid

@CrossOrigin(origins = ["http://erina.gitee.io", "http://localhost:8080"],
maxAge = 3600, allowCredentials = "true", methods = [RequestMethod.GET,
RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS])
@RestController
@RequestMapping("/pizza")
class PizzaController {
    @Resource
    private lateinit var ser: PizzaService

    private val log = LogFactory.getLog(PizzaController::class.java)

    private val limit: Int = 10

    @GetMapping("/findall")
    fun findAll(): Dto<Pizza> {
        return try {
            ser.findAllPizza()
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @GetMapping("/find")
    fun findPizza(page: String?): Dto<Pizza> {
        // val begin = if(page.isNullOrBlank()){
        //     0
        // } else {
        //     page!!.toInt()
        // }
        val begin = try {
            // page不为空执行toInt() 并赋值给begin 否则把null赋值给begin
            page?.toInt()
        } catch(e: Exception) {
            // toInt() 报异常说明参数无法转换为数字
            return Dto(StatusEnum.WRONG_PARAM)
        }
        return try {
            // begin不为空执行begin*limit 否则执行0*limit
            ser.findPizza((begin?:0)*limit, limit)
        } catch(e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @GetMapping("/findbyid")
    fun findPizzaByID(id: String?): Dto<Pizza> {
        id?:return Dto(StatusEnum.LOST_PARAM)

        val n = try {
            id.toInt()
        } catch (e: Exception) {
            return Dto(StatusEnum.WRONG_PARAM)
        }
        return try {
            ser.findPizzaByID(n)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @GetMapping("/findbyname")
    fun findByPizzaName(query: String?, page: String?): Dto<Pizza> {
        val begin = try {
            page?.toInt()
        } catch(e: Exception) {
            return Dto(StatusEnum.WRONG_PARAM)
        }
        return try {
            ser.findPizzaByName(query?:"", (begin?:0)*limit, limit)
        } catch(e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @PutMapping("/new")
    fun addPizza(@Valid @RequestBody pizza: RcPizza?, session: HttpSession): Dto<RcPizza> {
        if(session.getAttribute("level") != 2) {
            return Dto(StatusEnum.PERMISSION_DENIED)
        }
        pizza?:return Dto(StatusEnum.LOST_PARAM)

        return try {
            ser.addPizza(pizza)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @PostMapping("/edit")
    fun editPizza(@Valid @RequestBody pizza: UdPizza?, session: HttpSession): Dto<UdPizza> {
        if(session.getAttribute("level") != 2) {
            return Dto(StatusEnum.PERMISSION_DENIED)
        }
        pizza?:return Dto(StatusEnum.LOST_PARAM)

        return try {
            ser.updatePizza(pizza)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @DeleteMapping("/delete")
    fun deletePizza(id: String?, session: HttpSession): Dto<RcPizza> {
        if(session.getAttribute("level") != 2) {
            return Dto(StatusEnum.PERMISSION_DENIED)
        }
        id?:return Dto(StatusEnum.LOST_PARAM)

        val n = try {
            id.toInt()
        } catch (e: Exception) {
            return Dto(StatusEnum.WRONG_PARAM)
        }
        return try {
            ser.deletePizza(n)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
//not in use////////////////////////////////////////////////////////////////////
    @RequestMapping("/findbyprice")
    fun findByPizzaPrice(min: String?, max: String?, page: String?): Dto<Pizza> {
        min?.let { max?.let {
            val begin = try {
                page?.toInt()
            } catch(e: Exception) {
                return Dto(StatusEnum.WRONG_PARAM)
            }
            return try {
                ser.findPizzaByPrice(min, max , (begin?:0)*limit, limit)
            } catch (e: Exception) {
                log.error("Get Data Fail",e)
                Dto(StatusEnum.GET_DATA_FAIL)
            }
        } }
        return Dto(StatusEnum.LOST_PARAM)
    }
}