package com.ilirus.pizzabackend.interceptor

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class CROSInterceptor : WebMvcConfigurationSupport() {

    override fun addInterceptors(registry: InterceptorRegistry) {
        // super.addInterceptors(registry)
        val cros = object : HandlerInterceptor {
            @Throws(Exception::class)
            override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, o: Any): Boolean {
                request.getHeader(HttpHeaders.ORIGIN)?.let {
                    response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"))
                    response.addHeader("Access-Control-Allow-Credentials", "true")
                    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH, HEAD")
                    response.addHeader("Access-Control-Allow-Headers", "Content-Type")
                    response.addHeader("Access-Control-Max-Age", "3600")
                }
                return true
            }
        }
        registry.addInterceptor(cros)
                .addPathPatterns(mutableListOf("/pizza", "/user"))

        val role = object : HandlerInterceptor {
            @Throws(Exception::class)
            override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, o: Any): Boolean {
                val lv = request.session.getAttribute("level")
                return if(lv == 2 || lv == "2") {
                    true
                } else {
                    response.characterEncoding = "UTF-8"
                    response.contentType = "text/html; charset=UTF-8"
                    response.writer.print("""{"code": "403", "msg": "权限不足"}""")
                    response.writer.flush()
                    response.writer.close()
                    false
                }
            }
        }
        registry.addInterceptor(role)
                .addPathPatterns(mutableListOf(
                    "/pizza/newpizza",
                    "/pizza/editpizza",
                    "/pizza/deletepizza"
                ))
    }
}