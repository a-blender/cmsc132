//STUDENT TESTS by Anna Blendermann
//Honor Pledge - I have not received help on this assignment
package tests;
import org.junit.*;

import submitserver.SubmitServer;
import static org.junit.Assert.*;

//STUDENT TESTS*************************************************************
//this junit class tests all the methods from the SubmitServer class
//and checks to see that the functionality is correct and bug-free
public class StudentTests 
{
	//TEST ADD SUBMISSIONS**************************************************
	@Test public void testAddSubmissions() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);
		server.addSubmission("Jesse Sophomore", 82);
		server.addSubmission("Travis Graduate", 67);
		server.addSubmission("Travis Graduate", 85);
		server.addSubmission("Theresa Terrapin", 0);
		server.addSubmission("Juan Undergraduate", 53);

		assertEquals(2, server.numSubmissions("Rose Freshman"));
		assertEquals(2, server.numSubmissions("Travis Graduate"));
		assertEquals(1, server.numSubmissions("Juan Undergraduate"));
	}

	//TEST NUM SUBMISSIONS**************************************************
	//tests the number of submissions made by a particular student
	@Test public void testNumSubmissions() 
	{
		SubmitServer testServer = new SubmitServer();
		testServer.addSubmission("Jenny Graduate", 72);
		testServer.addSubmission("Jenny Graduate", 90);
		testServer.addSubmission("Jenny Graduate", 85);
		testServer.addSubmission("Jackie Graduate", 0);
		testServer.addSubmission("Geronimo Graduate", -40);

		assertEquals(3, testServer.numSubmissions("Jenny Graduate"));
	}

	//TEST NUM SUBMISSIONS***************************************************
	//tests the total number of submissions to the server
	@Test public void testNumSubmissions2() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);
		server.addSubmission("Jesse Sophomore", 82);
		server.addSubmission("Travis Graduate", 67);
		server.addSubmission("Travis Graduate", 85);
		server.addSubmission("Theresa Terrapin", -72);

		assertEquals(5, server.numSubmissions());
	}

	//TEST BEST SUBMISSION NUMBER METHOD****************************************
	@Test public void testBestSubmissionNumber() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);
		server.addSubmission("Rose Freshman", 82);

		assertEquals(2, server.bestSubmissionNumber("Rose Freshman"));

		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);

		assertEquals(5, server.bestSubmissionNumber("Rose Freshman"));
	}

	//TEST BEST SUBMISSION SCORE METHOD*****************************************
	@Test public void testBestSubmissionScore() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 92);
		server.addSubmission("Rose Freshman", 82);

		assertEquals(92, server.bestSubmissionScore("Rose Freshman"));

		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);

		assertEquals(96, server.bestSubmissionScore("Rose Freshman"));
	}

	//TEST SATISFACTORY METHOD***************************************************
	@Test public void testSatisfactory() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);
		server.addSubmission("Jesse Sophomore", 82);
		server.addSubmission("Travis Graduate", 67);
		server.addSubmission("Travis Graduate", 85);
		server.addSubmission("Theresa Terrapin", 0);
		server.addSubmission("Juan Undergraduate", 53);

		assertTrue(server.satisfactory("Rose Freshman"));
		assertTrue(server.satisfactory("Travis Graduate"));
		assertFalse(server.satisfactory("Theresa Terrapin"));
		assertFalse(server.satisfactory("Gordon Batman"));
	}

	//TEST GOT EXTRA CREDIT METHOD**********************************************
	@Test public void testGotExtraCredit() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);
		server.addSubmission("Jesse Sophomore", 100);
		server.addSubmission("Travis Graduate", 67);
		server.addSubmission("Travis Graduate", 85);
		server.addSubmission("Theresa Terrapin", 100);
		server.addSubmission("Juan Undergraduate", 53);

		assertEquals(2, server.gotExtraCredit());
	}

	//TEST CHANGE SCORE METHOD***************************************************
	@Test public void testChangeScore() 
	{
		SubmitServer server = new SubmitServer();
		server.addSubmission("Rose Freshman", 75);
		server.addSubmission("Rose Freshman", 96);
		server.addSubmission("Jesse Sophomore", 100);
		server.addSubmission("Travis Graduate", 67);
		server.addSubmission("Travis Graduate", 85);
		server.addSubmission("Theresa Terrapin", 100);
		server.addSubmission("Juan Undergraduate", 53);

		assertEquals(85, server.bestSubmissionScore("Travis Graduate"));
		server.changeScore("Travis Graduate", 2, 90);
		assertEquals(90, server.bestSubmissionScore("Travis Graduate"));
	}
} //end of Junit test class
