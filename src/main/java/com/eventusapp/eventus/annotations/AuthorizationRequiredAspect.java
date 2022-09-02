package com.eventusapp.eventus.annotations;
import com.eventusapp.eventus.security.JWTService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class AuthorizationRequiredAspect  {

    @Autowired
    JWTService jwtService;

    @Autowired
    HttpServletRequest request;

    @Before("@annotation(com.eventusapp.eventus.annotations.AuthorizationRequired)")
    public void authorize(){
        String token = request.getHeader("Authorization");
        if(token==null){
            throw new RuntimeException("Error: Authorization Failed!");
        }
        if(!(jwtService.verifyToken(token))){
            throw new RuntimeException("Error: Authorization Failed!");
        }
    }

}
