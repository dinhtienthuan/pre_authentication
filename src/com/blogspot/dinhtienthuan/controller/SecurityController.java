package com.blogspot.dinhtienthuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blogspot.dinhtienthuan.cryptography.Cryptography;

@Controller
public class SecurityController {
    @Autowired
    private Cryptography cryptography;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginViaGetMethod() {
        System.out.println("In SecurityController, loginViaGetMethod() method. Load login page.");
        return "login";
    }

}
