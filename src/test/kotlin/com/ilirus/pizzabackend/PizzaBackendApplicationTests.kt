package com.ilirus.pizzabackend

import com.ilirus.pizzabackend.dao.PizzaDao
import com.ilirus.pizzabackend.dao.UserDao
import com.ilirus.pizzabackend.model.receive.RcPizza
import com.ilirus.pizzabackend.service.PizzaService
import com.ilirus.pizzabackend.service.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.annotation.Resource

@RunWith(SpringRunner::class)
@SpringBootTest
class PizzaBackendApplicationTests {
    @Resource
    private lateinit var pd: PizzaDao

    @Resource
    private lateinit var ud: UserDao

    @Test
    fun contextLoads() {
        val obj = object {
            val id = 2
            val name = "Ilirus"
        }
        val res = with(obj) {
            return@with when {
                id == 1 -> return@with 1
                name == "Ilirus" -> return@with 2
                else -> 0
            }
        }
        println(res)
    }

}
