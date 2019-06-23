package tests;
import chess.BoardSquare;
import chess.Chess;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

	private final static char MAXCOL= 'h';
	private final static int MAXROW= 8;

	// Tests the basic operation of the constructor and the methods setEntry()
	// and getEntry(), since the other methods rely upon these.
	@Test public void testPublic1() {
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.WHITEPAWN, 'b', 3);
		chess.setEntry(BoardSquare.BLACKQUEEN, 'd', 4);
		chess.setEntry(BoardSquare.BLACKBISHOP, 'g', 7);

		assertEquals(BoardSquare.WHITEPAWN, chess.getEntry('b', 3));
		assertEquals(BoardSquare.BLACKQUEEN, chess.getEntry('d', 4));
		assertEquals(BoardSquare.BLACKBISHOP, chess.getEntry('g', 7));

		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('a', 2));
		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('b', 2));
		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('g', 5));
		assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('c', 6));
	}

	// Tests calling setEntry() on a square that is off the board (too far to
	// the right).
	@Test(expected=NoSuchElementException.class) public void testPublic2() {
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.WHITEQUEEN, 'i', 4);
	}

	// Tests calling setEntry() on a square that is off the board (too far up).
	@Test(expected=NoSuchElementException.class) public void testPublic3() {
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.WHITEKING, 'c', 9);
	}

	// Tests the contents of a board containing various pieces of both colors.
	@Test public void testPublic4() {
		Chess chess= new Chess();

		chess.setEntry(BoardSquare.BLACKPAWN, 'd', 8);
		chess.setEntry(BoardSquare.WHITEPAWN, 'e', 8);
		chess.setEntry(BoardSquare.BLACKKNIGHT, 'c', 7);
		chess.setEntry(BoardSquare.WHITEKNIGHT, 'f', 7);
		chess.setEntry(BoardSquare.BLACKBISHOP, 'b', 6);
		chess.setEntry(BoardSquare.WHITEBISHOP, 'g', 6);
		chess.setEntry(BoardSquare.BLACKROOK, 'a', 5);
		chess.setEntry(BoardSquare.WHITEROOK, 'h', 5);
		chess.setEntry(BoardSquare.BLACKQUEEN, 'b', 4);
		chess.setEntry(BoardSquare.WHITEQUEEN, 'g', 4);
		chess.setEntry(BoardSquare.BLACKKING, 'c', 3);
		chess.setEntry(BoardSquare.WHITEKING, 'f', 3);

		assertEquals("8: ---pP---\n" +
				"7: --n--N--\n" +
				"6: -b----B-\n" +
				"5: r------R\n" +
				"4: -q----Q-\n" +
				"3: --k--K--\n" +
				"2: --------\n" +
				"1: --------\n" +
				"   abcdefgh\n", TestCode.boardToString(chess));
	}

	// Tests the functionality of the copy constructor.
	@Test public void testPublic5() {
		Chess chess= TestCode.stringToBoard("p------p" +  // row 8
				"-p----p-" +
				"--p--p--" +
				"---pp---" +
				"---pp---" +
				"--p--p--" +
				"-p----p-" +
				"p------p");  // row 1
		Chess chess2= new Chess(chess);  // calls copy constructor
		char i;
		int j;

		for (i= 'a'; i <= MAXCOL; i++)
			for (j= 1; j <= MAXROW; j++)
				if ((i - 'a' + 1) == j || (i - 'a' + 1) == MAXROW - j + 1)
					assertEquals(BoardSquare.BLACKPAWN, chess2.getEntry(i, j));  // diagonals
				else assertEquals(BoardSquare.EMPTYSQUARE, chess2.getEntry(i, j));
	}

	// Tests some of the the functionality of the count() method.
	@Test public void testPublic6() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"--------" +
				"--------" +
				"--------" +
				"--------");  // row 1
		char i;

		assertEquals(0, chess.count(BoardSquare.WHITEKING));
		assertEquals(64, chess.count(BoardSquare.EMPTYSQUARE));

		chess.setEntry(BoardSquare.BLACKROOK, 'a', 3);
		assertEquals(1, chess.count(BoardSquare.BLACKROOK));

		for (i= 'a'; i <= MAXCOL; i++)
			chess.setEntry(BoardSquare.WHITEKNIGHT, i, 3);
		assertEquals(8, chess.count(BoardSquare.WHITEKNIGHT));
	}

	// Tests the method blackWon() in different cases, including when neither
	// player has won.
	@Test public void testPublic7() {
		Chess chess= TestCode.standardBoard();

		assertFalse(chess.blackWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'e', 1);  // remove white king
		assertTrue(chess.blackWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'e', 8);  // remove black king also
		assertFalse(chess.blackWon());
	}

	// Tests the method whiteWon() in different cases, including when neither
	// player has won.
	@Test public void testPublic8() {
		Chess chess= TestCode.standardBoard();

		assertFalse(chess.whiteWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'e', 8);  // remove black king
		assertTrue(chess.whiteWon());

		chess.setEntry(BoardSquare.EMPTYSQUARE, 'e', 1);  // remove white king also
		assertFalse(chess.whiteWon());
	}


	// Tests some functionality of the validMove() method for some valid
	// moves, verifying that both color pawns can move in their own forward
	// direction.
	@Test public void testPublic9() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"-P---p--" +
				"--------" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertTrue(chess.validMove('b', 5, 'b', 6));   // forward for white pawn
		assertTrue(chess.validMove('f', 5, 'f', 4));   // forward for black pawn
	}

	// Tests some functionality of the validMove() method, verifying some
	// valid moves for a black bishop.
	@Test public void testPublic10() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"-----b--" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertTrue(chess.validMove('f', 4, 'e', 3));
		assertTrue(chess.validMove('f', 4, 'd', 2));
		assertTrue(chess.validMove('f', 4, 'c', 1));
		assertTrue(chess.validMove('f', 4, 'g', 5));
		assertTrue(chess.validMove('f', 4, 'h', 6));
	}

	// Tests some functionality of the validMove() method, verifying some
	// valid moves for a white knight.
	@Test public void testPublic11() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"-----N--" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertTrue(chess.validMove('f', 4, 'd', 5));
		assertTrue(chess.validMove('f', 4, 'e', 6));
	}

	// Tests some functionality of the validMove() method, verifying all the
	// valid moves for a black king.
	@Test public void testPublic12() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"-----k--" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertTrue(chess.validMove('f', 4, 'e', 3));
		assertTrue(chess.validMove('f', 4, 'e', 4));
		assertTrue(chess.validMove('f', 4, 'e', 5));
		assertTrue(chess.validMove('f', 4, 'f', 5));
		assertTrue(chess.validMove('f', 4, 'g', 5));
		assertTrue(chess.validMove('f', 4, 'g', 4));
		assertTrue(chess.validMove('f', 4, 'g', 3));
		assertTrue(chess.validMove('f', 4, 'f', 3));
	}

	// Tests the functionality of the validMove() method for some valid moves
	// for two other piece types.
	@Test public void testPublic13() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--P---p-" +
				"-B---B--" +
				"--------" +
				"---rrr--" +
				"----rK--" +
				"--------");  // row 1

		assertTrue(chess.validMove('b', 5, 'a', 6));
		assertTrue(chess.validMove('d', 3, 'd', 2));
	}

	// Tests the functionality of the validMove() method for some valid moves
	// for two piece types, where a piece would move to a square occupied by a
	// piece of the opponent's.
	@Test public void testPublic14() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--P---p-" +
				"-B---B--" +
				"--------" +
				"---rrr--" +
				"----rK--" +
				"--------");  // row 1

		assertTrue(chess.validMove('f', 5, 'g', 6));  // piece of opposite color
		assertTrue(chess.validMove('f', 3, 'f', 2));  // piece of opposite color
	}

	// Tests the functionality of the validMove() method for some invalid
	// moves that have incorrect starting and ending squares.
	@Test public void testPublic15() {
		Chess chess= TestCode.standardBoard();

		assertFalse(chess.validMove('d', 10, 'd', 3));
		assertFalse(chess.validMove('d', 3, 'd', 10));

		assertFalse(chess.validMove('j', 3, 'b', 3));
		assertFalse(chess.validMove('b', 3, 'j', 3));
	}

	// Tests some functionality of the validMove() method for some invalid
	// moves, verifying that both color pawns cannot move backwards.
	@Test public void testPublic16() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"-P---p--" +
				"--------" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertFalse(chess.validMove('b', 5, 'b', 5));  // backward for white pawn
		assertFalse(chess.validMove('f', 5, 'f', 6));  // backward for black pawn
	}

	// Tests some functionality of the validMove() method for some invalid
	// moves for a black bishop.
	@Test public void testPublic17() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"-----b--" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertFalse(chess.validMove('f', 4, 'd', 5));
		assertFalse(chess.validMove('f', 4, 'f', 5));
	}

	// Tests some functionality of the validMove() method for some invalid
	// moves for a white knight.
	@Test public void testPublic18() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"-----N--" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertFalse(chess.validMove('f', 4, 'f', 5));
		assertFalse(chess.validMove('f', 4, 'd', 6));
	}

	// Tests some functionality of the validMove() method for some invalid
	// moves for a black king.
	@Test public void testPublic19() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--------" +
				"--------" +
				"-----k--" +
				"--------" +
				"--------" +
				"--------");  // row 1

		assertFalse(chess.validMove('f', 4, 'e', 2));
		assertFalse(chess.validMove('f', 4, 'd', 2));
		assertFalse(chess.validMove('f', 4, 'f', 6));
		assertFalse(chess.validMove('f', 4, 'f', 4));
	}

	// Tests the functionality of the validMove() method for some invalid
	// moves for some other piece types, that would try to move to a square
	// occupied by a piece of the same color.
	@Test public void testPublic20() {
		Chess chess= TestCode.stringToBoard("--------" +  // row 8
				"--------" +
				"--P---p-" +
				"-B---B--" +
				"--------" +
				"---rrr--" +
				"----rK--" +
				"--------");  // row 1

		assertFalse(chess.validMove('b', 5, 'c', 6));  // piece of same color
		assertFalse(chess.validMove('e', 3, '3', 2));  // piece of same color
	}

}
