package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import submitserver.SubmitServer;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Creates one thread, that reads one list of submissions, that only
  // contains one submission, just to ensure that one thread can be created
  // and manipulated correctly.
  @Test public void testPublic1() {
    SubmitServer submitserver= new SubmitServer(1);

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions1.html"
      });

    assertEquals(1, submitserver.numSubmissions("Laura Llama", 1));
    assertEquals(1, submitserver.numSubmissions(1));
    assertEquals(1, submitserver.numSubmissions("Laura Llama"));
    assertEquals(1, submitserver.numSubmissions());
  }

  // Creates one thread, that reads one list of submissions, that contains
  // several submissions for the same student for different projects.
  @Test public void testPublic2() {
    SubmitServer submitserver= new SubmitServer(1);

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions2.html"
      });

    assertEquals(3, submitserver.numSubmissions("Laura Llama", 1));
    assertEquals(3, submitserver.numSubmissions(1));
    assertEquals(3, submitserver.numSubmissions("Laura Llama"));
    assertEquals(3, submitserver.numSubmissions());
  }

  // Creates one thread, that reads one list of submissions, that contains
  // several submissions for different students for the same project.
  @Test public void testPublic3() {
    SubmitServer submitserver= new SubmitServer(2);
    String[] names= {"Aaron Aardvark", "Emory Emu", "Hamilton Hamster",
                     "Kourtney Koala", "Laura Llama", "Quinton Quokka",
                     "Sally Salamander"};

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions3.html"
      });

    for (String name : names)
      assertEquals(1, submitserver.numSubmissions(name, 2));
    assertEquals(7, submitserver.numSubmissions(2));
    assertEquals(7, submitserver.numSubmissions());
    assertEquals(0, submitserver.numSubmissions(1));
  }

  // Creates one thread, that reads one list of submissions, that contains
  // several submissions for different students for different projects.
  @Test public void testPublic4() {
    SubmitServer submitserver= new SubmitServer(2);
    String[][] names= {
      {"Hamilton Hamster", "Horace Horse", "Ignacio Iguana", "Laura Llama",
       "Sally Salamander"},
      {"Aaron Aardvark", "Emory Emu", "Kourtney Koala", "Manuel Manatee",
       "Quinton Quokka", "Rhianna Rhinoceros"}
    };
    int project, student;

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions4.html"
      });

    for (project= 0; project < names.length; project++)
      for (student= 0; student < names[project].length; student++)
        assertEquals(1, submitserver.numSubmissions(names[project][student],
                                                    project + 1));
    assertEquals(5, submitserver.numSubmissions(1));
    assertEquals(6, submitserver.numSubmissions(2));
    assertEquals(11, submitserver.numSubmissions());
  }

  // Creates two threads, that both read lists of submissions that contain
  // submissions for the same student for the same project.
  @Test public void testPublic5() {
    SubmitServer submitserver= new SubmitServer(1);

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions5a.html",
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions5b.html"
      });

    assertEquals(9, submitserver.numSubmissions("Laura Llama", 1));
    assertEquals(9, submitserver.numSubmissions(1));
    assertEquals(9, submitserver.numSubmissions("Laura Llama"));
    assertEquals(9, submitserver.numSubmissions());
  }

  // Creates two threads, that both read lists of submissions that contain
  // submissions for different students for the same project.
  @Test public void testPublic6() {
    SubmitServer submitserver= new SubmitServer(1);
    String[] names= {"Aaron Aardvark", "Emory Emu", "Hamilton Hamster",
                     "Kourtney Koala", "Laura Llama", "Quinton Quokka",
                     "Sally Salamander"};

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions6a.html",
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions6b.html"
      });

    for (String name : names)
      assertEquals(1, submitserver.numSubmissions(name, 1));
    assertEquals(7, submitserver.numSubmissions(1));
    assertEquals(7, submitserver.numSubmissions());
  }

  // Creates two threads, that both read lists of submissions that contain
  // submissions for the same student for different projects.
  @Test public void testPublic7() {
    SubmitServer submitserver= new SubmitServer(3);

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions7a.html",
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions7b.html"
      });

    assertEquals(5, submitserver.numSubmissions("Laura Llama", 1));
    assertEquals(4, submitserver.numSubmissions("Laura Llama", 2));
    assertEquals(5, submitserver.numSubmissions(1));
    assertEquals(4, submitserver.numSubmissions(2));
    assertEquals(9, submitserver.numSubmissions("Laura Llama"));
    assertEquals(9, submitserver.numSubmissions());
  }

  // Creates two threads, that both read lists of submissions that contain
  // submissions for different students for different projects.
  @Test public void testPublic8() {
    SubmitServer submitserver= new SubmitServer(2);
    String[][] names= {
      {"Hamilton Hamster", "Horace Horse", "Ignacio Iguana", "Laura Llama",
       "Sally Salamander"},
      {"Aaron Aardvark", "Emory Emu", "Kourtney Koala", "Manuel Manatee",
       "Quinton Quokka", "Rhianna Rhinoceros"}
    };
    int project, student;

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions8a.html",
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions8b.html"
      });

    for (project= 0; project < names.length; project++)
      for (student= 0; student < names[project].length; student++)
        assertEquals(1, submitserver.numSubmissions(names[project][student],
                                                    project + 1));
    assertEquals(5, submitserver.numSubmissions(1));
    assertEquals(6, submitserver.numSubmissions(2));
    assertEquals(11, submitserver.numSubmissions());
  }

  // Creates three threads, that each read lists of submissions that contain
  // submissions for different students for different projects.
  @Test public void testPublic9() {
    SubmitServer submitserver= new SubmitServer(3);
    String[] names= {
      "Aaron Aardvark", "Emory Emu", "Hamilton Hamster", "Horace Horse",
      "Ignacio Iguana", "Kourtney Koala", "Laura Llama", "Manuel Manatee",
      "Quinton Quokka", "Rhianna Rhinoceros", "Sally Salamander"
    };
    int[][] numSubmissions= {
      {0, 2, 0},  // submissions for each project for Aaron Aardvark
      {1, 1, 1},  // submissions for each project for Emory Emu
      {2, 0, 0},  // etc.
      {1, 0, 0},
      {2, 1, 0},
      {0, 0, 1},
      {1, 1, 0},
      {0, 0, 1},
      {0, 0, 1},
      {0, 1, 1},
      {0, 1, 1}
    };
    int project, student;

    submitserver.readSubmissions(new String[]{
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions9a.html",
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions9b.html",
        "http://www.cs.umd.edu/class/spring2016/cmsc132/" +
        "projectsubmissions9c.html"
      });

    for (student= 0; student < names.length; student++)
      for (project= 0; project < numSubmissions[student].length; project++)
        assertEquals(numSubmissions[student][project],
                     submitserver.numSubmissions(names[student],
                                                 project + 1));
  }

}
