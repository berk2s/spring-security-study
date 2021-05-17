package net.berk2s.security.s6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/hello")
    public String handleHello() {
        return "hey, there!";
    }

    @GetMapping("/error") // TODO: make exempt from security filter
    public String handleError() {
        return "something happened that was error!";
    }
}
