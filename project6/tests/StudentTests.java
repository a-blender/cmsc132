//STUDENT TESTS by Anna Blendermann
package tests;

import org.junit.*;

import submitserver.Submission;
import submitserver.SubmitServer;
import static org.junit.Assert.*;

public class StudentTests 
{
	//tests submitServer constructor by adding invalid numProjects
	@Test public void testPublic1() 
	{
		SubmitServer server= new SubmitServer(-3);

		server.addSubmission("George Straight", 2, 85);
		server.addSubmission("George Straight", -3, 72);

		assertEquals(0, server.numSubmissions());    	
	}

	//tests addSubmission() for duplicate submissions
	@Test public void testPublic2() 
	{
		SubmitServer server= new SubmitServer(2);

		server.addSubmission("Joe Brackster", 1, 85);
		server.addSubmission("Joe Brackster", 1, 85);
		server.addSubmission("Polly Pocket", 2, 79);

		assertEquals(3, server.numSubmissions()); 
	}

	//tests addSubmission() with set of submissions
	@Test public void testPublic3() 
	{
		SubmitServer server= new SubmitServer(5);

		server.addSubmission("Anna Blender", 1, 82);
		server.addSubmission("Chris Blender", 3, 90);
		server.addSubmission("Mary Blender", 2, 86);
		server.addSubmission("Katie Blender", 2, 83);
		assertEquals(4, server.numSubmissions());

		server.addSubmission("Martin Blender", 3, 100);
		server.addSubmission("Frances Blender", 3, 94);
		assertEquals(6, server.numSubmissions());
	}

	//tests addSubmission() for set of 1,000 submissions
	@Test public void testPublic4() 
	{
		SubmitServer server= new SubmitServer(1500);

		for(int x=0; x<=1000; x++)
			server.addSubmission("Cyrus Chesnut", x, 75);

		assertEquals(1000, server.numSubmissions());		
	}

	//tests addSubmission with invalid submissions
	@Test public void testPublic5() 
	{
		SubmitServer server= new SubmitServer(3);

		server.addSubmission("Joey Carpenter", 0, 82);
		server.addSubmission("Bertha Terpstein", 4, 75);

		server.addSubmission("Andrew Blackman", 3, -68);
		server.addSubmission("Andrew Blackman", 3, 102);
		assertEquals(0, server.numSubmissions());
	}


	//test numSubmissions() for two target student/projects
	@Test public void testPublic6() 
	{
		SubmitServer server= new SubmitServer(2);

		server.addSubmission("Rose Gilligan", 1, 82);
		server.addSubmission("Rose Gilligan", 1, 85);
		server.addSubmission("Violet Rider", 2, 75);

		assertEquals(1, server.numSubmissions("Violet Rider", 2));
		assertEquals(2, server.numSubmissions("Rose Gilligan", 1));
	}

	//test numSubmissions for invalid target student/project
	@Test public void testPublic7() 
	{
		SubmitServer server= new SubmitServer(3);

		server.addSubmission("Rose Gilligan", 1, 82);
		server.addSubmission("Tara Mondera", 3, 85);
		server.addSubmission("Violet Rider", 2, 75);

		assertEquals(1, server.numSubmissions("Violet Rider", 2));
		assertEquals(0, server.numSubmissions("Violet Rider", -2));
		assertEquals(0, server.numSubmissions("Violet Rider", 4));
	}

	//tests numSubmissions() for target project 2
	@Test public void testPublic8() 
	{
		SubmitServer server= new SubmitServer(3);

		server.addSubmission("Katrina Blender", 1, 81);
		server.addSubmission("Anna Blender", 2, 82);
		server.addSubmission("Andrea Blender", 2, 86);
		server.addSubmission("Christy Blender", 2, 70);

		assertEquals(3, server.numSubmissions(2));
	}

	//tests numSubmissions() for target project on empty server
	@Test public void testPublic9() 
	{
		SubmitServer server = new SubmitServer(4);

		assertEquals(0, server.numSubmissions(1));
		assertEquals(0, server.numSubmissions(2));

		server.addSubmission("Karen Gilligan", 4, 67);
		server.addSubmission("Melody Wingman", 4, 77);
		assertEquals(2, server.numSubmissions(4));
	}

	//tests numSubmissions() for particular target student
	@Test public void testPublic10() 
	{
		SubmitServer server= new SubmitServer(4);

		server.addSubmission("Anna Blender", 2, 80);
		server.addSubmission("Christy Blender", 4, 80);
		server.addSubmission("Anna Blender", 2, 80);
		server.addSubmission("Christy Blender", 4, 90);

		assertEquals(2, server.numSubmissions("Anna Blender"));
		assertEquals(2, server.numSubmissions("Christy Blender"));
	}

