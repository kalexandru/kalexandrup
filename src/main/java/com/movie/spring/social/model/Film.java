package com.movie.spring.social.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.search.annotations.*;

import org.hibernate.search.bridge.builtin.LongBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table
@Indexed
public class Film implements Serializable,Comparable {

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


   // private Collection<FilmCategory> filmCategories = new HashSet<FilmCategory>();

    private Set<FilmActor> filmActors;



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
    @GeneratedValue(strategy = GenerationType.AUTO)
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


//    @IndexedEmbedded(depth = 1)
//    public Collection<FilmCategory> getFilmCategories() {
//        return filmCategories;
//    }
//
//    public void setFilmCategories(Collection<FilmCategory> filmCategories) {
//        this.filmCategories = filmCategories;
//    }

    @OneToMany(mappedBy = "film")
    //@JoinColumn(name="film_id", insertable = false , updatable = false)
    @IndexedEmbedded
    public Set<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Set<FilmActor> filmActors) {
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
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("film_id : ").append(getFilm_id());
        strBuff.append(", title : ").append(getTitle());
        strBuff.append(", description : ").append(getDescription());

        return strBuff.toString();
    }
}
