package com.ilirus.pizzabackend.service.impl

import com.ilirus.pizzabackend.dao.UserDao
import com.ilirus.pizzabackend.dto.Dto
import com.ilirus.pizzabackend.dto.enums.StatusEnum
import com.ilirus.pizzabackend.model.User
import com.ilirus.pizzabackend.model.receive.RcUser
import com.ilirus.pizzabackend.service.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException
import javax.annotation.Resource

@Service
class UserServiceImpl : UserService {
    @Resource
    private lateinit var dao: UserDao

    @Throws(Exception::class)
    override fun login(email: String, password: String): Dto<User> {
        val result = dao.login(email, password)
        return if(result.isPresent) {
            Dto(StatusEnum.LOGIN_SUCCESS, result.get())
        } else {
            Dto(StatusEnum.LOGIN_FAIL)
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    @Throws(Exception::class)
    override fun register(user: RcUser): Dto<RcUser> {
        val res = with(user) {
            when {
                isEmailExist(email!!) -> return Dto(StatusEnum.EMAIL_ALREADY_EXIST)
                isPhoneExist(phone!!) -> return Dto(StatusEnum.PHONE_ALREADY_EXIST)
                else -> dao.register(name!!,password!!,email!!,phone!!,address!!)
            }
        }
        return if(res != 0) {
            Dto(StatusEnum.REGISTER_SUCCESS)
        } else {
            throw SQLException("REGISTER_FAIL")
        }
    }

    @Throws(Exception::class)
    override fun isEmailExist(email: String) = dao.findEmail(email).isPresent

    @Throws(Exception::class)
    override fun isPhoneExist(phone: String) = dao.findPhone(phone).isPresent
}