	//tests numSubmissions() for target student on empty server
	@Test public void testPublic11() 
	{
		SubmitServer server = new SubmitServer(1);

		server.addSubmission("Thomas King", 1,95);
		server.addSubmission("Rose Braveheart", 1, 96);

		assertEquals(1, server.numSubmissions("Thomas King"));
		assertEquals(0, server.numSubmissions("snake"));
		assertEquals(0, server.numSubmissions("koala345"));
		assertEquals(0, server.numSubmissions(null)); //null check
	}

	//TESTING BEST SUBMISSION NUMBER NOW*********************************

	//tests bestSubmissionNumber() for server with three scores
	@Test public void testPublic12() 
	{
		SubmitServer server = new SubmitServer(2);

		server.addSubmission("Singwu", 1,95);
		server.addSubmission("Singwu", 2, 92);
		server.addSubmission("Singwu", 2, 96);

		assertEquals(1, server.bestSubmissionNumber("Singwu", 1));
		assertEquals(3, server.bestSubmissionNumber("Singwu", 2));
	}

	//tests bestSubmissionNumber() for server with six scores
	@Test public void testPublic13() 
	{
		SubmitServer server = new SubmitServer(6);

		server.addSubmission("Tiger", 1,67);
		server.addSubmission("Tiger", 1,82);
		server.addSubmission("Tiger", 2, 79);

		server.addSubmission("Violet", 2, 85);
		server.addSubmission("Violet", 2, 86);
		server.addSubmission("Violet", 1, 90);

		assertEquals(2, server.bestSubmissionNumber("Tiger", 1));
		assertEquals(2, server.bestSubmissionNumber("Violet", 2));
	}

	//tests bestSubmissionNumber() for duplicate scores
	@Test public void testPublic14() 
	{
		SubmitServer server = new SubmitServer(2);

		server.addSubmission("Martha", 1,82);
		server.addSubmission("Martha", 1,82);
		server.addSubmission("Martha", 1, 82);
		server.addSubmission("Martha", 1, 78);

		assertEquals(3, server.bestSubmissionNumber("Martha", 1));
	}

	//tests bestSubmissionNumber() for invalid params
	@Test public void testPublic15() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Grace", 2,82);
		server.addSubmission("Rose", 3,82);
		server.addSubmission("Anna", 5, 82);

		assertEquals(0, server.bestSubmissionNumber("Rose", -2));
		assertEquals(0, server.bestSubmissionNumber("Anna", 56));

