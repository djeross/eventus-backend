package com.eventusapp.eventus.apis;

import com.eventusapp.eventus.forms.UserForm;

import com.eventusapp.eventus.models.User;
import com.eventusapp.eventus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@Validated
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = {RequestMethod.POST}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> Events(@Valid @ModelAttribute UserForm form) {
        User newUser = userService.addNewUser(form); /*Utilizes the UserService Class in the Service Package*/
        Object x =new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
