package com.ilirus.pizzabackend.model.receive

import javax.validation.constraints.*

/**
 * 接收 新增Pizza 的model
 */
class RcPizza {
    @NotBlank
    @Size(min=1, max=28, message = "不超过28个字符")
    var name: String? = null

    @NotBlank
    @Size(min=1, max=1024, message = "不超过1024个字符")
    var pictureLink: String? = null

    @NotBlank
    @Size(min=1, max=1024, message = "不超过1024个字符")
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