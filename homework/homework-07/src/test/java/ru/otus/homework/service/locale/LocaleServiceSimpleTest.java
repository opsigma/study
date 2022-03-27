package ru.otus.homework.service.locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.homework.dao.locale.LocaleProvider;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с сообщениями должен")
@SpringBootTest
class LocaleServiceSimpleTest {

    private  static  final  String messageSimple = "strings.app.service.locale.message.simple";
    private  static  final  String messageWithParameters = "strings.app.service.locale.message.two.parameters";

    @Autowired
    private LocaleService localeService;

    @Test
    @DisplayName("возвращать сообщение")
    void getMessage() {
        String expected = localeService.getMessage(messageSimple);
        String actual = "A simple string";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("возвращать сообщение с параметрами")
    void getFormatMessage() {
        String expected = localeService.getFormatMessage(messageWithParameters, 2,1);
        String actual = "String with two parameters: 2, 1";
        Assertions.assertEquals(expected, actual);
    }
}
