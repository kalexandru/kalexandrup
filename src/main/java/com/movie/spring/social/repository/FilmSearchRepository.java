package com.movie.spring.social.repository;

import com.movie.spring.social.model.Actor;
import com.movie.spring.social.model.Film;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.EntityContext;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.QueryContextBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ObjDoubleConsumer;


/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Repository
@Transactional
public class FilmSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<List> search(String text) {

        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                        getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Film.class).get();



        org.apache.lucene.search.Query query =
                queryBuilder
                        .keyword()
                        .onFields("title", "description", "length", "rating", "release_year","language_id")
                        .matching(text)
                        .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Film.class,Actor.class);




        @SuppressWarnings("unchecked")
        List<Film> filmResults = jpaQuery.getResultList();
        if (filmResults != null) {
            for (Film film : filmResults) {
                System.out.println("Film found = " + filmResults);
            }
        }
        // Seach for film Actor
        queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Actor.class).get();
        query = queryBuilder.keyword().onFields("first_name","last_name").matching(text).createQuery();
        jpaQuery = fullTextEntityManager.createFullTextQuery(query, Actor.class);

        // execute search
        List<Actor> actorResults = jpaQuery.getResultList();

        if (actorResults  != null) {
            for (Actor actor : actorResults ) {
                System.out.println("Film Actor Found = " + actor);
            }
        }
        List<List> resultLists= new  ArrayList<List>();
        resultLists.add(actorResults);
        resultLists.add(filmResults);
        return resultLists;
    }
}
