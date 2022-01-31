package ru.otus.homework.dao.quiz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.resource.ResourceDaoFile;
import ru.otus.homework.domain.quiz.Option;
import ru.otus.homework.domain.quiz.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final String delimiter ;

    private final String path;
    private final ResourceDaoFile resourceDaoFile;

    public QuestionDaoCsv(@Value(value = "${app.dao.questionDao.delimiter}") String delimiter, @Value("${app.dao.questionDao.path}") String path, ResourceDaoFile resourceDaoFile) {
        this.delimiter = delimiter;
        this.path = path;
        this.resourceDaoFile = resourceDaoFile;
    }

    @Override
    @NonNull
    public List<Question> loadQuestions() {
        final ArrayList<Question> questions = new ArrayList<>();
        final InputStream inputStream = resourceDaoFile.loadResource(path);
        if (inputStream == null) {
            return questions;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVFormat csvFormat = CSVFormat.DEFAULT;
            csvFormat = csvFormat.withDelimiter(Optional.ofNullable(delimiter).map(s -> s.charAt(0)).orElse(','));
            CSVParser csvParser = new CSVParser(reader, csvFormat);
            for (CSVRecord csvRecord : csvParser) {
                questions.add(convert(csvRecord));
            }
            return questions;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return questions;
        }

    }

    private Question convert(CSVRecord csvRecord) {
        final Question question = new Question();

        if (csvRecord.size() > 0) {
            question.setQuestion(csvRecord.get(0).trim());
        }
        if (csvRecord.size() > 1) {
            question.setCorrectAnswer(csvRecord.get(1));
        }
        List<Option> options = new ArrayList<>();
        if (csvRecord.size() > 2) {
            for (int i = 2; i < csvRecord.size(); i++) {
                final Option option = new Option();
                option.setOption(csvRecord.get(i));
                options.add(option);
            }
        }
        question.setOptions(options);
        return question;
    }
}
