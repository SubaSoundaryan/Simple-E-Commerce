package com.test.e.commerce.e.commerce.controller;

import com.test.e.commerce.e.commerce.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticateController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<String> login(@RequestParam("userName") String userName){
        return new ResponseEntity<>(authenticationService.login(userName), HttpStatus.ACCEPTED);
    }
}
