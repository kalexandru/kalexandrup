package com.movie.spring.social.service;

import com.movie.spring.social.users.SignupForm;
import com.movie.spring.social.model.User;
import com.movie.spring.social.users.UserEditForm;

public interface UserService {
	
	public abstract User signup(SignupForm signupForm);

	public abstract User findOne(long userId);

	public abstract void update(long userId, UserEditForm userEditForm);

}
