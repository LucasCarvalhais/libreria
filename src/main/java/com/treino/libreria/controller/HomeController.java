package com.treino.libreria.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping("/welcome")
    public ModelAndView getWelcome() {
        return new ModelAndView("welcome");
    }

}
