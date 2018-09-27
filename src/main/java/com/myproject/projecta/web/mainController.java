package com.myproject.projecta.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class mainController implements ErrorController {
    //METHOD FOR CUSTOM ERROR PAGE
    private final String PATH = "/error";
    @RequestMapping(value = PATH)
    public String returnError() {
        return "error";
    }
    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }


}
