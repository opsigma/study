package ru.otus.example.testconfigurationdemo.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;
import ru.otus.example.testconfigurationdemo.family.parents.Father;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("В NestedTestConfigurationDemoTest семья должна ")
@SpringBootTest
public class NestedTestConfigurationDemoTest {

    @Autowired
    private Map<String, FamilyMember> family;

    @ComponentScan("ru.otus.example.testconfigurationdemo.family")
    @Configuration
    static class NestedTestConfiguration {
        @Bean
        FamilyMember father() {
            return new Father();
        }
    }

    @DisplayName(" содержать маму, папу, сына и собаку ")
    @Test
    void shouldContainAllFamilyWithFather() {
        assertThat(family).containsOnlyKeys("mother", "father", "son", "dog");
    }

}
