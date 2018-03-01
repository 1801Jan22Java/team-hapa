package com.revature.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.revature.dao.CommonLookupDao;
import com.revature.dao.CommonLookupDaoImpl;
import com.revature.dao.EndUserDao;
import com.revature.dao.EndUserDaoImpl;
import com.revature.domain.CommonLookup;
import com.revature.domain.EndUser;

@Controller
public class EndUserController {
	
	
	//Returns the EndUser who just registered their account, parsed into JSON
	@RequestMapping(value = "/util/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String registerAccount(@RequestParam("firstName") String firstName,
									@RequestParam("lastName") String lastName,
									@RequestParam("email") String email,
									@RequestParam("password") String password,
									@RequestParam("type") String type,
									@RequestParam("answer1") String answer1,
									@RequestParam("answer2") String answer2,
									@RequestParam("answer3") String answer3
									) {

		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUserDao eudi = new EndUserDaoImpl();
		CommonLookup cl1;
		if(type.equals("Passenger")) {
			cl1 = cldi.getCommonLookupByName("END_USER_TYPE", "Passenger");
		}
		else {
			cl1 = cldi.getCommonLookupByName("END_USER_TYPE", "Employee");
		}
		EndUser newUser = new EndUser(firstName, lastName, email, password, cl1,
				answer1, answer2, answer3);
		
		eudi.addEndUser(newUser);
		
		Gson gson = new Gson();
		return gson.toJson(newUser);
	}
	
	/*
	 * 
	 */
	@RequestMapping(value = "/util/profile", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String profile(@RequestParam("userID") int userID,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password
			) {

		
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser user = eudi.getEndUserById(userID);
		
		//Change info if fields aren't blank
		if(!firstName.equals("")) {
			user.setFirstname(firstName);
		}
		if(!lastName.equals("")) {
			user.setLastname(lastName);
		}
		if(!email.equals("")) {
			user.setEmail(email);
		}
		if(!password.equals("")) {
			user.setPassword(password);
		}
		
		eudi.updateEndUser(user);
		
		Gson gson = new Gson();
		return gson.toJson(user);
	}
	
	
	@RequestMapping(value = "/util/reset", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String profile(@RequestParam("email") String email,
			@RequestParam("firstAnswer") String answer1,
			@RequestParam("secondAnswer") String answer2,
			@RequestParam("thirdAnswer") String answer3,
			@RequestParam("password") String password) {

		
		CommonLookupDao cldi = new CommonLookupDaoImpl();
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser user = eudi.getEndUserByEmail(email);
		
		//Change info if fields aren't blank
		if(user.getSecretAnswer1().equals(answer1) &&
				user.getSecretAnswer2().equals(answer2) &&
				user.getSecretAnswer3().equals(answer3)) {
			user.setPassword(password);
		}
		
		eudi.updateEndUser(user);
		
		Gson gson = new Gson();
		return gson.toJson(user);
	}
	
	
	@RequestMapping(value = "/util/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String login(@RequestParam("email") String email,
						@RequestParam("password") String password) {
		//Pending the format of the JSON object we'll be returning.
		return null;
	}
	
	
	/*
	 * Pending our decision on session management
	 */
	@RequestMapping(value = "/util/logout", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String logout() {
		return null;
	}
	
	
	@RequestMapping(value = "/util/admin/nofly", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String noFly(@RequestParam("userID") int userID) {
		EndUserDao eud = new EndUserDaoImpl();
		EndUser user = eud.getEndUserById(userID);
		
		user.setNoFly(true);
		eud.updateEndUser(user);
		
		Gson gson = new Gson();
		return gson.toJson(user);
	}
}
