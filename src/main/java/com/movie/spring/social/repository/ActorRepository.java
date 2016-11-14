package com.movie.spring.social.repository;

import com.movie.spring.social.model.Actor;
import com.movie.spring.social.model.Film;
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
public class ActorRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }

    @Cacheable("actors")
    public List<Actor> retrieveActors() {
        LOGGER.debug("Started ActorRepository");
        return jdbcTemplate.query("select * from actor ", new RowMapper<Actor>() {
            @Override
            public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
                Actor actor = new Actor();
                actor.setActor_id(rs.getLong("actor_id"));
                actor.setFirst_name(rs.getString("first_name"));
                actor.setLast_name(rs.getString("last_name"));
                actor.setLast_update(rs.getDate("last_update"));
                return actor;
            }
        });
    }

    public List<Actor> searchActorsByActorId(String id) {
        LOGGER.debug("Started searchActorsByActorId()");
        return jdbcTemplate.query("select * from actor act where act.actor_id =" + "'" + id + "'" + ";", new RowMapper<Actor>() {
            @Override
            public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
                Actor actor = new Actor();
                actor.setActor_id(rs.getLong("actor_id"));
                actor.setFirst_name(rs.getString("first_name"));
                actor.setLast_name(rs.getString("last_name"));
                actor.setLast_update(rs.getDate("last_update"));
                return actor;
            }
        });
    }
}
