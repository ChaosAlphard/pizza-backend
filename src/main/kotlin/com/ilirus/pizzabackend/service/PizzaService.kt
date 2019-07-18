package com.ilirus.pizzabackend.service

import com.ilirus.pizzabackend.dto.Dto
import com.ilirus.pizzabackend.model.Pizza
import com.ilirus.pizzabackend.model.receive.RcPizza
import com.ilirus.pizzabackend.model.receive.UdPizza

interface PizzaService {
    /**
     * 查找所有Pizza
     * @return Dto<Pizza(Pizza列表)>
     */
    fun findAllPizza(): Dto<Pizza>

    /**
     * 查找指定数目的Pizza
     * @param begin: 从第几条记录开始
     * @param limit: 查询几条记录
     * @return Dto<Pizza(Pizza列表)>
     */
    fun findPizza(begin: Int, limit: Int): Dto<Pizza>

    /**
     * 按ID查找Pizza
     * @param id: ID
     * @return Dto<Pizza(单个Pizza)>
     */
    fun findPizzaByID(id: Int): Dto<Pizza>

    /**
     * 查找Pizza是否存在
     * @param id: ID
     * @return true/false
     */
    fun isPizzaExist(id: Int): Boolean

    /**
     * 按名称查找相似Pizza
     * @param query: 名称
     * @param begin: 从第几条记录开始
     * @param limit: 查询几条记录
     * @return Dto<Pizza(Pizza列表)>
     */
    fun findPizzaByName(query: String, begin: Int, limit: Int): Dto<Pizza>

    /**
     * 按价格区间查找Pizza
     * @param min: 最低价格
     * @param max: 最高价格
     * @param begin: 从第几条记录开始
     * @param limit: 查询几条记录
     * @return Dto<Pizza(Pizza列表)>
     */
    fun findPizzaByPrice(min: String, max: String, begin: Int, limit: Int): Dto<Pizza>

    /**
     * 新增Pizza
     * @param pizza: 从前端发来的Pizza model (ReceivePizza)
     * @return Dto<RcPizza> (实际上并不包含Pizza模型)
     */
    fun addPizza(pizza: RcPizza): Dto<RcPizza>

    /**
     * 更新Pizza
     * @param pizza: 从前端发来的Pizza model (UpdatePizza)
     * @return Dto<UdPizza> (实际上并不包含Pizza模型)
     */
    fun updatePizza(pizza: UdPizza): Dto<UdPizza>

    /**
     * 删除Pizza
     * @param id: 要删除的Pizza ID
     * @return Dto<RcPizza> (实际上并不包含Pizza模型)
     */
    fun deletePizza(id: Int): Dto<RcPizza>
}