package com.movie.spring.social.service;

import com.movie.spring.social.repository.CategoryRepository;
import com.movie.spring.social.repository.FilmCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by oleksandr.kushnir on 02.11.2016.
 */
@Service("categoryService")
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List getCategoryObjects() {
        return getCategoryRepository().retrieveCategories();
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
