package com.revature.tests;

import org.junit.Test;

import java.util.Random;

import org.junit.Assert;

import com.revature.dao.*;
import com.revature.domain.*;

public class FeedbackDaoTests {

	@Test
	final public void testAddFeedback() {
		FeedbackDao fdi = new FeedbackDaoImpl();
		String rand = ((Integer) new Random().nextInt(100000000)).toString();
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser u = eudi.getEndUserByEmail("blah@blah.com");
		Feedback fb = new Feedback(u, rand);
		fdi.addFeedback(fb);
		boolean resultant = false;
		for (Feedback f : fdi.getAllFeedback()) {
			resultant |= f.getMessage().equals(rand);
		}
		Assert.assertTrue(resultant);
	}
	
	@Test
	final public void testGetAllFeedback() {
		// Tested by other functions
	}
	
	@Test
	final public void testDeleteFeedback() {
		FeedbackDao fdi = new FeedbackDaoImpl();
		String rand = ((Integer) new Random().nextInt(100000000)).toString();
		EndUserDao eudi = new EndUserDaoImpl();
		EndUser u = eudi.getEndUserByEmail("blah@blah.com");
		Feedback fb = new Feedback(u, rand);
		fdi.addFeedback(fb);
		fdi.deleteFeedback(fb);
		boolean resultant = true;
		for (Feedback f : fdi.getAllFeedback()) {
			resultant &= !f.getMessage().equals(rand);
		}
		Assert.assertTrue(resultant);
	}

}
