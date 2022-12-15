package com.edu.oauth2.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeResource {
    @RequestMapping({"/welcome"})
    public String welcome() {
        return "Welcome to Edureka Spring Security";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }

}
