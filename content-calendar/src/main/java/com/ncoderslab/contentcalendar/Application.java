package com.ncoderslab.contentcalendar;

import com.ncoderslab.contentcalendar.model.Content;
import com.ncoderslab.contentcalendar.model.Status;
import com.ncoderslab.contentcalendar.model.Type;
import com.ncoderslab.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        //Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);

        int beanDefinitionCount = ctx.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);

    }

    @Bean
    public CommandLineRunner dbInitializer(ContentRepository contentRepository) {
        System.out.println("I am from dbInitializer");
        return args -> {
            Content content = new Content(null, "My init Blog post", "Description", Status.COMPLETED, Type.ARTICLE, LocalDateTime.now(), null, "");
            //contentRepository.save(content);
        };
    }

}
