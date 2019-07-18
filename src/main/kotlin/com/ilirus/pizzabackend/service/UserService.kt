package com.ilirus.pizzabackend.service

import com.ilirus.pizzabackend.dto.Dto
import com.ilirus.pizzabackend.model.User
import com.ilirus.pizzabackend.model.receive.RcUser

interface UserService {
    /**
     * 登录接口
     * @param email: 邮箱
     * @param password: 密码
     * @return Dto<User>
     */
    fun login(email: String, password: String): Dto<User>

    /**
     * 注册
     * @param user: 从前端发来的用户model (ReceiveUser)
     * @return Dto<RcUser> (实际上并不包含用户模型)
     */
    fun register(user: RcUser): Dto<RcUser>

    /**
     * 检查邮箱是否已存在
     * @param email: 邮箱
     * @return true/false
     */
    fun isEmailExist(email: String): Boolean

    /**
     * 检查手机号是否已存在
     * @param phone: 手机号
     * @return true/false
     */
    fun isPhoneExist(phone: String): Boolean
}