package ru.otus.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springdata.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
