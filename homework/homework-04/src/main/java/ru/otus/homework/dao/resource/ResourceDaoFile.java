package ru.otus.homework.dao.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class ResourceDaoFile implements ResourceDao {

    @Override
    @Nullable
    public InputStream loadResource(String path) {
        try {
            return new ClassPathResource(path).getInputStream();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
