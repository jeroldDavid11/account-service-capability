package com.serviceaccount.security.resources;

import org.springframework.security.core.userdetails.User;

import com.serviceaccount.security.model.UserEntity;

public class CustomUser extends User {
	   private static final long serialVersionUID = 1L;
	   public CustomUser(UserEntity user) {
	      super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
	   }
	} 
