package com.venesa.ctvvcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thuanlm
 * @created at 11/3/2020
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"","/login.html"})
    public String home(Model model) {
        return "login";
    }
}
