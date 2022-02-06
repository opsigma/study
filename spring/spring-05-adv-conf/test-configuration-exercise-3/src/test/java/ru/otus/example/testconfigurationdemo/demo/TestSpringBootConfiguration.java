package ru.otus.example.testconfigurationdemo.demo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;
import ru.otus.example.testconfigurationdemo.family.childrens.Son;
import ru.otus.example.testconfigurationdemo.family.parents.Father;

@ComponentScan("ru.otus.example.testconfigurationdemo.family.parents")
@SpringBootConfiguration
public class TestSpringBootConfiguration {

    @Bean
    FamilyMember son(){
        return new Son();
    }

    @Bean
    FamilyMember father(){
        return new Father();
    }
}
