package com.ilirus.pizzabackend.dto.enums

enum class StatusEnum (
    var code: Int,
    var msg: String
) {
    // 通用
    GET_DATA_SUCCESS(200,"获取数据成功"),
    LOST_PARAM(400,"丢失参数"),
    WRONG_PARAM(400,"参数错误"),
    PERMISSION_DENIED(403,"权限不足"),
    NO_DATA_FOUND(404,"找不到对应的数据"),
    GET_DATA_FAIL(406,"获取数据失败"),
    // SERVER_ERROR(500,"服务器错误"),

    //Pizza
    ADD_PIZZA_SUCCESS(200,"新增Pizza成功"),
    ADD_PIZZA_FAIL(406,"新增Pizza失败"),
    UPDATE_PIZZA_SUCCESS(200,"更新Pizza成功"),
    UPDATE_PIZZA_FAIL(406,"更新Pizza失败"),
    DELETE_PIZZA_SUCCESS(200,"删除Pizza成功"),
    DELETE_PIZZA_FAIL(406,"删除Pizza失败"),

    // 登录注册
    LOGIN_SUCCESS(200,"登录成功"),
    LOGIN_FAIL(406,"账号密码不符"),
    REGISTER_SUCCESS(200,"注册成功"),
    REGISTER_FAIL(406,"注册失败"),
    EMAIL_ALREADY_EXIST(402,"邮箱已存在"),
    PHONE_ALREADY_EXIST(402,"手机号已存在")
}