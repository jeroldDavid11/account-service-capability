package com.serviceaccount.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.serviceaccount.security.model.UserEntity;
import com.serviceaccount.security.repo.UsersDBQuery;
import com.serviceaccount.security.resources.CustomUser;



@Service
public class CustomDetailsService implements UserDetailsService {
	   @Autowired
	   UsersDBQuery usersDBQuery ;

	   @Override
	   public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
	      UserEntity userEntity = null;
	      try {
	         userEntity = usersDBQuery.getUserDetails(username);
	         CustomUser customUser = new CustomUser(userEntity);
	         return customUser;
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new UsernameNotFoundException("User " + username + " was not found in the database");
	      }
	   }
	} 