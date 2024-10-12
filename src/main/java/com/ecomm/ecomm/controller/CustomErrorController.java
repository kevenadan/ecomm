package com.ecomm.ecomm.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/403")
    public String error403() {
        return "403"; // Nome do arquivo 403.html
    }
    
}