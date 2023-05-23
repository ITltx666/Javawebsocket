package com.example.text.see.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pk/")
public class Controllerkk {
    @RequestMapping("see/")
    public String see() {

        return "pk/pk.html";
    }
}
