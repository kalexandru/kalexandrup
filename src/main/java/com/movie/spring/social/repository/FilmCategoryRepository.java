package com.movie.spring.social.repository;

import com.movie.spring.social.model.Film;
import com.movie.spring.social.model.FilmCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oleksandr.kushnir on 02.11.2016.
 */
@Repository
public class FilmCategoryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }

    @Cacheable("filmCategories")
    public List<FilmCategory> retrieveFilmCategories() {
        return jdbcTemplate.query("select * from film_category ", new RowMapper<FilmCategory>() {
            @Override
            public FilmCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
                FilmCategory filmCategory = new FilmCategory();
                filmCategory.setFilm_id(rs.getLong("film_id"));
                filmCategory.setCategory_id(rs.getLong("category_id"));

                return filmCategory;
            }
        });
    }


//
   public List<FilmCategory> getFilmCategories() {
//
//      //  List list = session.createQuery("from com.movie.spring.social.model.FilmCategory").list();
  return null;
  }

}
