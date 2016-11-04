package com.movie.spring.social.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
/**
 * 1,category_id,sakila,category,TINYINT UNSIGNED,binary,3,2,0
 2,name,sakila,category,VARCHAR,utf8,25,11,0
 3,last_update,sakila,category,TIMESTAMP,binary,19,19,0

 */
@Entity(name = "Category")
@Table(name = "category")
public class Category  implements Serializable{


    private Long category_id;
    private String name;


    private Collection<FilmCategory> filmCategories = new HashSet<FilmCategory>();

    public Category() {
    }

    public Category( Long category_id, String name, Collection<FilmCategory> filmCategories) {
        this.category_id = category_id;
        this.name = name;
        this.filmCategories = filmCategories;
    }



    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "category")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(Collection<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (category_id != null ? !category_id.equals(category.category_id) : category.category_id != null)
            return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return filmCategories != null ? filmCategories.equals(category.filmCategories) : category.filmCategories == null;

    }

    @Override
    public int hashCode() {
        int result = category_id != null ? category_id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (filmCategories != null ? filmCategories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                ", filmCategories=" + filmCategories +
                '}';
    }
}
