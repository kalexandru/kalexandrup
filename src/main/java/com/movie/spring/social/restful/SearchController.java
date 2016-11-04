package com.movie.spring.social.restful;

import com.movie.spring.social.model.Actor;
import com.movie.spring.social.model.Film;
import com.movie.spring.social.model.FilmActor;
import com.movie.spring.social.repository.FilmSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Controller
public class SearchController {

    @Autowired
    FilmSearchRepository filmSearchRepository;

    @RequestMapping("/filmsearch")
    public String getSearchedFilms(@RequestParam("str") String searchByStr, Model model) {
        System.out.println("Full Text Search!");
        StringBuffer sb = null;
        StringBuffer result = new StringBuffer();
        try {

            List<Film> films = filmSearchRepository.search(searchByStr);
            List<FilmActor> filmActors = new ArrayList<>();
            FilmActor filmActor = null;
            Actor actor = null;
            for (int i = 0; i < films.size(); i++) {
                filmActors = (List<FilmActor>) films.get(i).getFilmActors();
                Iterator<FilmActor> itr = filmActors.iterator();
                while (itr.hasNext()) {
                    filmActor = itr.next();
                    actor = filmActor.getActor();
                    sb = new StringBuffer();
                    sb.append(" ").append(actor.getFirst_name()).append(" ").append(actor.getLast_name());
                    result.append(sb);
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

}
