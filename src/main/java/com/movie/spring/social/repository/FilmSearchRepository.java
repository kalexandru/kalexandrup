package com.movie.spring.social.repository;

import com.movie.spring.social.model.Film;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    public List<Film> search(String text) {

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
                fullTextEntityManager.createFullTextQuery(query, Film.class);

        @SuppressWarnings("unchecked")
        List<Film> results = jpaQuery.getResultList();
        Set<Film> filmSet = new HashSet<>();
        for(int i=0;i<results.size();i++){
            filmSet.add(results.get(i));
        }

        System.out.println(filmSet.toString());

        return results;
    }
}
