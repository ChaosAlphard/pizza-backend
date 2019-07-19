package com.ilirus.pizzabackend.model.receive

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * 接收 更新Pizza 的model
 */
class UdPizza {
    @NotNull
    @Min(1, message = "不能小于1")
    var id: Int? = null

    @NotBlank
    @Size(min=1, max=28, message = "1-28个字符")
    var name: String? = null

    @NotBlank
    @Size(min=1, max=1024, message = "1-1024个字符")
    var pictureLink: String? = null

    @NotBlank
    @Size(min=1, max=1024, message = "1-1024个字符")
    var picturePreLink: String? = null

    @NotNull
    @Min(1, message = "不能小于1元")
    var priceSmall: Int? = null

    @NotNull
    @Min(1, message = "不能小于1元")
    var priceBig: Int? = null

    @Size(max=420, message = "不超过420个字符")
    var description: String? = null
}