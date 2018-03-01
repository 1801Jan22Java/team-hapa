package com.revature.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.revature.dao.*;
import com.revature.domain.Feedback;


@Component
public class FeedbackController {
	
	@RequestMapping(value = "/util/feedback", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String addFeedback(@RequestParam("userID") int userID,
								@RequestParam("message") String message) {
		FeedbackDao fd = new FeedbackDaoImpl();
		EndUserDao eud = new EndUserDaoImpl();
		Feedback feedback = new Feedback(eud.getEndUserById(userID), message);
		fd.addFeedback(feedback);
		
		Gson gson = new Gson();
		return gson.toJson(feedback);
	}
	
	
	/*
	 * Still needs to be sorted when queried
	 */
	@RequestMapping(value = "/util/admin/feedback", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getAllFeedback() {
		FeedbackDao fd = new FeedbackDaoImpl();
		List<Feedback> feedbackList = fd.getAllFeedback();

		Gson gson = new Gson();
		return gson.toJson(feedbackList);
	}
	
	
	@RequestMapping(value = "/util/admin/read", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String deleteFeedback(@RequestParam("feedbackID") int feedbackID) {
		FeedbackDao fd = new FeedbackDaoImpl();
		Feedback feedback = fd.getFeedbackById(feedbackID);
		fd.deleteFeedback(feedback);

		//Can simply return nothing if we want to.
		Gson gson = new Gson();
		return gson.toJson(feedback);
	}
}
