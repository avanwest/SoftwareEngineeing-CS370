package edu.qc.seclass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuggyClassTestSC1b {
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
	void testBuggyMethod1() {
		assertEquals(0, buggy.buggyMethod1(1));
	}

}
