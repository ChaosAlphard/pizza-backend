package com.ilirus.pizzabackend.dao

import com.ilirus.pizzabackend.model.Pizza
import org.apache.ibatis.annotations.*
import java.util.*

@Mapper
interface PizzaDao {

    @Select("select * from pizza")
    fun findAllPizza(): List<Pizza>

    @Select("select * from pizza limit #{begin}, #{limit}")
    fun findPizza(@Param("begin") begin: Int,
                  @Param("limit") limit: Int): List<Pizza>

    @Select("select * from pizza where id=#{id} limit 1")
    fun findPizzaByID(@Param("id") id: Int): Optional<Pizza?>

    @Select("select id from pizza where id=#{id} limit 1")
    fun isPizzaExist(@Param("id") id: Int): Optional<Int?>

    @Select("select * from pizza where name like #{query} limit #{begin}, #{limit}")
    fun findPizzaByName(@Param("query") query: String,
                        @Param("begin") begin: Int,
                        @Param("limit") limit: Int): List<Pizza>

    @Select("""select * from pizza where priceSmall between #{min} and #{max} or
            priceBig between #{min} and #{max} limit #{begin}, #{limit}""")
    fun findPizzaByPrice(@Param("min") min: String,
                         @Param("max") max: String,
                         @Param("begin") begin: Int,
                         @Param("limit") limit: Int): List<Pizza>

    @Insert("""insert into pizza(name, pictureLink, picturePreLink, priceSmall, priceBig, description)
            value(#{name}, #{pictureLink}, #{picturePreLink}, #{priceSmall}, #{priceBig}, #{description})""")
    fun addNewPizza(@Param("name") name: String,
                    @Param("pictureLink") pictureLink: String,
                    @Param("picturePreLink") picturePreLink: String,
                    @Param("priceSmall") priceSmall: Int,
                    @Param("priceBig") priceBig: Int,
                    @Param("description") description: String): Int

    @Update("""update pizza set name=#{name}, pictureLink=#{pictureLink}, picturePreLink=#{picturePreLink},
            priceSmall=#{priceSmall}, priceBig=#{priceBig}, description=#{description} where id=#{id}""")
    fun updatePizza(@Param("id") id: Int,
                    @Param("name") name: String,
                    @Param("pictureLink") pictureLink: String,
                    @Param("picturePreLink") picturePreLink: String,
                    @Param("priceSmall") priceSmall: Int,
                    @Param("priceBig") priceBig: Int,
                    @Param("description") description: String): Int

    @Delete("delete from pizza where id=#{id}")
    fun deletePizza(@Param("id") id: Int): Int
}