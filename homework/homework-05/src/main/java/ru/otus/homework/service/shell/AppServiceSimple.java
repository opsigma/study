package ru.otus.homework.service.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppServiceSimple implements AppService {

    @Override
    public void stopApplication() {
        System.exit(1);
    }

}
