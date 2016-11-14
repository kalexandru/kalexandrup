package com.movie.spring.social.repository;

import com.movie.spring.social.model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component("filmRepository")
public class FilmRepository {


    private static final Logger LOGGER = LoggerFactory.getLogger(FilmRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }

    public boolean create(Film film) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(film);


        return jdbcTemplate.update("insert into film (title, description) values (:title,:description)", parameterSource) == 1;
    }

    @Cacheable("films")
    public List<Film> retrieveFilms() {
        return jdbcTemplate.query("select * from film ", new RowMapper<Film>() {
            @Override
            public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
                Film film = new Film();
                film.setFilm_id(rs.getLong("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setLanguage_id(rs.getLong("language_id"));
                film.setLength(rs.getLong("length"));
                film.setRating(rs.getString("rating"));
                film.setRelease_year(rs.getLong("release_year"));
                return film;
            }
        });
    }

    public List<Film> searchFilmsByFilm_id(String film_id) {
        return jdbcTemplate.query("select * from film f where f.film_id =" + "'" + film_id + "'" + ";", new RowMapper<Film>() {
            @Override
            public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
                Film film = new Film();
                film.setFilm_id(rs.getLong("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setLanguage_id(rs.getLong("language_id"));
                film.setLength(rs.getLong("length"));
                film.setRating(rs.getString("rating"));
                film.setRelease_year(rs.getLong("release_year"));
                return film;
            }
        });
    }

    public List<Film> searchFilmsByLanguage(String title) {
        return jdbcTemplate.query("select * from film f where f.title =" + "'" + title + "'" + ";", new RowMapper<Film>() {
            @Override
            public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
                Film film = new Film();
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                return film;
            }
        });
    }

//        public List<Film> filterFilms(String firstTime,String secondTime){
//            return jdbcTemplate.query("select * from film c where c.message_date BETWEEN"+"'"+ firstTime +"'"+"AND"+"'"+ secondTime +"'"+";", new RowMapper<Film>() {
//                @Override
//                public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    Film film = new Film();
//                    film.setTitle(rs.getString("title"));
//                    film.setDescription(rs.getString("description"));
//                    return film;
//                }
//            });
//        }

//        public boolean delete(int id){
//            MapSqlParameterSource params = new MapSqlParameterSource("id",id);
//            return  jdbcTemplate.update("delete from film where film_id=:id",params)==1;
//        }


}
