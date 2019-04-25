package com.treino.libreria.controller;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomeControllerTest {

    HomeController homeController = new HomeController();

    /*@Test
    public void shouldShowWelcomePage() {
        ModelMap welcome = homeController.getWelcome();

        assertThat(welcome.getViewName(), is("welcome"));
    }*/

}
