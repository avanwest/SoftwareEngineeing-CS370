package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuggyClassTestSC2 {
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
	void testBuggyMethod2A() {
		assertEquals(1, buggy.buggyMethod2(4));
	}
	
	@Test
	void testBuggyMethod2B() {
		assertEquals(1, buggy.buggyMethod2(-1));
	}

}
