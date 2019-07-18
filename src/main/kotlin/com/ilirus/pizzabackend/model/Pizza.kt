package com.ilirus.pizzabackend.model

data class Pizza (
    var id: Int,
    var name: String,
    var pictureLink: String,
    var picturePreLink: String,
    var priceSmall: Int,
    var priceBig: Int,
    var description: String?
)