package com.ilirus.pizzabackend.model

data class User (
    var id: Int,
    var name: String,
    var email: String,
    var phone: String,
    var address: String,
    var level: Int

    // 返回给前端的model 不需要password
    // var password: String?
)