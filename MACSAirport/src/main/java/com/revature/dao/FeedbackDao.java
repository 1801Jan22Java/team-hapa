package com.revature.dao;

import com.revature.domain.Feedback;

public interface FeedbackDao {
	public Feedback getFeedbackById(int id);
	public int addFeedback(Feedback thisFeedback);
	
}
