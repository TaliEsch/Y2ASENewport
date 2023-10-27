package com.example.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class requestRouter implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/councilInput").setViewName("councilInput");
        registry.addViewController("/homepage").setViewName("index");
        registry.addViewController("/buyRent").setViewName("buyRent");
        registry.addViewController("/error").setViewName("error/error");
    }
}
