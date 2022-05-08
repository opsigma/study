package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springdata.domain.Email;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.EmailRepository;
import ru.otus.springdata.repository.PersonRepository;

import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        var email1 = new Email("1@email.com");
        var email2 = new Email("2@email.com");
        var email3 = new Email("3@email.com");

        emailRepository.save(email1);
        emailRepository.save(email2);
        emailRepository.save(email3);

        personRepository.save(new Person("Александр Сергеевич Пушкин", email1));
        personRepository.save(new Person("Михаил Юрьевич Лермонтов", email2));
        personRepository.save(new Person("Михаил Сергеевич Горбачев", email3));
        List<Person> personByNameList = personRepository.findByName("Михаил Юрьевич Лермонтов");
        System.out.printf("personByNameList: %s%n", personByNameList);

        Optional<Person> personByEmail = personRepository.findByEmailAddress(email3.getAddress());
        System.out.printf("personByEmail: %s%n", personByEmail);



        List<Email> emailList = emailRepository.findAll();
        System.out.printf("emailList: %s%n", emailList);
    }


}
