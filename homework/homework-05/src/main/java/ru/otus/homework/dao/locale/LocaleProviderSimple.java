package ru.otus.homework.dao.locale;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleProviderSimple implements LocaleProvider {

    @Getter
    private final Locale locale;

    public LocaleProviderSimple(@Value("${app.dao.localeProvider.lang}") Locale locale) {
        this.locale = locale;
    }

}
