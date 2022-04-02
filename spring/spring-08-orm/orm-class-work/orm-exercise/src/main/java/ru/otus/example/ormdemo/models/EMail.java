package ru.otus.example.ormdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "email")
@NoArgsConstructor
@AllArgsConstructor
public class EMail {
    @Id
    private long id;
    @Column(name = "email")
    private String email;
}
