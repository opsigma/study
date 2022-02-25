package ru.otus.homework.service.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.stream.InputStreamDaoScanner;
import ru.otus.homework.dao.stream.OutputStreamDaoPrintStream;
import ru.otus.homework.service.locale.LocaleServiceSimple;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleServiceSimple implements ConsoleService {

    private final InputStreamDaoScanner inputStream;
    private final OutputStreamDaoPrintStream outputStream;
    private final LocaleServiceSimple localeService;

    private static final String C_ENTER_NAME = "strings.app.service.shell.enterName";
    private static final String C_ENTER_SURNAME = "strings.app.service.shell.enterSurname";

    @Override
    public String getUserName() {
        return getInfo(localeService.getMessage(C_ENTER_NAME), "TEST_USER");
    }

    @Override
    public String getUserSurname(){
        return getInfo(localeService.getMessage(C_ENTER_SURNAME), "TEST_SURNAME");
    }

    @Override
    public String getInfo(String lineString, String defaultValue) {
        outputStream.outputLine(lineString);
        return Optional.ofNullable(inputStream.readLine()).filter(s -> s.length() > 0).orElse(defaultValue);
    }
}
