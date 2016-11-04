package com.movie.spring.social.repository;

import com.movie.spring.social.model.Category;
import com.movie.spring.social.model.Language;
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
 * Created by oleksandr.kushnir on 02.11.2016.
 */
@Repository
public class CategoryRepository {


    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRepository.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }
    @Cacheable("categories")
    public List<Category> retrieveCategories() {
        LOGGER.debug("Started CategoryRepository");
        return jdbcTemplate.query("select * from category ", new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setCategory_id(rs.getLong("category_id"));
                category.setName(rs.getString("name"));

                return category;
            }
        });
    }

}
