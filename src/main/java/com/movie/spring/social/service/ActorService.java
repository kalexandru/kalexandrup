package com.movie.spring.social.service;

import com.movie.spring.social.repository.ActorRepository;
import com.movie.spring.social.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleksandr.kushnir on 03.11.2016.
 */
@Service("actorService")
public class ActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorService.class);

    @Autowired
    private ActorRepository actorRepository;

    public List getActorObjects() {
        return getActorRepository().retrieveActors();
    }

    public List getActorById(String id){
        return getActorRepository().searchActorsByActorId(id);
    }
    public ActorRepository getActorRepository() {
        return actorRepository;
    }

    public void setActorRepository(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
}