		assertEquals(0, server.bestSubmissionNumber("Joey", 2));
		assertEquals(0, server.bestSubmissionNumber("", 2));
		assertEquals(0, server.bestSubmissionNumber(null, 2));
	}

	//TESTING BEST SUBMISSION SCORE NOW**********************************

	//tests bestSubmissionScore() for server with three scores
	@Test public void testPublic16() 
	{
		SubmitServer server = new SubmitServer(2);

		server.addSubmission("Singwu", 1,95);
		server.addSubmission("Singwu", 2, 92);
		server.addSubmission("Singwu", 2, 96);

		assertEquals(95, server.bestSubmissionScore("Singwu", 1));
		assertEquals(96, server.bestSubmissionScore("Singwu", 2));
	}

	//tests bestSubmissionScore() for server with five scores
	@Test public void testPublic17() 
	{
		SubmitServer server = new SubmitServer(6);

		server.addSubmission("Tiger", 1,67);
		server.addSubmission("Tiger", 1,82);
		server.addSubmission("Tiger", 2, 79);
		server.addSubmission("Violet", 2, 86);
		server.addSubmission("Violet", 2, 85);

		assertEquals(82, server.bestSubmissionScore("Tiger", 1));
		assertEquals(86, server.bestSubmissionScore("Violet", 2));
	}

	//tests bestSubmissionScore() for duplicate scores
	@Test public void testPublic18() 
	{
		SubmitServer server = new SubmitServer(2);

		server.addSubmission("turtle", 1, 67);
		server.addSubmission("turtle", 1, 82);
		server.addSubmission("turtle", 1, 82);
		server.addSubmission("robin", 2, 79);
		server.addSubmission("robin", 2, 79);

		assertEquals(82, server.bestSubmissionScore("turtle", 1));
		assertEquals(79, server.bestSubmissionScore("robin", 2));
	}

	//tests bestSubmissionScore() for invalid params
	@Test public void testPublic19() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Grace", 2, 82);
		server.addSubmission("Rose", 3, 82);
		server.addSubmission("Anna", 5, 82);

		assertEquals(0, server.bestSubmissionScore("Joey", 2));
		assertEquals(0, server.bestSubmissionScore("Bryan", 2));

		assertEquals(-1, server.bestSubmissionScore("", 2));
		assertEquals(-1, server.bestSubmissionScore("Rose", -5));
		assertEquals(-1, server.bestSubmissionScore("Rose", 42));
	}

	//FINISHED TESTING BEST NUMBER/SCORE METHODS*************************

	//tests Satisfactory() for server with two valid scores
	@Test public void testPublic20() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Jenna", 2, 92);
		server.addSubmission("Katheryn", 3, 81);

		assertTrue(server.satisfactory("Jenna", 2));
		assertTrue(server.satisfactory("Katheryn", 3));
	}

	//tests Satisfactory for server with more valid scores
	@Test public void testPublic21() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Anna", 2, 80);
		server.addSubmission("Nikki", 2, 80);
		server.addSubmission("George", 3, 67);

		assertTrue(server.satisfactory("Anna", 2));
		assertTrue(server.satisfactory("George", 3));
	}

	//tests Satisfactory() for students with scores of zero
	@Test public void testPublic22() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Andrea", 2, 0);
		server.addSubmission("Andrea", 2, 0);
		server.addSubmission("Marilyn", 4, 81);

		assertFalse(server.satisfactory("Marilyn", 3));
		assertTrue(server.satisfactory("Marilyn", 4));
		assertFalse(server.satisfactory("Andrea", 2));
	}

	//tests Satisfactory() for invalid parameters
	@Test public void testPublic23() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Andrea", 2, 0);
		server.addSubmission("Marilyn", 4, 50);

		assertFalse(server.satisfactory("", 3));
		assertFalse(server.satisfactory(null, 2));
		assertFalse(server.satisfactory("Andrea", -3));
		assertFalse(server.satisfactory("Andrea", 100));
	}

	//tests gotExtraCredit() for two confirmed students
	@Test public void testPublic24() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Thomas", 1, 100);
		server.addSubmission("Timothy", 2, 100);

		assertTrue(server.gotExtraCredit("Thomas", 1));
		assertTrue(server.satisfactory("Timothy", 2));
	}

	//tests gotExtraCredit for students who didn't get credit
	@Test public void testPublic25() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Joey", 3, 65);
		server.addSubmission("Joey", 3, 100);

		assertFalse(server.gotExtraCredit("Joey", 3));
	}

	//tests gotExtraCredit for invalid parameters
	@Test public void testPublic26() 
	{
		SubmitServer server = new SubmitServer(5);
		server.addSubmission("Andrew", 5, 97);

		assertFalse(server.gotExtraCredit("Andrew", 2));
		assertFalse(server.gotExtraCredit(null, 2));
		assertFalse(server.gotExtraCredit("", 2));

		assertFalse(server.gotExtraCredit("Corea", 1));
		assertFalse(server.gotExtraCredit("Annabelle", -5));
		assertFalse(server.gotExtraCredit("Annabelle", 102));
	}

	//NOW TESTING CHANGE SCORE*******************************************

	//tests changeScore() by changing two different scores
	@Test public void testPublic27() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Andrew", 2, 80);
		server.addSubmission("Andrew", 2, 92);
		server.addSubmission("Andrew", 2, 86);
		assertEquals(92, server.bestSubmissionScore("Andrew", 2));

		assertTrue(server.changeScore("Andrew", 2, 2, 72));
		assertTrue(server.changeScore("Andrew", 2, 3, 75));
		assertEquals(80, server.bestSubmissionScore("Andrew", 2));
	}

	//tests changeScore() for student that doesn't exist
	@Test public void testPublic28() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Andrew", 2, 80);
		server.addSubmission("Drew", 3, 81);

		assertFalse(server.changeScore("Augustus", 2, 1, 72));
		assertFalse(server.changeScore("waters1452", 2, 1, 75));
	}

	//tests changeScore() for changing duplicate scores
	@Test public void testPublic29() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Toad", 1, 92);
		server.addSubmission("Frog", 1, 95);

		assertFalse(server.changeScore("Toad", 1, 1, 92));
		assertTrue(server.changeScore("Frog", 1, 1, 92));
	}

	//tests changeScore() for invalid name/project number
	@Test public void testPublic30() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Toad", 2, 92);
		server.addSubmission("Frog", 2, 95);

		assertFalse(server.changeScore("berkleybah", 2, 1, 80));
		assertFalse(server.changeScore("sundance", 2, 1, 80));

		assertFalse(server.changeScore("Frog", 3, 1, -20));
		assertFalse(server.changeScore("Frog", 12, 1, 80));
	}

	//tests changeScore() for target student, wrong submission
	@Test public void testPublic31() 
	{
		SubmitServer server = new SubmitServer(5);

		server.addSubmission("Toad", 2, 92);
		server.addSubmission("Frog", 2, 95);

		//False - Toad never had a second or third submission
		assertFalse(server.changeScore("Toad", 2, 2, 88));
		assertFalse(server.changeScore("Toad", 2, 3, 88));
	}

	//tests setProjectnumber() and setProjectScore()
	@Test public void testPublic32() 
	{
		Submission object = new Submission(2, 92);

		object.setProjectNumber(4);
		object.setProjectScore(80);
		
		assertEquals(4, object.getProjectNumber());
		assertEquals(80, object.getProjectScore());
	}

} //end of studentTests class
