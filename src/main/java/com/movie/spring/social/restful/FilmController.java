package com.movie.spring.social.restful;

import com.movie.spring.social.model.*;
import com.movie.spring.social.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oleksandr.kushnir on 01.11.2016.
 */
@Controller
public class FilmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmController.class);

    private FilmService filmService;
    private LanguageService languageService;
    private FilmCategoryService filmCategoryService;
    private CategoryService categoryService;
    private FilmActorService filmActorService;
    private ActorService actorService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setLanguageService(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Autowired
    public void setFilmCategoryService(FilmCategoryService filmCategoryService) {
        this.filmCategoryService = filmCategoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setFilmActorService(FilmActorService filmActorService) {
        this.filmActorService = filmActorService;
    }

    @Autowired
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @RequestMapping("/films")
    public String showFilms(Model model) {

        List<Film> films = filmService.getFilms();
     //   films = fillWithLanguage(films);
     //   films = fillFilmsWithFilmActors(films);
        model.addAttribute("films", films);
        LOGGER.info("Showing all films from Data Base");
        return "films";
    }

    private List<Film> fillWithLanguage(List flms){
        List<Language> languages = languageService.getLanguages();

        Film film;
        Language language;
        Iterator itrLang = languages.iterator();

        while (itrLang.hasNext()) {
            language = (Language) itrLang.next();
            for (int i = 0; i < flms.size(); i++) {

                if (language.getId() == ((Film)flms.get(i)).getLanguage_id()) {
                    ((Film)flms.get(i)).setLanguage(language.getName());
                }
            }

        }
        return flms;
    }

    private List<Film> fillFilmsWithFilmCategories(List films) {
        Film film;
        FilmCategory filmCategory;
        Category ctgr;
        List<FilmCategory> filmCategories = getFilmCategoryService().getFilmCategoryObjects();
        Iterator itrFilmCategories = filmCategories.iterator();

        for (int i=0;i<films.size();i++) {

            while (itrFilmCategories.hasNext()) {
                filmCategory = (FilmCategory) itrFilmCategories.next();
                if (filmCategory.getFilm_id().equals(((Film)films.get(i)).getFilm_id())) {
                    ctgr = findCategoryById(filmCategory.getCategory_id());
                    ((Film)films.get(i)).setCategoryName(ctgr.getName());
                    break;
                }
            }
        }
        return films;
    }

    private Category findCategoryById(Long id) {
        List<Category> categoryList = getCategoryService().getCategoryObjects();
        Category category = null;
        for (Category cat : categoryList) {
            if (cat.getCategory_id().equals(id)) {
                category = cat;
                break;
            }
        }
        return category;
    }

//    private List<Film> fillFilmsWithFilmActors(List filmLst) {
//        Film film;
//        FilmActor filmActor;
//        Actor actr;
//        List<FilmActor> filmActors = getFilmActorService().getFilmActorObjects();
//        Iterator itrFilmActors = filmActors.iterator();
//        StringBuffer sb = null;
//        StringBuffer result = new StringBuffer();
//        for (int i=0;i<filmLst.size();i++) {
//
//            for (int k=0;k<filmActors.size();k++) {
//
//                filmActor = filmActors.get(k);
//                if (filmActor.getFilm_id().equals(((Film)filmLst.get(i)).getFilm_id())) {
//                    actr = findActorById(filmActor.getFilm_id());
//                    sb = new StringBuffer();
//                    sb.append(" ").append(actr.getFirst_name()).append(" ").append(actr.getLast_name());
//
//                    result.append(sb);
//                }
//
//            }
//            ((Film)filmLst.get(i)).setActorName(new String(result.toString()));
//            result = new StringBuffer();
//        }
//        return filmLst;
//    }

    private Actor findActorById(Long id){
        List<Actor> actorList = getActorService().getActorObjects();
        Actor actor = null;
        for (Actor act : actorList) {
            if (act.getActor_id().equals(id)) {
                actor = act;
                break;
            }
        }
        return actor;
    }

    @RequestMapping("/createfilm")
    public String createFilm(Model model) {
        model.addAttribute("film", new Film());
        LOGGER.info("Creating a new film message");
        return "createfilm";
    }

    @RequestMapping(value = "/docreate", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid Film film, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.info("Please once more fill the tabs");
            return "createfilm";
        } else {
            LOGGER.info("Form validated.");
            filmService.create(film);
        }
        LOGGER.info("Message created");
        return "filmcreated";
    }

    @RequestMapping("/dofilter")
    public String filter(Model model, Film film) {
        List<Film> filmList = null;//filmService.filter(film.getMessage_time(),film.getCustomTime());
        model.addAttribute("comments", filmList);
        LOGGER.info("Filtering messages by time range");
        return "films";
    }

    @RequestMapping("/dosearch")
    public String show(Model model, Film film) {
        List<Film> filmList = null;
//        if (film.getTitle() != null) {
//            filmList = filmService.searchTitle(film.getTitle());
//            filmList = fillWithLanguage(filmList);
//            filmList = fillFilmsWithFilmActors(filmList);
//        }
////            if(film.getDescription() != null) {
////                filmList = filmService.searchByDescription(film.getDescription());
////            }
////            if(film.getCategoryName() != null) {
////                filmList = filmService.searchByCategoryName(film.getDescription());
////            }
////            if(film.getActorName() != null) {
////                filmList = filmService.searchByActorName(film.getDescription());
////            }
//            if(film.getLanguage() != null) {
//                System.out.println("Lang ID-->"+film.getLanguage_id());
//            //    filmList = filmService.searchByLanguageName();
//            }
        model.addAttribute("films", fillFilmsWithFilmCategories(filmList));

        LOGGER.info("Searching by the film title");
        return "films";
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public LanguageService getLanguageService() {
        return languageService;
    }

    public FilmCategoryService getFilmCategoryService() {
        return filmCategoryService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public FilmActorService getFilmActorService() {
        return filmActorService;
    }

    public ActorService getActorService() {
        return actorService;
    }
}
