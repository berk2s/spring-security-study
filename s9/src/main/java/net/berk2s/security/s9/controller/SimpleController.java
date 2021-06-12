package net.berk2s.security.s9.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SimpleController {

    @GetMapping
    public String getIndex() {
        return "Hello!";
    }

}
