package com.movie.spring.social.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Entity(name = "Actor")
@Table(name = "actor")
public class Actor implements Serializable {


    private Long actor_id;
    private String first_name;
    private String last_name;
    private Date last_update;

    private Collection<FilmActor> filmActors = new HashSet<FilmActor>();

    public Actor() {
    }

    public Actor(Long actor_id, String first_name, String last_name, Date last_update, Collection<FilmActor> filmActors) {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_update = last_update;
        this.filmActors = filmActors;
    }
    @Id
    @Column(name = "actor_id", unique = true, nullable = false)
    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }




    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "actor")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Collection<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actor_id != null ? !actor_id.equals(actor.actor_id) : actor.actor_id != null) return false;
        if (first_name != null ? !first_name.equals(actor.first_name) : actor.first_name != null) return false;
        if (last_name != null ? !last_name.equals(actor.last_name) : actor.last_name != null) return false;
        if (last_update != null ? !last_update.equals(actor.last_update) : actor.last_update != null) return false;
        return filmActors != null ? filmActors.equals(actor.filmActors) : actor.filmActors == null;

    }

    @Override
    public int hashCode() {
        int result = actor_id != null ? actor_id.hashCode() : 0;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (last_update != null ? last_update.hashCode() : 0);
        result = 31 * result + (filmActors != null ? filmActors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actor_id=" + actor_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", last_update=" + last_update +
                ", filmActors=" + filmActors +
                '}';
    }
}
