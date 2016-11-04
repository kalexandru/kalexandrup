package com.movie.spring.social.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.search.annotations.*;

import org.hibernate.search.annotations.Index;
import org.hibernate.search.bridge.builtin.LongBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.*;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="film", indexes = {
        @javax.persistence.Index(columnList="film_id", name="film_id_idx")
})
@Indexed
public class Film implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Film.class);

    private Long film_id;

    private String title;
    private String description;
    private Long release_year;
    private Long length;
    private String rating;
    private String language;
    private String categoryName;
    private String actorName;
    private Long language_id;


    private Collection<FilmCategory> filmCategories = new HashSet<FilmCategory>();
    private Collection<FilmActor> filmActors = new HashSet<FilmActor>();


    public Film(Long film_id, String title, String description, Long release_year, Long length, String rating, String language, String categoryName, String actorName, Long language_id, StringBuffer actors, Collection<FilmCategory> filmCategories, Collection<FilmActor> filmActors) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.length = length;
        this.rating = rating;
        this.language = language;
        this.categoryName = categoryName;
        this.actorName = actorName;
        this.language_id = language_id;
        this.filmCategories = filmCategories;
        this.filmActors = filmActors;
    }

    public Film() {

    }
    @Field(index= org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
    @FieldBridge(impl = LongBridge.class)
    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }

    @Id
    @Column(name = "film_id", unique = true, nullable = false)
    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }
    @Field(index= org.hibernate.search.annotations.Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Field(index= org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Transient
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Transient
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {


        this.actorName = actorName;
    }
    @Transient
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(mappedBy = "film")
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexedEmbedded
    public Collection<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(Collection<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }

    @OneToMany(mappedBy = "film")
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexedEmbedded
    public Collection<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Collection<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }
    @Field(index= org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
    @FieldBridge(impl = LongBridge.class)
    public Long getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Long release_year) {
        this.release_year = release_year;
    }

    @Field(index= org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
    @FieldBridge(impl = LongBridge.class)
    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
    @Field(index= org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (film_id != null ? !film_id.equals(film.film_id) : film.film_id != null) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (description != null ? !description.equals(film.description) : film.description != null) return false;
        if (release_year != null ? !release_year.equals(film.release_year) : film.release_year != null) return false;
        if (length != null ? !length.equals(film.length) : film.length != null) return false;
        if (rating != null ? !rating.equals(film.rating) : film.rating != null) return false;
        if (language != null ? !language.equals(film.language) : film.language != null) return false;
        if (categoryName != null ? !categoryName.equals(film.categoryName) : film.categoryName != null) return false;
        if (actorName != null ? !actorName.equals(film.actorName) : film.actorName != null) return false;
        if (language_id != null ? !language_id.equals(film.language_id) : film.language_id != null) return false;
        if (filmCategories != null ? !filmCategories.equals(film.filmCategories) : film.filmCategories != null)
            return false;
        return filmActors != null ? filmActors.equals(film.filmActors) : film.filmActors == null;

    }

    @Override
    public int hashCode() {
        int result = film_id != null ? film_id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (release_year != null ? release_year.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (actorName != null ? actorName.hashCode() : 0);
        result = 31 * result + (language_id != null ? language_id.hashCode() : 0);
        result = 31 * result + (filmCategories != null ? filmCategories.hashCode() : 0);
        result = 31 * result + (filmActors != null ? filmActors.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("film_id : ").append(getFilm_id());
        strBuff.append(", title : ").append(getTitle());

        return strBuff.toString();
    }
}
