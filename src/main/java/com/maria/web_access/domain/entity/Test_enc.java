package com.maria.web_access.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_enc")
public class Test_enc {

    @Id
    private Long id;
    private String comment;

    // Конструкторы, геттеры и сеттеры
    public Test_enc() {}

    public Test_enc(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
