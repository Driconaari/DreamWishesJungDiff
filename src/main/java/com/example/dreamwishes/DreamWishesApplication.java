    package com.example.dreamwishes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EntityScan("com.example.dreamwishes.entity")  // Specify the correct base package for entity scanning
public class DreamWishesApplication {

    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> hiddenHttpMethodFilter() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registrationBean = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        registrationBean.setOrder(1); // Set the order if needed
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(DreamWishesApplication.class, args);
    }
}
