package com.movie.spring.social.service;

import com.movie.spring.social.repository.FilmCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleksandr.kushnir on 02.11.2016.
 */
@Service("filmCategoryService")
public class FilmCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmCategoryService.class);

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    public List getFilmCategoryObjects() {
        return getFilmCategoryRepository().retrieveFilmCategories();
    }

    public FilmCategoryRepository getFilmCategoryRepository() {
        return filmCategoryRepository;
    }

    public void setFilmCategoryRepository(FilmCategoryRepository filmCategoryRepository) {
        this.filmCategoryRepository = filmCategoryRepository;
    }
}
