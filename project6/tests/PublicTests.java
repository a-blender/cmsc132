package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import submitserver.SubmitServer;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests adding one submission to a SubmitServer, then calling
  // numSubmissions() to check the total number of submissions made.
  @Test public void testPublic1() {
    SubmitServer server= new SubmitServer(1);

    server.addSubmission("Leanne Llama", 1, 74);

    assertEquals(1, server.numSubmissions());
  }

  // Tests adding several submissions to a SubmitServer, all for the same
  // student and the same project, then calling numSubmissions() to check
  // the number of submissions made for that student.
  @Test public void testPublic2() {
    SubmitServer server= exampleSubmitServer();

    assertEquals(4, server.numSubmissions("Julie Junior", 1));
  }

  // Tests adding several submissions to a SubmitServer, all for the same
  // student, then calling numSubmissions() to check the total number of
  // submissions made for all students, which in this case should be the
  // same as the total number of submissions made for that student.
  @Test public void testPublic3() {
    SubmitServer server= exampleSubmitServer();

    assertEquals(4, server.numSubmissions());
  }

  // Tests adding several submissions to a SubmitServer, for different
  // students, then calling bestSubmissionScore() on one of them.
  @Test public void testPublic4() {
    SubmitServer server= new SubmitServer(1);

    server.addSubmission("Sally Student", 1, 70);
    server.addSubmission("Sally Student", 1, 80);
    server.addSubmission("Sally Student", 1, 90);
    server.addSubmission("Paul Pupil", 1, 100);
    server.addSubmission("Sally Student", 1, 70);
    server.addSubmission("Sally Student", 1, 80);

    assertEquals(90, server.bestSubmissionScore("Sally Student", 1));
  }

  // Tests adding several submissions to a SubmitServer for different
  // students and different projects, then calls some of the forms of
  // numSubmissions().
  @Test public void testPublic5() {
    SubmitServer server= new SubmitServer(2);

    server.addSubmission("Schuyler Scholar", 1, 70);
    server.addSubmission("Freida Freshman", 1, 100);
    server.addSubmission("Schuyler Scholar", 1, 80);
    server.addSubmission("Schuyler Scholar", 1, 90);
    server.addSubmission("Schuyler Scholar", 2, 100);
    server.addSubmission("Paula Pupil", 1, 100);
    server.addSubmission("Schuyler Scholar", 1, 70);
    server.addSubmission("Junior Junior, Jr.", 2, 100);
    server.addSubmission("Freida Freshman", 2, 100);
    server.addSubmission("Theresa Terrapin", 1, 60);
    server.addSubmission("Theresa Terrapin", 2, 50);

    // has only three submissions for the first project
    assertEquals(4, server.numSubmissions("Schuyler Scholar", 1));

    // the submissions for everyone but only for the first project
    assertEquals(7, server.numSubmissions(1));

    // all total submissions for everyone for all projects
    assertEquals(11, server.numSubmissions());
  }

  // private utility method ////////////////////////////////////////////

  // Creates and returns a Submit Server with several submissions for the
  // same student, for the same project.
  SubmitServer exampleSubmitServer() {
    SubmitServer server= new SubmitServer(1);

    server.addSubmission("Julie Junior", 1, 50);
    server.addSubmission("Julie Junior", 1, 70);
    server.addSubmission("Julie Junior", 1, 65);
    server.addSubmission("Julie Junior", 1, 80);

    return server;
  }

}
