package com.movie.spring.social.model;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "FilmActor")
@Table
@Indexed
public class FilmActor implements Serializable {

    private Long film_id;
    private Long actor_id;

    private Film film;
    private Actor actor;

    private Date last_update;

    public FilmActor() {
    }

    public FilmActor(Long film_id, Long actor_id, Film film, Actor actor, Date last_update) {
        this.film_id = film_id;
        this.actor_id = actor_id;
        this.film = film;
        this.actor = actor;
        this.last_update = last_update;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }
    @Id
    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }

//    @OneToMany(mappedBy = "actors")
//    @ContainedIn
//    public Set<Film> getFilmActors() {
//        return films;
//    }

//
//    @OneToMany(mappedBy = "films")
//    @ContainedIn
//    public Set<Actor> getFilmActors() {
//        return actors;
//    }

    @ManyToOne
    @JoinColumn(name = "film_id",  insertable = false , updatable = false)
    @ContainedIn
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
    @ManyToOne
    @JoinColumn(name = "actor_id",  insertable = false , updatable = false)
    @ContainedIn
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
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

        FilmActor filmActor = (FilmActor) o;

        if (film_id != null ? !film_id.equals(filmActor.film_id) : filmActor.film_id != null) return false;
        if (actor_id != null ? !actor_id.equals(filmActor.actor_id) : filmActor.actor_id != null) return false;
        return last_update != null ? last_update.equals(filmActor.last_update) : filmActor.last_update == null;

    }

    @Override
    public int hashCode() {
        int result = film_id != null ? film_id.hashCode() : 0;
        result = 31 * result + (actor_id != null ? actor_id.hashCode() : 0);
        result = 31 * result + (last_update != null ? last_update.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("actor_id : ").append(getActor_id());
        strBuff.append(", film_id : ").append(getFilm_id());


        return strBuff.toString();
    }
}

