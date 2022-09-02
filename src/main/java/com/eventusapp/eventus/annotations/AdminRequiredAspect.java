package com.eventusapp.eventus.annotations;
import com.eventusapp.eventus.security.JWTService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AdminRequiredAspect {

    @Autowired
    JWTService jwtService;

    @Autowired
    HttpServletRequest request;

    @AuthorizationRequired
    @Before("@annotation(com.eventusapp.eventus.annotations.AdminRequired)")
    public void adminAccess(){
        String token = request.getHeader("Authorization");
        if(token==null){
            throw new RuntimeException("Error: Authorization Failed!");
        }
        if(!(jwtService.verifyToken(token))){
            throw new RuntimeException("Error: Authorization Failed!");
        }
        if(!(jwtService.getRole(token).equals("ADMIN"))){
            throw new RuntimeException("Adminstrator Priviledge Required");
        }
    }

}
