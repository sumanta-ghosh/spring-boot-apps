package com.ncoderslab.contentcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);

        int beanDefinitionCount = ctx.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);

    }

}
