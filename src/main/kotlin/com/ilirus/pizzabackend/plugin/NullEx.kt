package com.ilirus.pizzabackend.plugin

/**
 * 判断对象中每个值是否均不为空
 */
@Throws(Exception::class)
fun allFieldsNotNull(obj: Any): Boolean {
    val clazz = obj.javaClass
    val fields = clazz.declaredFields
    for(f in fields) {
        val accessible = f.canAccess(obj)
        f.isAccessible = true
        val value = f.get(obj)
        f.isAccessible = accessible
        value?:return false
    }
    return true
}

/**
 * 判断非null执行第一个block,it为对象的非null类型;为null则执行第二个block
 *
 * sample:
 * val result = nullableString.notNullElse {
 *  "$it is not null"
 * } ({ "is null" })
 */
fun <T, R> T?.notNullElse(block: (T)-> R): (() -> R) -> R = { if (this == null) it() else block(this) }

/**
 * 判断2个变量都非空执行block, 参数a, b为对象的非null类型
 * allNotNull(first, second) { a, b ->
 *     printText("$a, $b is all not null")
 * }
 */
fun <A, B> allNotNull(first: A?, second: B?, block: (A, B) -> Unit) {
    if (first != null && second != null) block(first, second)
}

/**
 * 判断3个变量都非空执行block, 参数a, b为对象的非null类型
 * allNotNull(first, second) { a, b ->
 *     printText("$a, $b is all not null")
 * }
 */
fun <A, B, C> allNotNull(first: A?, second: B?, third: C?, block: (A, B, C) -> Unit) {
    if (first != null && second != null && third != null) block(first, second, third)
}

/**
 * 判断2个变量都非空执行第一个block, 参数a, b, c为对象的非null类型；为null则执行第二个block
 *  val result = allNotNullElse(first, second, third) { a, b, c ->
 *     "$a, $b, $c is all not null"
 *  } ({ "one of them is null"})
 */
fun <A, B, R> allNotNullElse(first: A?, second: B?, block: (A, B) -> R) : (() -> R) -> R {
    return {
        if (first != null && second != null) {
            block(first, second)
        } else {
            it()
        }
    }

}

/**
 * 判断3个变量都非空执行第一个block, 参数a, b, c为对象的非null类型；为null则执行第二个block
 *  val result = allNotNullElse(first, second, third) { a, b, c ->
 *     "$a, $b, $c is all not null"
 *  } ({ "one of them is null"})
 */
fun <A, B, C, R> allNotNullElse(first: A?, second: B?, third: C?, block: (A, B, C) -> R) : (() -> R) -> R {
    return {
        if (first != null && second != null && third != null) {
            block(first, second, third)
        } else {
            it()
        }
    }

}