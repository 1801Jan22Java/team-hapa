package com.revature.tests;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

import com.revature.dao.*;
import com.revature.domain.*;

public class StateDaoTests {

	@Test
	final public void testGetStateByName() {
		State state = new StateDaoImpl().getStateByName("California");
		Assert.assertTrue(state != null && state.getName().equals("California"));
	}
	
	@Test
	final public void testAddState() {
		StateDao sd = new StateDaoImpl();
		String name = "Fifty-first";
		sd.addState(new State(name));
		State state = sd.getStateByName(name);
		Assert.assertTrue(state != null && state.getName().equals(name));
	}
	
}
