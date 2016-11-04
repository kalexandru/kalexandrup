package com.movie.spring.social.repository;

import com.movie.spring.social.model.Film;
import com.movie.spring.social.model.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oleksandr.kushnir on 02.11.2016.
 */
@Component("languageRepository")
public class LanguageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }

    @Cacheable("languages")
    public List<Language> retrieveLanguages() {
        LOGGER.debug("Started LanguageRepository");
        return jdbcTemplate.query("select * from language ", new RowMapper<Language>() {
            @Override
            public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
                Language language = new Language();
                language.setId(rs.getLong("language_id"));
                language.setName(rs.getString("name"));

                return language;
            }
        });
    }


}
