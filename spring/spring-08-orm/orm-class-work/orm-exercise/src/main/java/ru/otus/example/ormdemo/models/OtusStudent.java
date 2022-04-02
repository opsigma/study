package ru.otus.example.ormdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OtusStudent {
    @Id
    private long id;
    private String name;

    //private Avatar avatar;
    //private List<EMail> emails;
    //private List<Course> courses;
}
