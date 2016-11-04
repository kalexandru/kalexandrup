package com.movie.spring.social.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.movie.spring.social.model.User;
import org.hibernate.validator.constraints.Email;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;

public class SignupForm {
	
	@NotNull
	@Size(min=1, max=255)
	@Email
	private String email;
	
	@NotNull
	@Size(min=1, max=100, message="{nameSizeError}")
	private String name;
	
	@NotNull
	@Size(min=1, max=30)
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignupForm [email=" + email + ", name=" + name + ", password="
				+ password + "]";
	}
	
	public static SignupForm fromConnection(Connection<?> connection) {
		
        SignupForm form = new SignupForm();
        
        if (connection != null) {
//			Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
			Facebook facebook = (Facebook) connection.getApi();
			String [] fields = { "id", "email","name"};
			//User userProfile = facebook.fetchObject("me", User.class, fields);
			//facebook.userOperations().getUserProfile();

			User userProfile = facebook.fetchObject("me", User.class, fields);
			System.out.println(userProfile.getEmail()+" "+userProfile.getName());
			//UserProfile socialMediaProfile = connection.fetchUserProfile();
            form.setEmail(userProfile.getEmail());
            form.setName(userProfile.getName());
//			+ " " + socialMediaProfile.getLastName()
        }
 
        return form;
	}
	
}
