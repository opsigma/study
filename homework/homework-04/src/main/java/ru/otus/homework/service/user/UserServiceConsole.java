package ru.otus.homework.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.user.User;
import ru.otus.homework.service.shell.ConsoleServiceSimple;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceConsole implements UserService {

    private final ConsoleServiceSimple consoleCommandService;

    @Override
    public User getUser() {
        final User user = new User();
        user.setName(consoleCommandService.getUserName());
        user.setSurname(consoleCommandService.getUserSurname());
        return user;
    }

}
