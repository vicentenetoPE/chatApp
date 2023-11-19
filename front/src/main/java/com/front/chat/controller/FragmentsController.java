package com.front.chat.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/fragments")
public class FragmentsController {

    @GetMapping("/navbar")
    public String navbar() {
        return "navBar.html";
    }

    
}
