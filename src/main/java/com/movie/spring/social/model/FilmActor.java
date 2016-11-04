package com.movie.spring.social.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "FilmActor")
@Table(name = "film_actor")
public class FilmActor implements Serializable,Comparable {

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

    @Id
    @Column(name = "film_id", unique = true, nullable = false)
    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
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
    @JoinColumn(name = "actor_id" ,insertable = false, updatable = false)
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
        if (film != null ? !film.equals(filmActor.film) : filmActor.film != null) return false;
        if (actor != null ? !actor.equals(filmActor.actor) : filmActor.actor != null) return false;
        return last_update != null ? last_update.equals(filmActor.last_update) : filmActor.last_update == null;

    }

    @Override
    public int hashCode() {
        int result = film_id != null ? film_id.hashCode() : 0;
        result = 31 * result + (actor_id != null ? actor_id.hashCode() : 0);
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (last_update != null ? last_update.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FilmActor{" +
                "film_id=" + film_id +
                ", actor_id=" + actor_id +
                ", film=" + film +
                ", actor=" + actor +
                ", last_update=" + last_update +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

