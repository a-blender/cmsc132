//STUDENT TESTS by Anna Blendermann
//Honor Pledge: I have not received help on this assignment
package tests;
import java.util.NoSuchElementException;

import org.junit.*;

import chess.BoardSquare;
import chess.Chess;
import static org.junit.Assert.*;

//STUDENT TESTS CLASS****************************************************
//this class tests the methods of the Chess class for functionality,
//accuracy, backwards-moving pieces, and special cases
public class StudentTests 
{
	//STUDENT TEST 1***************************************************** 
	//tests the accuracy of the getColumn method
	@Test public void studentTest1() 
	{
		Chess chess= new Chess();

		assertEquals(0, chess.getColumn('a'));
		assertEquals(2, chess.getColumn('c'));
		assertEquals(5, chess.getColumn('f'));
		assertEquals(-1, chess.getColumn('x'));
		assertEquals(-1, chess.getColumn('z'));
	}

	//STUDENT TEST 2***************************************************** 
	//tests correct params for setEntry and getEntry
	@Test public void studentTest2() 
	{
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.WHITEPAWN, 'a', 2);
		chess.setEntry(BoardSquare.WHITEPAWN, 'b', 2);
		chess.setEntry(BoardSquare.BLACKPAWN, 'a', 7);
		chess.setEntry(BoardSquare.BLACKPAWN, 'b', 7);

		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('b', 4));
		assertEquals(BoardSquare.WHITEPAWN, chess.getEntry('a', 2));
		assertEquals(BoardSquare.BLACKPAWN, chess.getEntry('a', 7));
	}

	//STUDENT TEST 3*******************************************************
	//tests invalid params for setEntry and getEntry
	@Test(expected=NoSuchElementException.class) public void studentTest3() 
	{
		Chess chess= new Chess();
		chess.setEntry(BoardSquare.WHITEQUEEN, 'x', 9);
		chess.setEntry(BoardSquare.BLACKBISHOP, 'y', 12);
		chess.setEntry(BoardSquare.BLACKROOK, 'z', 0);

		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('a', 10));
		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('c', 20));
		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('g', 0));
	}

	//STUDENT TEST 4*******************************************************
	//tests count method for placed pieces and empty squares
	@Test public void studentTest4() 
	{
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.WHITEBISHOP, 'c', 1);
		chess.setEntry(BoardSquare.BLACKBISHOP, 'c', 8);
		chess.setEntry(BoardSquare.WHITEPAWN, 'b', 3);
		chess.setEntry(BoardSquare.BLACKKNIGHT, 'g', 7);

		assertEquals(0, chess.count(BoardSquare.WHITEKING));
		assertEquals(60, chess.count(BoardSquare.EMPTYSQUARE));
		assertEquals(1, chess.count(BoardSquare.BLACKKNIGHT));

		chess.setEntry(BoardSquare.BLACKROOK, 'd', 5);
		chess.setEntry(BoardSquare.BLACKROOK, 'd', 6);
		assertEquals(58, chess.count(BoardSquare.EMPTYSQUARE));
		assertEquals(2, chess.count(BoardSquare.BLACKROOK));
	}

	//STUDENT TEST 5*******************************************************
	//tests the accuracy of blackWon in different cases
	@Test public void studentTest5() 
	{
		Chess chess= new Chess();
		chess.setEntry(BoardSquare.WHITEKING, 'f', 2);
		chess.setEntry(BoardSquare.BLACKKING, 'f', 8);

		assertFalse(chess.blackWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'f', 2);  // remove white king
		assertTrue(chess.blackWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'f', 8);  // remove black king also
		assertFalse(chess.blackWon());

	}

	//STUDENT TEST 6*******************************************************
	//tests the accuracy of white won in different cases
	@Test public void studentTest6() 
	{
		Chess chess= new Chess();
		chess.setEntry(BoardSquare.BLACKKING, 'b', 3);
		chess.setEntry(BoardSquare.WHITEKING, 'd', 3);

		assertFalse(chess.whiteWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'b', 3);  // remove black king
		assertTrue(chess.whiteWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'd', 3);  // remove white king also
		assertFalse(chess.whiteWon());
	}

	//STUDENT TEST 7*******************************************************
	//tests validPath() for pawn movement (pawns can't move backwards)
	@Test public void studentTest7() 
	{
		Chess chess= new Chess();
		assertTrue(chess.validPath(BoardSquare.WHITEPAWN, 2, 5, 3, 5));
		assertTrue(chess.validPath(BoardSquare.BLACKPAWN, 4, 6, 3, 6));

		//pawns cannot move horizontally or diagonally
		assertFalse(chess.validPath(BoardSquare.WHITEPAWN, 2, 5, 2, 8));
		assertFalse(chess.validPath(BoardSquare.BLACKPAWN, 4, 6, 5, 7));

		//black pawn is moving in the wrong direction
		assertFalse(chess.validPath(BoardSquare.BLACKPAWN, 4, 1, 5, 1));
	}

	//STUDENT TEST 8*******************************************************
	//tests openPath() method for accuracy with obstructing pieces
	@Test public void studentTest8() 
	{
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.BLACKKING, 'h', 8);
		chess.setEntry(BoardSquare.WHITEKING, 'g', 7);

		assertFalse(chess.openPath(1, 1, 8, 8)); //obstructing piece
		assertTrue(chess.openPath(1, 2, 1, 6)); //move horizontally

		assertFalse(chess.openPath(7, 1, 7, 8)); //obstructing piece
		assertTrue(chess.openPath(2, 8, 8, 8)); //obstructing piece
		assertTrue(chess.openPath(1, 1, 6, 6)); //move diagonally
	}

	//STUDENT TEST 9*******************************************************
	//tests validMove() for pawn movement (more detailed tests in PublicTests()
	//also confirms the functionality of validPath() and openPath()
	@Test public void studentTest9() 
	{
		Chess chess= new Chess();
		chess.setEntry(BoardSquare.WHITEPAWN, 'b', 5);
		chess.setEntry(BoardSquare.BLACKPAWN, 'f', 5);

		assertEquals(BoardSquare.WHITEPAWN, chess.getEntry('b', 5));
		assertEquals(BoardSquare.BLACKPAWN, chess.getEntry('f', 5));

		assertTrue(chess.validMove('b', 5, 'b', 6));   // forward for white pawn
		assertTrue(chess.validMove('f', 5, 'f', 4));   // forward for black pawn
	}

	//STUDENT TEST 10*******************************************************
	//debugs test10() and test14() from publicTests
	@Test public void studentTest10() 
	{
		Chess chess= new Chess();
				
		//check test10()
		chess.setEntry(BoardSquare.BLACKBISHOP, 'f', 4);
		assertTrue(chess.validMove('f', 4, 'g', 5));
		assertTrue(chess.validMove('f', 4, 'h', 6));
		
		//check test14()
		Chess chess2= new Chess();
		chess2.setEntry(BoardSquare.BLACKBISHOP, 'f', 5);
		chess2.setEntry(BoardSquare.WHITEPAWN, 'g', 6);
		assertTrue(chess2.validMove('f', 5, 'g', 6));  // piece of opposite color		
	}
} //end of student tests
