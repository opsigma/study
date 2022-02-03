package ru.otus.homework.service.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.stream.InputStreamDaoScanner;
import ru.otus.homework.dao.stream.OutputStreamDaoPrintStream;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShellServiceConsole implements ShellService {

    private final InputStreamDaoScanner inputStream;
    private final OutputStreamDaoPrintStream outputStream;

    private static final String ENTER_NAME = "Enter Name:";
    private static final String ENTER_SURNAME = "Enter Surname:";

    @Override
    public String getUserName() {
        return getInfo(ENTER_NAME, "TEST_USER");
    }

    @Override
    public String getUserSurname(){
        return getInfo(ENTER_SURNAME, "TEST_SURNAME");
    }

    @Override
    public String getInfo(String lineString, String defaultValue) {
        outputStream.outputLine(lineString);
        return Optional.ofNullable(inputStream.readLine()).filter(s -> s.length() > 0).orElse(defaultValue);
    }
}
