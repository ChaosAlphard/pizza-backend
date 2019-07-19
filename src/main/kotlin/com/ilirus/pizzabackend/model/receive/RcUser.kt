package com.ilirus.pizzabackend.model.receive

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * 接收 新增User 的model
 */
class RcUser {
    @NotBlank
    @Size(min = 1, max = 24, message = "1-24个字符")
    var name: String? = null

    @NotBlank
    @Size(min = 32, max = 32, message = "请传入加密后的字符")
    var password: String? = null

    @NotBlank
    @Size(min = 3, max = 64, message = "3-64个字符")
    var email: String? = null

    @NotBlank
    @Size(min = 11, max = 11, message = "请传入11位手机字符")
    var phone: String? = null

    @NotBlank
    @Size(min = 1, max = 128, message = "1-128个字符")
    var address: String? = null
}

//(
//    @NotBlank
//    @Size(min=1, max=24, message = "不超过24个字符")
//    var name: String?,
//
//    @NotBlank
//    @Size(min=32, max=32, message = "请传入加密后的字符")
//    var password: String?,
//
//    @NotBlank
//    @Size(min=3, max=64, message = "不超过64个字符")
//    var email: String?,
//
//    @NotBlank
//    @Size(min=11, max=11, message = "请传入11位手机字符")
//    var phone: String?,
//
//    @NotBlank
//    @Size(min=1, max=128, message = "不超过128个字符")
//    var address: String?
//)
