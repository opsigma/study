package ru.otus.homework.service.locale;

public interface LocaleService {

    String getMessage(String code);

    String getFormatMessage(String code, Object... ags);
}
