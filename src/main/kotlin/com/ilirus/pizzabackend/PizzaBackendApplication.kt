package com.ilirus.pizzabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PizzaBackendApplication {

}

fun main(args: Array<String>) {
    runApplication<PizzaBackendApplication>(*args)
}

// package as .war
// class PizzaBackendApplication : SpringBootServletInitializer() {
//    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
//        return builder.sources(PizzaBackendApplication::class.java)
//    }
//
//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            runApplication<PizzaBackendApplication>(*args)
//        }
//    }
//}