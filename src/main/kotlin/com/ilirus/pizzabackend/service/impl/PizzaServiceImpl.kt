package com.ilirus.pizzabackend.service.impl

import com.ilirus.pizzabackend.dao.PizzaDao
import com.ilirus.pizzabackend.dto.Dto
import com.ilirus.pizzabackend.dto.enums.StatusEnum
import com.ilirus.pizzabackend.model.Pizza
import com.ilirus.pizzabackend.model.receive.RcPizza
import com.ilirus.pizzabackend.model.receive.UdPizza
import com.ilirus.pizzabackend.service.PizzaService
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.Resource

@Service
class PizzaServiceImpl : PizzaService {
    @Resource
    private lateinit var dao: PizzaDao

    @Throws(Exception::class)
    override fun findAllPizza(): Dto<Pizza> {
        return Dto(StatusEnum.GET_DATA_SUCCESS, dao.findAllPizza())
    }

    @Throws(Exception::class)
    override fun findPizza(begin: Int, limit: Int): Dto<Pizza> {
        return Dto(StatusEnum.GET_DATA_SUCCESS, dao.findPizza(begin, limit))
    }

    @Throws(Exception::class)
    override fun findPizzaByID(id: Int): Dto<Pizza> {
        val res = dao.findPizzaByID(id)
        return if(res.isPresent) {
            Dto(StatusEnum.GET_DATA_SUCCESS, res.get())
        } else {
            Dto(StatusEnum.NO_DATA_FOUND)
        }
    }

    @Throws(Exception::class)
    override fun isPizzaExist(id: Int): Boolean {
        val res = dao.isPizzaExist(id)
        return res.isPresent
    }

    @Throws(Exception::class)
    override fun findPizzaByName(query: String, begin: Int, limit: Int): Dto<Pizza> {
        return Dto(StatusEnum.GET_DATA_SUCCESS,
                    dao.findPizzaByName("%$query%", begin, limit))
    }

    @Throws(Exception::class)
    override fun findPizzaByPrice(min: String, max: String, begin: Int, limit: Int): Dto<Pizza> {
        return Dto(StatusEnum.GET_DATA_SUCCESS,
                    dao.findPizzaByPrice(min,max, begin, limit))
    }

    @Transactional(rollbackFor = [Exception::class])
    @Throws(Exception::class)
    override fun addPizza(pizza: RcPizza): Dto<RcPizza> {
        val res = with(pizza) {
            dao.addNewPizza(name!!,pictureLink!!, picturePreLink!!, priceSmall!!, priceBig!!, description!!)
        }
        return if(res != 0) {
            Dto(StatusEnum.ADD_PIZZA_SUCCESS)
        } else {
            Dto(StatusEnum.ADD_PIZZA_FAIL)
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    @Throws(Exception::class)
    override fun updatePizza(pizza: UdPizza): Dto<UdPizza> {
        val res = with(pizza) {
            dao.updatePizza(id!!, name!!,pictureLink!!, picturePreLink!!, priceSmall!!, priceBig!!, description!!)
        }
        return if(res != 0) {
            Dto(StatusEnum.UPDATE_PIZZA_SUCCESS)
        } else {
            Dto(StatusEnum.UPDATE_PIZZA_FAIL)
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    @Throws(Exception::class)
    override fun deletePizza(id: Int): Dto<RcPizza> {
        val res = if(isPizzaExist(id)) {
            dao.deletePizza(id)
        } else {
            return Dto(StatusEnum.NO_DATA_FOUND)
        }
        return if(res != 0) {
            Dto(StatusEnum.DELETE_PIZZA_SUCCESS)
        } else {
            Dto(StatusEnum.DELETE_PIZZA_FAIL)
        }
    }
}