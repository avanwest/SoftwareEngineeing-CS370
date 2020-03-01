package edu.qc.seclass;

public class BuggyClass {

	public BuggyClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/* Task 1 -  Statement Coverage
	 *  
	 * 	1) 100% statement coverage and does NOT reveal the fault
	 * 	2) Less than 50% statement coverage and reveals fault. 
	 */
	
	public static int buggyMethod1(int x) {
		int y = 1, z = 2;
		if (x >= 1) {
			y = x - 1;
		} else {
			y = 2;
		}
		return z/y;
	}
	
	
	/* Task 2 - Statement / Branch Coverage
	 *  
	 * 	1) 100% statement coverage and does NOT reveal the fault
	 * 	2) Every test suite that achieves more than 50% branch coverage reveals the fault.  
	 */
	public static int buggyMethod2(int x) {
		int y = 2, z = 2;
		if (x >= 0) {
			y = x - 2;
		} else if (x < 0) {
			y = 2;
		}
		return z/y;
	}
	
	/* Task 3 - Branch / Statement x Branch Coverage
	 *  
	 * 	1) 100% branch coverage and does NOT reveal the fault
	 * 	2) 100% statement coverage, does not achieve 100% branch and reveals the fault. 
	 */
	public static void buggyMethod3(int x) {
		
		// 100% Branch coverage does not guarantee you will find the fault.
		// However because Branch coverage has a higher hierarchy than Statement coverage it's not possible to find the fault with 100% Statement while not finding it with Branch as well. 
		
	}
	
	/* Task 4 - Statement / Branch
	 *  
	 * 	1) 100% statement coverage and reveals the fault.
	 * 	2) 100% branch coverage and does not reveal the fault.  
	 */
	public static void buggyMethod4(int x) {
		
		// Similar to method 3 above: Branch coverage has a higher hierarchy, meaning that it's not possible to have branch coverage that doesn't find the fault that statement coverage did. 
		
		
	}
	
	/* Task 5 - Statement / Statement x Branch Coverage
	 *  
	 * 	1) 100% statement coverage and reveals the fault.
	 * 	2) The division by zero fault at line 4 cannot be revealed by any test suite.  
	 */
	public static void buggyMethod5(int i) {
		
		/*
		int x;
		// add code here
		x = i/0;
		//add code here
		 */
		
		// Not possible to have a 100% statement coverage with division by zero at line 4. It would be revealed by all tests.
	}
		
		
		
	

}
