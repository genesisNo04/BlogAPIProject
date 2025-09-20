package com.example.BloggingApiProject.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Map;

@Component
public class BeanInspector {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void listUserDetailsServices() {
        Map<String, UserDetailsService> beans = applicationContext.getBeansOfType(UserDetailsService.class);
        System.out.println("===== UserDetailsService beans in context =====");
        beans.forEach((name, bean) -> System.out.println(name + " -> " + bean.getClass().getName()));
        System.out.println("===============================================");
    }
}
