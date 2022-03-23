package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final PersonDao personDao;

    @Override
    public void example() {
        log.info("Person count:{}", personDao.count());
        personDao.insert(new Person(2,"Boris"));
        log.info("Person count after insert:{}", personDao.count());
        log.info("Person getById:{}", personDao.getById(2));
        log.info("Person getAll:{}", personDao.getAll());
        personDao.deleteById(2);
        log.info("Person count after deletion: {}", personDao.count());

    }
}
