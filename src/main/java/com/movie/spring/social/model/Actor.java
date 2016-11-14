package com.movie.spring.social.model;

import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Entity(name = "Actor")
@Table
@Indexed
public class Actor implements Serializable,Comparable<Actor> {

    private Long actor_id;
    private String first_name;
    private String last_name;
    private Date last_update;


    private Set<FilmActor> filmActors;

    public Actor() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "actor")
    //   @JoinColumn(name="actor_id", insertable = false , updatable = false)
    @IndexedEmbedded
    public Set<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Set<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }

    @Field(index= org.hibernate.search.annotations.Index.YES, analyze= Analyze.YES, store= Store.NO)
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    @Field(index= org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store=Store.NO)
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

//
//    @Override
//    public String toString() {
//        StringBuffer strBuff = new StringBuffer();
//        strBuff.append("actor_id : ").append(getActor_id());
//        strBuff.append(", first_name : ").append(getFirst_name());
//        strBuff.append(", last_name : ").append(getLast_name());
//        return strBuff.toString();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (actor_id != null ? !actor_id.equals(actor.actor_id) : actor.actor_id != null) return false;
        if (first_name != null ? !first_name.equals(actor.first_name) : actor.first_name != null) return false;
        if (last_name != null ? !last_name.equals(actor.last_name) : actor.last_name != null) return false;
        if (last_update != null ? !last_update.equals(actor.last_update) : actor.last_update != null) return false;
     //   filmActors != null ? filmActors.equals(actor.filmActors) : actor.filmActors == null
        return true ;

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



    public static final Comparator<Actor> NameComparator = new Comparator<Actor>(){



        @Override
        public int compare(Actor o1, Actor o2) {
            return o1.getFirst_name().compareTo(o2.getFirst_name());
        }
    };


    @Override
    public int compareTo(Actor actor) {
        return 0;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("actor_id : ").append(getActor_id());
        strBuff.append(", first_name : ").append(getFirst_name());
        strBuff.append(", last_name : ").append(getLast_name());

        return strBuff.toString();
    }

}
