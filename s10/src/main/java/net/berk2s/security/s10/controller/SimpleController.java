package net.berk2s.security.s10.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class SimpleController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, there!";
    }

    @GetMapping("/me")
    public String getMe(Authentication a) {
        return "Hello, " + a.getName() + "!";
    }

    @GetMapping("/read")
    public String getRead() {
        return "You are allowed to read";
    }

    @GetMapping("/write")
    public String getWrite() {
        return "You are allowed to write";
    }


    @GetMapping("/update")
    public String getUpdate() {
        return "You are allowed to update";
    }


    @GetMapping("/delete")
    public String getDelete() {
        return "You are allowed to delete";
    }


    @GetMapping("/manager")
    public String getManager() {
        return "You are manager or more than";
    }


    @GetMapping("/admin")
    public String getAdmin() {
        return "You are admin or more than";
    }

    @GetMapping("/manager2")
    public String getManager2() {
        return "You are manager or more than";
    }


    @GetMapping("/admin2")
    public String getAdmin2() {
        return "You are admin or more than";
    }


}
