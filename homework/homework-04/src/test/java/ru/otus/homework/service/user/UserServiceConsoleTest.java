package ru.otus.homework.service.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.homework.domain.user.User;
import ru.otus.homework.service.shell.ConsoleServiceSimple;

@SpringBootTest
@ContextConfiguration(classes = UserConfiguration.class)
class UserServiceConsoleTest {

    @Autowired
    private UserServiceConsole userServiceConsole;

    @MockBean
    private ConsoleServiceSimple consoleCommandService;


    @Test
    void getUser() {
        Mockito.when(consoleCommandService.getUserName()).thenReturn("Tom");
        Mockito.when(consoleCommandService.getUserSurname()).thenReturn("Brown");
        User actual = userServiceConsole.getUser();

        User expected = new User();
        expected.setName("Tom");
        expected.setSurname("Brown");
        Assertions.assertEquals(expected, actual);
    }
}