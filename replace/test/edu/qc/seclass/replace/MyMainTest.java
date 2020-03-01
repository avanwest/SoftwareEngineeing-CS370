package edu.qc.seclass.replace;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;
    private String usageStr = "Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is a test file for the replace utility\n" +
                "Let's make sure it has at least a few lines\n" +
                "so that we can create some interesting test cases...\n" +
                "And let's say \"howdy bill\" again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill,\n" +
                "This is another test file for the replace utility\n" +
                "that contains a list:\n" +
                "-a) Item 1\n" +
                "-b) Item 2\n" +
                "...\n" +
                "and says \"howdy Bill\" twice");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                "It is important to know your abc and 123," +
                "so you should study it\n" +
                "and then repeat with me: abc and 123");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

	    
	    // Test Cases -----------------------------------------
	   
	   /*
	     * Newly created test case:
	     * Test that a valid "-b" option by itself generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test1() throws Exception {

	        String args[] = {"-b",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that a valid "-f" option by itself generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test2() throws Exception {

	        String args[] = {"-f",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that a valid "-i" option by itself generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test3() throws Exception {

	        String args[] = {"-i",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that a valid "-l" option by itself generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test4() throws Exception {

	        String args[] = {"-l",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that an unknown option generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test5() throws Exception {

	        String args[] = {"-x",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that an empty file list seperator generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test6() throws Exception {

	        String args[] = {"--",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that a command with no arguments generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test7() throws Exception {

	        String args[] = {};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }


	    /*
	     * Newly created test case:
	     * Test that a valid file without the expected "from" and "to" generates a usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test8() throws Exception {
	        File file1 = createInputFile1();
	        String args[] = {"-- ", file1.getPath(),};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that a command without the "to" parameter generates a usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test9() throws Exception {
	        File file1 = createInputFile1();
	        String args[] = {"hello", "--", file1.getPath(),};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Newly created test case:
	     * Test that a command without the file separator generates the usage messsage.
	     * @throws Exception
	     */
	    @Test
	    public void test10() throws Exception {
	        File file1 = createInputFile1();
	        String args[] = {"hello", "bye", file1.getPath(),};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Implementation of test frame #1
	     * Test that a command without files generates the usage message.
	     * @throws Exception
	     */
	    @Test
	    public void test11() throws Exception {
	        File file1 = createInputFile1();
	        String args[] = {"hello", "bye", "--",};
	        Main.main(args);
	        assertEquals(usageStr, errStream.toString().trim());
	    }

	    /*
	     * Implementation of test frame #6
	     * Test that a file that doesn't exist generates the expected error message.
	     * @throws Exception
	     */
	    @Test
	    public void test12() throws Exception {

	        String args[] = {"a", "b", "--",  "DoesNotExist.txt",};
	        Main.main(args);
	        assertEquals("File does not exist: DoesNotExist.txt", errStream.toString().trim());
	    }

	    /*
	     * Implementation of test frame #2
	     * Test that a command with an empty file generates an error message.
	     * @throws Exception
	     */
	    @Test
	    public void test13() throws Exception {
	        File file1 = createTmpFile();
	        String args[] = {"hello", "bye", "--", file1.getPath()};
	        Main.main(args);
	        assertEquals("File is empty: " + file1.getPath(), errStream.toString().trim());
	    }


	    /*
	     *Implementation of test frame #3
	     * Test of Number of occurrences replaced is none.
	     * @throws Exception
	     */
	    @Test
	    public void test14() throws Exception {
	    	File inputFile = createInputFile1();
	    	String args[] = {"hello", "bye", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	    	String expected = inputFile.toString();
	    	String actual = getFileContent(inputFile.getPath());
	    	
	    	assertTrue(expected == actual);
	    }
	    
	    /*
	     *Implementation of test frame #19 
	     * Test length of string is longer than the file
	     */
	    @Test
	    public void test15() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);

	        fileWriter.write("short string file");
	    	
	    	String args[] = {"This string is longer than file string", "shortstring", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	    	fileWriter.close();
	    	
	    	assertEquals("From string is longer than file: " + inputFile.getPath(), errStream.toString().trim());
	    }
	    
	    /*
	     * Newly created test case:
	     * Test that spacing works as input/output.
	     * @throws Exception
	     */
	    @Test
	    public void test16() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);
	    	fileWriter.write("How are you doing?");
	    	
	        String args[] = {" ", "", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Howareyoudoing?";
	        String actual = getFileContent(inputFile.getPath());
	        fileWriter.close();
	        
	        assertEquals("Files differ", expected, actual);
	    }
    
	  
	    /*
	     *Implementation of test frame #4 
	     * Test that the first only is replaced. 
	     */
	    @Test
	    public void test17() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);
	    	fileWriter.write("I am the first.\n" + "First in line to the throne.");
	        String args[] = { "-f","first", "first born twin", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "I am the first born twin.\n"+
	                 		  "First in line to the throne.";
	        
	        fileWriter.close();

	        String actual = getFileContent(inputFile.getPath());
	        assertTrue(expected == actual);
	        
	    }
	    

	    /*
	     *Implementation of test frame #5 
	     * Test that last occurance only gets replaced.
	     */
	    @Test
	    public void test18() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);
	    	fileWriter.write("I am last in line.\n" + "But not last to leave.");
	        String args[] = { "-l","last", "the last", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "I am last in line.\n"+
	                 		  "But not the last to leave.";
	        
	        fileWriter.close();

	        String actual = getFileContent(inputFile.getPath());
	        assertTrue(expected == actual);
	        
	    }
	    
	    /*
	     *Implementation of test frame #7 
	     * Test when no options are defined.
	     */
	    @Test
	    public void test19() throws Exception {
	    	File inputFile = createInputFile1();
	        String args[] = { "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";

	        String actual = getFileContent(inputFile.getPath());
	        assertEquals(expected, actual);
	        
	    }
	    
	    /*
	     * Implementation of test frame #8
	     * Test when "-b" is not defined, a backup is not created. 
	     * @throws Exception
	     */
	    @Test
	    public void test20() throws Exception {

	    	File inputFile = createInputFile1();
	        String args[] = { "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";
	        
	        String actual = getFileContent(inputFile.getPath());
	        
	        assertEquals("files differ", expected, actual);
	        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	    }
	    
	    /*
	     * Implementation of test frame #9
	     * Test when "-f" is not defined, all occurrences get replaced not only first.  
	     * @throws Exception
	     */
	    @Test
	    public void test21() throws Exception {

	    	File inputFile = createInputFile1();
	        String args[] = { "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";
	        
	        String actual = getFileContent(inputFile.getPath());
	        
	        assertEquals(expected, actual);
	       
	    }
	    
	    /*
	     * Implementation of test frame #10
	     * Test when "-l" is not defined, all occurrences get replaced not only last
	     * @throws Exception
	     */
	    @Test
	    public void test22() throws Exception {

	    	File inputFile = createInputFile1();
	        String args[] = { "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";
	        
	        String actual = getFileContent(inputFile.getPath());
	        
	        assertEquals(expected, actual);
	       
	    }
	    
	    /*
	     * Implementation of test frame #11
	     * Test when "-i" is not defined, case insensitivity is turned off. 
	     * @throws Exception
	     */
	    @Test
	    public void test23() throws Exception {

	    	File inputFile = createInputFile1();
	        String args[] = { "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";
	        
	        String actual = getFileContent(inputFile.getPath());
	        
	        assertEquals(expected, actual);
	        
	    }
	    
	    /*
	     * Implementation of test frame #11
	     * Test when "-i" is not defined, case insensitivity is turned off. 
	     * @throws Exception
	     */
	    @Test
	    public void test24() throws Exception {

	    	File inputFile = createInputFile1();
	        String args[] = { "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";
	        
	        String actual = getFileContent(inputFile.getPath());
	        
	        assertEquals(expected, actual);
	        
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for "-b" and "-f"
	     * @throws Exception
	     */
	    @Test
	    public void test25() throws Exception {
	    	File inputFile = createInputFile1();
	        
	        String args[] = {"-b", "-f", "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Hello Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"howdy bill\" again!";
	               
	        String actual= getFileContent(inputFile.getPath());
	    
	        assertEquals("files differ", expected, actual);
	        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	      }
	    
	    
	    /*
	     * Newly created test case:
	     * Test for "-b" and "-l"
	     * @throws Exception
	     */
	    @Test
	    public void test26() throws Exception {
	    	File inputFile = createInputFile1();
	        
	        String args[] = {"-b", "-l", "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Howdy Bill,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!";
	               
	        String actual = getFileContent(inputFile.getPath());
	    
	        assertEquals("files differ", expected, actual);
	        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	      }
	    
	    /*
	     * Newly created test case:
	     * Test for "-b" and "-i"
	     * @throws Exception
	     */
	    @Test
	    public void test27() throws Exception {
	    	File inputFile = createInputFile2();
	        
	        String args[] = {"-b", "-i", "Howdy", "Hello", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Howdy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"Howdy Bill\" twice";
	               
	        String actual = getFileContent(inputFile.getPath());
	    
	        assertEquals("files differ", expected, actual);
	        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	      }
	    
	    /*
	     * Newly created test case:
	     * Test for "-f" and "-l"
	     * @throws Exception
	     */
	    @Test
	    public void test28() throws Exception {
	    	File inputFile = createInputFile1();

	        String args[] = {"-f","-l",  "Howdy", "Hello", "--", inputFile.getPath()};
	       
	        Main.main(args);

	        String expected = "Hello William,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"howdy bill\" again!";    

	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals("files differ", expected, actual);

	        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for "-f" and "-i"
	     * @throws Exception
	     */
	    @Test
	    public void test29() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);
	    	fileWriter.write("ChECk one two, CHECK one two");
	        String args[] = { "-f", "i", "check", "Test", "--", inputFile.getPath()};
	        Main.main(args);

	        String expected = "Test one two, CHECK one two";
	        String actual = getFileContent(inputFile.getPath());
	        assertEquals("files differ", expected, actual);
	        
	        fileWriter.close();
	        
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for "-f" "-l" "-i"
	     * @throws Exception
	     */
	    @Test
	    public void test30() throws Exception {
	    	File inputFile = createInputFile1();

	        String args[] = {"-f","-l", "-i", "Howdy", "Hello", "--", inputFile.getPath()};
	       
	        Main.main(args);

	        String expected = "Hello Bill,\n" +  
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!"; 

	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals(expected, actual);
	 

	    }
	    
	    
	    /*
	     * Newly created test case:
	     * Test for "-b" "-l" "-i"
	     * @throws Exception
	     */
	    @Test
	    public void test31() throws Exception {
	   	 
	    	File inputFile= createInputFile1();
	     
	    	String args[] = {"-b","-l", "-i", "Howdy", "Hello", "--", inputFile.getPath()};
		       
	        Main.main(args);

	        String expected = "Hello Bill,\n" +  
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!"; 

	     String actual = getFileContent(inputFile.getPath());
	     
	     assertEquals("The files differ!", expected, actual);
	     
	     assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	   
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for "-b", "-f","-l", "-i"
	     * @throws Exception
	     */
	    @Test
	    public void test32() throws Exception {
	        
	    	File inputFile = createInputFile1();

	        String args[] = {"-b", "-f","-l", "-i", "Howdy", "Hello", "--", inputFile.getPath()};
	       
	        Main.main(args);

	        String expected = "Hello Bill,\n" +    
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!"; 

	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals("The files differ!", expected, actual);
	 
	        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for special character
	     * @throws Exception
	     */
	    @Test
	    public void test33() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);

	        fileWriter.write("The chicken & the egg");
	    	
	    	String args[] = {"&", "and", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	    	fileWriter.close();

	        String expected = "The chicken and the egg";
	        
	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals(expected, actual);
	    	
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for pattern
	     * @throws Exception
	     */
	    @Test
	    public void test34() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);

	        fileWriter.write("(**)");
	    	
	    	String args[] = {"(**)", "(pair of stars)", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	    	fileWriter.close();

	        String expected = "(pair of stars)";
	        
	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals(expected, actual);
	    	
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for absurdly long random string deletion with space
	     * @throws Exception
	     */
	    @Test
	    public void test35() throws Exception {
	    	File inputFile = createTmpFile();
	    	FileWriter fileWriter = new FileWriter(inputFile);

	        fileWriter.write("This should dksajoaidshgoaihdg work fine.");
	    	
	    	String args[] = {"dksajoaidshgoaihdg", "", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	    	fileWriter.close();

	        String expected = "This should work fine.";
	        
	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals(expected, actual);
	    	
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for turning numbers to alphabet representation.
	     * @throws Exception
	     */
	    @Test
	    public void test36() throws Exception {
	    	File inputFile = createInputFile3();
	    	
	    	String args[] = {"123", "onetwothree", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	        String expected = "Howdy Bill, have you learned your abc and onetwothree?\n" +
	                "It is important to know your abc and onetwothree," +
	                "so you should study it\n" +
	                "and then repeat with me: abc and onetwothree";
	        
	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals(expected, actual);
	    }
	    
	    
	    /*
	     * Implementation of test frame #24
	     * Number of strings more than two input
	     * @throws Exception
	     */
	    @Test
	    public void test37() throws Exception {
	    	File inputFile = createInputFile3();
	    	
	    	String args[] = {"Howdy", "then repeat", "sing", "--", inputFile.getPath()};
	    	Main.main(args);
	    
	    	assertEquals(usageStr, errStream.toString().trim());
	    }
	    
	    /*
	     * Implementation of test frame #13
	     * Length of string is 1
	     * @throws Exception
	     */
	    @Test
	    public void test38() throws Exception {
	    	File inputFile = createInputFile1();
	    	
	    	String args[] = {"a", "aaa", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	        String expected = "Hello Bill,\n" +    
	                "This is aaa test file for the replace utility\n" +
	                "Let's make sure it has at least aaa few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"Hello bill\" again!"; 
	        
	        String actual = getFileContent(inputFile.getPath());
	       
	        assertEquals(expected, actual);
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for "-f" and "-f", duplicate options
	     * @throws Exception
	     */
	    @Test
	    public void test39() throws Exception {
	    	File inputFile = createInputFile1();
	    	
	    	String args[] = {"-f", "-f", "a", "aaa", "--", inputFile.getPath()};
	    	Main.main(args);
	    	
	    	assertEquals("Error, duplicate option: " + usageStr, errStream.toString().trim());
	    }
	    
	    /*
	     * Newly created test case:
	     * Test for writing to two files correctly
	     * @throws Exception
	     */
	    @Test
	    public void test40() throws Exception {
	    	File inputFile1 = createInputFile1();
	    	File inputFile2 = createInputFile2();
	    	

	        String args[] = {"Bill", "William", "--", inputFile1.getPath(), inputFile2.getPath()};
	       
	        Main.main(args);

	        String expected = "Howdy William,\n" +
	                "This is a test file for the replace utility\n" +
	                "Let's make sure it has at least a few lines\n" +
	                "so that we can create some interesting test cases...\n" +
	                "And let's say \"howdy William\" again!";  
	        
	        String expected2="Howdy William,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy William\" twice";  

	        String actual = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	       
	        assertEquals(expected, actual);
	        assertEquals(expected2, actual2);

	    }
	    
}
