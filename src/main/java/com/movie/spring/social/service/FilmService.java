package com.movie.spring.social.service;

import com.movie.spring.social.repository.FilmRepository;
import com.movie.spring.social.model.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Created by oleksandr.kushnir on 01.11.2016.
 */
@Service("filmService")
public class FilmService {



        private static final Logger LOGGER = LoggerFactory.getLogger(FilmService.class);
        private FilmRepository filmRepository;


        public FilmRepository getFilmRepository() {
            return filmRepository;
        }

        @Autowired
        public void setFilmRepository(FilmRepository filmRepository) {
            this.filmRepository = filmRepository;
        }

        public List<Film> getFilms() {
            return filmRepository.retrieveFilms();
        }

        public void create(Film film){
            filmRepository.create(film);

        }

        public List<Film> searchTitle(String title){
            return filmRepository.searchFilmsByTitle(title);
        }

//        public List<Film> filter(String firstTime,String secondTime){
//            return filmRepository.filterComments(firstTime,secondTime);
//        }



}
