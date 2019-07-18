package com.ilirus.pizzabackend.dto

import com.ilirus.pizzabackend.dto.enums.StatusEnum

/**
 * Data Transfer Object
 * @constructor
 * StatusEnum: 枚举, 包含code和msg
 *   code: 返回的结果代码
 *   msg: 返回的信息
 * model: 单个模型
 * data: 一个或多个模型的列表
 */
class Dto<T> {
    var code: Int
    var msg: String
    var model: T? = null
    var data: List<T>? = null

    constructor(se: StatusEnum) {
        this.code = se.code
        this.msg = se.msg
    }

    constructor(se: StatusEnum, model: T) {
        this.code = se.code
        this.msg = se.msg
        this.model = model
    }

    constructor(se: StatusEnum, data: List<T>) {
        this.code = se.code
        this.msg = se.msg
        this.data = data
    }

    fun setEnum(se: StatusEnum) {
        this.code = se.code
        this.msg = se.msg
    }
}
