package com.movie.spring.social.repository;

import com.movie.spring.social.model.FilmActor;
import com.movie.spring.social.model.FilmCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Repository
public class FilmActorRepository {


    private static final Logger LOGGER = LoggerFactory.getLogger(FilmActorRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }
    @Cacheable("filmActors")
    public List<FilmActor> retrieveFilmActors() {
        return jdbcTemplate.query("select * from film_actor ", new RowMapper<FilmActor>() {
            @Override
            public FilmActor mapRow(ResultSet rs, int rowNum) throws SQLException {
                FilmActor filmActor = new FilmActor();
                filmActor.setActor_id(rs.getLong("actor_id"));
                filmActor.setFilm_id(rs.getLong("film_id"));
                filmActor.setLast_update(rs.getDate("last_update"));
                return filmActor;
            }
        });
    }



}
