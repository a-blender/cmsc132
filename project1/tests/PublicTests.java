package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import submitserver.SubmitServer;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests calling addSubmission() to add one submission for one student to
  // a SubmitServer object, then calling numSubmissions(name) on that
  // student.
  @Test public void testPublic1() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Paula Pupil", 74);

    assertEquals(1, server.numSubmissions("Paula Pupil"));
  }

  // Tests calling addSubmission() to add several submissions for one
  // student to a SubmitServer object, then calling numSubmissions(name) on
  // that student.
  @Test public void testPublic2() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 64);
    server.addSubmission("Leanne Learner", 68);
    server.addSubmission("Leanne Learner", 72);
    server.addSubmission("Leanne Learner", 70);
    server.addSubmission("Leanne Learner", 0);
    server.addSubmission("Leanne Learner", 70);
    server.addSubmission("Leanne Learner", 84);

    assertEquals(7, server.numSubmissions("Leanne Learner"));
  }

  // Tests calling addSubmission() and numSubmissions() for different
  // students in a SubmitServer.
  @Test public void testPublic3() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Theresa Terrapin", 62);
    server.addSubmission("Theresa Terrapin", 55);
    server.addSubmission("Schuyler Scholar", 95);
    server.addSubmission("Leanne Learner", 93);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 93);
    server.addSubmission("Ursula Undergraduate", 57);

    assertEquals(2, server.numSubmissions("Leanne Learner"));
    assertEquals(4, server.numSubmissions("Schuyler Scholar"));
    assertEquals(4, server.numSubmissions("Theresa Terrapin"));
    assertEquals(2, server.numSubmissions("Ursula Undergraduate"));
  }

  // Tests calling numSubmissions() (the overloaded form without a
  // parameter) on a SubmitServer object storing different numbers of
  // submissions.
  @Test public void testPublic4() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Theresa Terrapin", 62);
    server.addSubmission("Theresa Terrapin", 55);
    server.addSubmission("Schuyler Scholar", 95);
    server.addSubmission("Leanne Learner", 93);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 93);
    server.addSubmission("Ursula Undergraduate", 57);

    assertEquals(12, server.numSubmissions());
  }

  // Tests calling bestSubmissionNumber() on a student who made several
  // submissions.
  @Test public void testPublic5() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 92);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 96);

    assertEquals(4, server.bestSubmissionNumber("Sophie Sophomore"));
  }

  // Tests calling bestSubmissionNumber() on the name of a nonexistent
  // student.
  @Test public void testPublic6() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 96);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 92);

    assertEquals(-1, server.bestSubmissionNumber("Misty Mysterious"));
  }

  // Tests calling bestSubmissionScore() on a student who made several
  // submissions.
  @Test public void testPublic7() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 92);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 96);

    assertEquals(96, server.bestSubmissionScore("Sophie Sophomore"));
  }

  // Tests calling bestSubmissionScore() on the name of a nonexistent
  // student.
  @Test public void testPublic8() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 96);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 92);

    assertEquals(-1, server.bestSubmissionScore("Misty Mysterious"));
  }

  // Tests calling satisfactory() on a student with satisfactory
  // submissions.
  @Test public void testPublic9() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 0);
    server.addSubmission("Freddy Freshman", 0);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 0);
    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 0);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 96);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 92);

    assertTrue(server.satisfactory("Freddy Freshman"));
  }

  // Tests calling satisifactory() on the name of a nonexistent student.
  @Test public void testPublic10() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Junior Junior, Jr.", 87);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 96);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 92);

    assertFalse(server.satisfactory("Misty Mysterious"));
  }

  // Tests calling gotExtraCredit() in one case.
  @Test public void testPublic11() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Theresa Terrapin", 62);
    server.addSubmission("Theresa Terrapin", 55);
    server.addSubmission("Schuyler Scholar", 95);
    server.addSubmission("Leanne Learner", 93);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 93);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Junior Junior, Jr.", 100);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 96);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 92);

    assertEquals(1, server.gotExtraCredit());
  }

  // Tests calling changeScore() in one case.
  @Test public void testPublic12() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Theresa Terrapin", 62);
    server.addSubmission("Theresa Terrapin", 55);
    server.addSubmission("Schuyler Scholar", 95);
    server.addSubmission("Leanne Learner", 93);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 93);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Junior Junior, Jr.", 100);
    server.addSubmission("Freddy Freshman", 84);
    server.addSubmission("Freddy Freshman", 55);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Freddy Freshman", 68);
    server.addSubmission("Freddy Freshman", 48);
    server.addSubmission("Ursula Undergraduate", 57);
    server.addSubmission("Sophie Sophomore", 96);
    server.addSubmission("Sophie Sophomore", 0);
    server.addSubmission("Sophie Sophomore", 75);
    server.addSubmission("Sophie Sophomore", 92);

    assertEquals(96, server.bestSubmissionScore("Sophie Sophomore"));
    server.changeScore("Sophie Sophomore", 1, 98);
    assertEquals(98, server.bestSubmissionScore("Sophie Sophomore"));
  }

  // Tests addSubmission() with a too-small negative score.
  @Test public void testPublic13() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Leanne Learner", -10);
    server.addSubmission("Leanne Learner", 92);

    assertEquals(2, server.numSubmissions());
    assertEquals(2, server.numSubmissions("Leanne Learner"));
  }

  // Tests addSubmission() with a too-large score greater than 100.
  @Test public void testPublic14() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Leanne Learner", 101);
    server.addSubmission("Leanne Learner", 92);

    assertEquals(2, server.numSubmissions());
    assertEquals(2, server.numSubmissions("Leanne Learner"));
  }

  // Tests calling some of the methods with names (references) that are null.
  @Test public void testPublic15() {
    SubmitServer server= new SubmitServer();

    server.addSubmission("Leanne Learner", 83);
    server.addSubmission("Schuyler Scholar", 92);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 0);
    server.addSubmission("Ursula Undergraduate", 77);
    server.addSubmission("Theresa Terrapin", 62);
    server.addSubmission("Theresa Terrapin", 55);
    server.addSubmission("Schuyler Scholar", 95);
    server.addSubmission("Leanne Learner", 93);
    server.addSubmission("Theresa Terrapin", 84);
    server.addSubmission("Schuyler Scholar", 93);
    server.addSubmission("Ursula Undergraduate", 57);

    assertEquals(-1, server.numSubmissions(null));
    assertEquals(-1, server.bestSubmissionNumber(null));
    assertEquals(-1, server.bestSubmissionScore(null));
    assertFalse(server.satisfactory(null));
  }

}
