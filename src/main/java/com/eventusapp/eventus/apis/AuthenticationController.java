package com.eventusapp.eventus.apis;


import com.eventusapp.eventus.forms.LoginForm;
import com.eventusapp.eventus.models.User;
import com.eventusapp.eventus.apis.responses.LoginResponse;
import com.eventusapp.eventus.security.JWTService;
import com.eventusapp.eventus.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;

@RequestMapping(value = "/api/v1/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JWTService jwtService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> loginUser(LoginForm form){
        User user = authenticationService.Login(form);
        String token =jwtService.generateToken(user);
        return new ResponseEntity<>(new LoginResponse(user,token), HttpStatus.CREATED);
    }
}
