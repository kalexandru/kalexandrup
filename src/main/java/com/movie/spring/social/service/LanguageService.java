package com.movie.spring.social.service;

import com.movie.spring.social.model.Film;
import com.movie.spring.social.model.Language;
import com.movie.spring.social.repository.FilmRepository;
import com.movie.spring.social.repository.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleksandr.kushnir on 02.11.2016.
 */
@Service("languageService")
public class LanguageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);
    @Autowired
    private LanguageRepository languageRepository;


    public LanguageRepository getLanguageRepository() {
        return languageRepository;
    }


    public void setLanguageRepository(LanguageRepository filmRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguages() {
        return languageRepository.retrieveLanguages();
    }

}
