package ru.otus.homework.dao.resource;

import java.io.InputStream;

public interface ResourceDao {
    InputStream loadResource(String path);
}
