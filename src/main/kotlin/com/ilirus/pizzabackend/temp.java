package com.ilirus.pizzabackend;

import com.ilirus.pizzabackend.dto.Dto;
import com.ilirus.pizzabackend.dto.enums.StatusEnum;
import com.ilirus.pizzabackend.model.receive.RcPizza;
import com.ilirus.pizzabackend.model.receive.RcUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

//@RestControllerAdvice
public class temp {

//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public Dto<RcUser> bindExceptionUser(BindException be) {
//        return new Dto<>(StatusEnum.WRONG_PARAM);
//    }
}
