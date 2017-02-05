package com.priya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.priya.dao.UserProfileDAO;
import com.priya.model.UserProfiles;

@Service
public class UserProfileService {

	@Autowired	
	private UserProfileDAO userProfileDAO;
	public void setUserProfileDAO(UserProfileDAO userProfileDAO) {
		this.userProfileDAO = userProfileDAO;
	}
	
	
	@Transactional
	public UserProfiles get(String id) {
		return this.userProfileDAO.get(id);
	}

	
	@Transactional
	public void add(UserProfiles profile) {
		this.userProfileDAO.add(profile);
	}

	
	@Transactional
	public void remove(UserProfiles profile) {		
		this.userProfileDAO.remove(profile);
	}


}
