package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuggyClassTestSC1a {
	BuggyClass buggy;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		buggy = new BuggyClass();
	}

	@AfterEach
	void tearDown() throws Exception {
		buggy = null;
	}

	@Test
	public void testBuggyMethod1A() {
		assertEquals(1, buggy.buggyMethod1(3));
	}
	
	@Test
	public void testBuggyMethod1B() {
		assertEquals(1, buggy.buggyMethod1(0));
	}

}
