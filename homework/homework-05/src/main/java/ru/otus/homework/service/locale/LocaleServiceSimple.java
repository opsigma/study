package ru.otus.homework.service.locale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.locale.LocaleProvider;

@Service
@RequiredArgsConstructor
public class LocaleServiceSimple implements LocaleService {
    private final MessageSource messageSource;
    private final LocaleProvider localeProvider;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, localeProvider.getLocale());
    }

    @Override
    public String getFormatMessage(String code, Object... ags) {
        String msg = getMessage(code);
        return String.format(msg, ags);
    }
}
