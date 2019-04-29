package com.treino.libreria.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomeControllerTest {

    HomeController homeController = new HomeController();

    @Test
    public void shouldShowWelcomePage() {
        ModelAndView welcome = homeController.getWelcome();

        assertThat(welcome.getViewName(), is("welcome"));
    }

}
