package com.movie.spring.social.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 1,film_id,sakila,film_category,SMALLINT UNSIGNED,binary,5,4,0
 2,category_id,sakila,film_category,TINYINT UNSIGNED,binary,3,2,0
 3,last_update,sakila,film_category,TIMESTAMP,binary,19,19,0

 */
@Entity(name = "FilmCategory")
@Table(name = "film_category")
public class FilmCategory implements Serializable {

    private Long film_id;
    private Long category_id;
    private Film film;
    private Category category;
    private Date last_update;

    public FilmCategory() {
    }

    public FilmCategory(Long film_id, Long category_id, Film film, Category category, Date last_update) {
        this.film_id = film_id;
        this.category_id = category_id;
        this.film = film;
        this.category = category;
        this.last_update = last_update;
    }

    @Id
    @Column(name = "film_id", unique = true, nullable = false)
    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }


    @ManyToOne
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @ManyToOne
    @JoinColumn(name = "category_id" ,insertable = false, updatable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmCategory that = (FilmCategory) o;

        if (film_id != null ? !film_id.equals(that.film_id) : that.film_id != null) return false;
        if (category_id != null ? !category_id.equals(that.category_id) : that.category_id != null) return false;
        if (film != null ? !film.equals(that.film) : that.film != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        return last_update != null ? last_update.equals(that.last_update) : that.last_update == null;

    }

    @Override
    public int hashCode() {
        int result = film_id != null ? film_id.hashCode() : 0;
        result = 31 * result + (category_id != null ? category_id.hashCode() : 0);
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (last_update != null ? last_update.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FilmCategory{" +
                "film_id=" + film_id +
                ", category_id=" + category_id +
                ", film=" + film +
                ", category=" + category +
                ", last_update=" + last_update +
                '}';
    }
}
