package com.movie.spring.social.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Formatter;

/**
 * 1,language_id,sakila,language,TINYINT UNSIGNED,binary,3,1,0
 2,name,sakila,language,CHAR,utf8,20,8,0
 3,last_update,sakila,language,TIMESTAMP,binary,19,19,0

 */

public class Language {

    private Long id;
    private String name;

    public Language() {
    }

    public Language(Long id, String name, String last_update) {
        this.id = id;
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (id != null ? !id.equals(language.id) : language.id != null) return false;
        return name != null ? name.equals(language.name) : language.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
