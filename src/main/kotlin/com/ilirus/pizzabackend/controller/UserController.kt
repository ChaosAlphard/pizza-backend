package com.ilirus.pizzabackend.controller

import com.ilirus.pizzabackend.dto.Dto
import com.ilirus.pizzabackend.dto.enums.StatusEnum
import com.ilirus.pizzabackend.model.User
import com.ilirus.pizzabackend.model.receive.RcUser
import com.ilirus.pizzabackend.service.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.*
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.validation.Valid

//@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
class UserController {
    @Resource
    private lateinit var ser: UserService

    private val log = LogFactory.getLog(UserController::class.java)

    @RequestMapping("/login")
    fun login(email: String?, password: String?, session: HttpSession): Dto<User> {
        email?.let { password?.let {
            val dto = try {
                ser.login(email, password)
            } catch (e: Exception) {
                log.error("Get Data Fail",e)
                Dto<User>(StatusEnum.GET_DATA_FAIL)
            }
            if(dto.code==200) {
                // 找不到用户信息说明登录失败
                dto.model?:return Dto(StatusEnum.NO_DATA_FOUND)

                session.setAttribute("id",dto.model!!.id)
                session.setAttribute("level",dto.model!!.level)
            }
            return dto
        } }
        return Dto(StatusEnum.LOST_PARAM)
    }

    @RequestMapping("/logout")
    fun logout(session: HttpSession) {
        session.invalidate()
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody user: RcUser?): Dto<RcUser> {
        user?:return Dto(StatusEnum.LOST_PARAM)

        return try {
            ser.register(user)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            Dto(StatusEnum.GET_DATA_FAIL)
        }
    }

    @GetMapping("/register/emailexist")
    fun isEmailExist(email: String?): Dto<User> {
        email?:return Dto(StatusEnum.LOST_PARAM)

        val flag = try {
            ser.isEmailExist(email)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            return Dto(StatusEnum.GET_DATA_FAIL)
        }

        return if(flag) {
            Dto(StatusEnum.EMAIL_ALREADY_EXIST)
        } else {
            Dto(StatusEnum.NO_DATA_FOUND)
        }
    }

    @GetMapping("/register/phoneexist")
    fun isPhoneExist(phone: String?): Dto<User> {
        phone?:return Dto(StatusEnum.LOST_PARAM)

        val flag = try {
            ser.isPhoneExist(phone)
        } catch (e: Exception) {
            log.error("Get Data Fail",e)
            return Dto(StatusEnum.GET_DATA_FAIL)
        }

        return if(flag) {
            Dto(StatusEnum.EMAIL_ALREADY_EXIST)
        } else {
            Dto(StatusEnum.NO_DATA_FOUND)
        }
    }
}