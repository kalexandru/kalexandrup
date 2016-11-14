package com.movie.spring.social.restful;

import com.movie.spring.social.model.Actor;
import com.movie.spring.social.model.Film;

import com.movie.spring.social.model.FilmActor;
import com.movie.spring.social.repository.FilmSearchRepository;
import com.movie.spring.social.service.ActorService;
import com.movie.spring.social.service.FilmActorService;
import com.movie.spring.social.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Controller
public class SearchController {

    @Autowired
    private FilmSearchRepository filmSearchRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private FilmActorService filmActorService;

    @Autowired
    private FilmService filmService;

    @RequestMapping("/filmsearch")
    public String getSearchedFilms(@RequestParam("str") String searchByStr, Model model) {
        System.out.println("Full Text Search!");
        StringBuffer sb = null;
        StringBuffer result = new StringBuffer();
        try {
            List<List> searchResults = getFilmSearchRepository().search(searchByStr);
           // Set<Actor> actors = null;
            List<Film> films = searchResults.get(1);
            List<Actor> actors = searchResults.get(0);
            List<FilmActor> filmSrchActrs = getFilmActorService().getFilmActorObjects();
            List<FilmActor> foundFIlmActors = new ArrayList<>();

            for(int h = 0;h < foundFIlmActors.size();h++) {
               for (Actor a : actors){
                   if(foundFIlmActors.get(h).getActor_id().equals(a.getActor_id())){
                       continue;
                   }
                   foundFIlmActors.remove(h);
               }
            }


            Film film = null;
            FilmActor filmActor = null;
            Set<FilmActor> filmActors = null;
            for (int i = 0; i < films.size(); i++) {
                film = films.get(i);
                filmActors = (Set<FilmActor>) film.getFilmActors();
                Iterator<FilmActor> itr = filmActors.iterator();
                while (itr.hasNext()) {
                   // List<FilmActor> filmActByactId = getFilmActorService().getFilmActorsByFilmId(film.getFilm_id().toString());
                    filmActor = itr.next();
                    if(filmActor.getFilm().getFilm_id().equals(film.getFilm_id())) {
                        sb = new StringBuffer();
                        sb.append(" ").append(filmActor.getActor().getFirst_name()).append(" ").append(filmActor.getActor().getLast_name());
                        result.append(sb);
                    }
                }
                ((Film)films.get(i)).setActorName(new String(result.toString()));
                result = new StringBuffer();
            }

            model.addAttribute("films", films);
            System.out.println("Showing all films from Data Base");
            return "films";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public FilmSearchRepository getFilmSearchRepository() {
        return filmSearchRepository;
    }

    public void setFilmSearchRepository(FilmSearchRepository filmSearchRepository) {
        this.filmSearchRepository = filmSearchRepository;
    }

    public ActorService getActorService() {
        return actorService;
    }

    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    public FilmActorService getFilmActorService() {
        return filmActorService;
    }

    public void setFilmActorService(FilmActorService filmActorService) {
        this.filmActorService = filmActorService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }
}
