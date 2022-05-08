package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.PersonRepository;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
//        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        personRepository.save(new Person("Александр Сергеевич Пушкин"));
        personRepository.save(new Person("Михаил Юрьевич Лермонтов"));
        personRepository.save(new Person("Михаил Сергеевич Горбачев"));
        Iterable<Person> personByNameList = personRepository.findByName("Михаил Юрьевич Лермонтов");
        System.out.printf("personByNameList: %s", personByNameList);
    }


}
