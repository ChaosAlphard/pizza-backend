package com.ilirus.pizzabackend.dao

import com.ilirus.pizzabackend.model.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import java.util.*

@Mapper
interface UserDao {

    @Select("""select id, name, email, phone, address, level
            from user where email=#{email} and password=#{password} limit 1""" )
    fun login(@Param("email") email: String,
              @Param("password") password: String): Optional<User?>

    @Insert("""insert into user(name, password, email, phone, address, level)
            value(#{name}, #{password}, #{email}, #{phone}, #{address}, 1)""" )
    fun register(@Param("name") name: String,
                 @Param("password") password: String,
                 @Param("email") email: String,
                 @Param("phone") phone: String,
                 @Param("address") address: String): Int

    @Select("select email from user where email=#{email} limit 1")
    fun findEmail(@Param("email") email: String): Optional<String?>

    @Select("select phone from user where phone=#{phone} limit 1")
    fun findPhone(@Param("phone") phone: String): Optional<String?>
}