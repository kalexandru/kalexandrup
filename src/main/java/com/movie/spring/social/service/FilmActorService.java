package com.movie.spring.social.service;

import com.movie.spring.social.repository.FilmActorRepository;
import com.movie.spring.social.repository.FilmCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Service("filmActorService")
public class FilmActorService {


    private static final Logger LOGGER = LoggerFactory.getLogger(FilmActorService.class);

    @Autowired
    private FilmActorRepository filmActorRepository;

    public List getFilmActorObjects() {
        return getFilmActorRepository().retrieveFilmActors();
    }

    public FilmActorRepository getFilmActorRepository() {
        return filmActorRepository;
    }

    public void setFilmActorRepository(FilmActorRepository filmActorRepository) {
        this.filmActorRepository = filmActorRepository;
    }
}
