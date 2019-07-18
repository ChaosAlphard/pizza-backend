package com.ilirus.pizzabackend.handler

import com.ilirus.pizzabackend.dto.enums.StatusEnum
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    // OR -> org.springframework.validation.BindException
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.OK)
    fun validException(ve: MethodArgumentNotValidException): HandlerDto {
        val name = ve.bindingResult.objectName
        val map = mutableMapOf<String,String?>()
        ve.bindingResult.fieldErrors.forEach {
            map[it.field] = it.defaultMessage
        }
        return HandlerDto(StatusEnum.WRONG_PARAM, name, map)
    }

    @ExceptionHandler(NullPointerException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun nullException(ne: NullPointerException): HandlerDto {
        return HandlerDto(StatusEnum.LOST_PARAM)
    }

    class HandlerDto {
        var code: Int
        var msg: String
        var errObj: String = ""
        var detail: MutableMap<String, String?> = mutableMapOf()

        constructor(se: StatusEnum) {
            this.code = se.code
            this.msg = se.msg
        }

        constructor(se: StatusEnum, errObj: String, detail: MutableMap<String, String?>) {
            this.code = se.code
            this.msg = se.msg
            this.errObj = errObj
            this.detail = detail
        }
    }
}