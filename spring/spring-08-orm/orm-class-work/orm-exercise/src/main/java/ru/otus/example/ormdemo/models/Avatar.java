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
@Table(name = "avatar")
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {
    @Id
    private long id;
    @Column(name = "photo_url")
    private String photoUrl;
}
