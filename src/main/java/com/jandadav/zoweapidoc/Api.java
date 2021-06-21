package com.jandadav.zoweapidoc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    @GetMapping("/greeting")
    public String greeting() {
        return "Greetings!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Administration endpoint";
    }

    @GetMapping("/user")
    public String user() {
        return "User endpoint";
    }
}
