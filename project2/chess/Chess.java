//CHESS CLASS by Anna Blendermann
//Honor Pledge: I have not received help on this assignment
package chess;
import java.util.NoSuchElementException;

import com.sun.prism.paint.Color;

//CHESS CLASS*************************************************************
//this class creates an immutable chessboard for each object and tracks
//the gameplay of a chess game using relevant methods
public class Chess 
{
	private BoardSquare[][] chessBoard; //create the storage location
	private Character[] columns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

	//CHESS CONSTRUCTOR***************************************************
	public Chess() 
	{
		chessBoard = new BoardSquare[8][8]; //create the chess board
		for(int row=0; row<chessBoard[0].length; row++)
		{
			for(int col=0; col<chessBoard[row].length; col++)
				chessBoard[row][col] = BoardSquare.EMPTYSQUARE;
		}
	}

	//COPY CONSTRUCTOR****************************************************
	public Chess(Chess otherGame) 
	{
		//new board but use the same references
		BoardSquare[][] newBoard = new BoardSquare[8][8];

		for(int row=0; row<newBoard[0].length; row++)
		{
			for(int col=0; col<newBoard[row].length; col++)
				newBoard[row][col] = otherGame.chessBoard[row][col];
		}
		this.chessBoard = otherGame.chessBoard;
	}

	//GET ROW METHOD - CONVERSION METHOD************************************
	//gets target row for array indexing, or returns invalid
	public int getRow(int row)
	{
		if(row>0 && row<9)
			return (row-1);
		return -1; //row is invalid	
	}

	//GET COLUMN METHOD - CONVERSION METHOD*********************************
	//gets target column for array indexing, or returns invalid
	public int getColumn(char col)
	{
		if(col>='a' && col<='h') {
			for(int x=0; x<columns.length; x++) {
				if(col==columns[x])
					return x;
			}
		}
		return -1; //column is invalid
	}

	//SET ENTRY METHOD****************************************************
	//sets a board piece on the 2D array using row and col parameters
	//throws NoSuchElementException if piece is null, or row/col are invalid
	public void setEntry(BoardSquare piece, char col, int row) throws NoSuchElementException 
	{
		if(piece!=null && getRow(row)!=-1 && getColumn(col)!=-1)
			chessBoard[getRow(row)][getColumn(col)] = piece;
		else
			throw new NoSuchElementException();

	} //end of setEntry method

	//GET ENTRY METHOD*****************************************************
	//gets a board piece from the 2D array using row and col parameters
	//throws NoSuchElementException if piece is null, or row/col are invalid
	public BoardSquare getEntry(char col, int row) throws NoSuchElementException 
	{
		if(getRow(row)!=-1 && getColumn(col)!=-1)
			return chessBoard[getRow(row)][getColumn(col)];
		else
			throw new NoSuchElementException();

	} //end of getEntry exception

	//COUNT METHOD********************************************************
	//Gets number of target pieces on the board through iteration
	public int count(BoardSquare piece) 
	{	
		int numPieces = 0;
		if(piece!=null) //check for invalid param
		{
			for(int row=0; row<chessBoard[0].length; row++)
			{
				for(int col=0; col<chessBoard[row].length; col++)
				{
					if(chessBoard[row][col].equals(piece))
						numPieces=numPieces+1;
				}
			}
		}		
		return numPieces;
	} //end of count method

	//BLACK WON METHOD*****************************************************
	//Case1 - only kings on the board are black, return true
	//Case2 - there exists only white or black/white kings, return false
	public boolean blackWon() 
	{
		int numBlackKings = 0, numWhiteKings=0;
		for(int row=0; row<chessBoard[0].length; row++)
		{
			for(int col=0; col<chessBoard[row].length; col++)
			{
				if(chessBoard[row][col].equals(BoardSquare.BLACKKING))
					numBlackKings=numBlackKings+1;
				else if(chessBoard[row][col].equals(BoardSquare.WHITEKING))
					numWhiteKings=numWhiteKings+1;
			}
		}
		if(numBlackKings>0 && numWhiteKings==0)
			return true;
		else //all other cases are covered
			return false;
	}

	//WHITE WON METHOD******************************************************
	//Case1 - only kings on the board are white, return true
	//Case2 - there exists only black or black/white kings, return false
	public boolean whiteWon() 
	{
		int numBlackKings = 0, numWhiteKings=0;
		for(int row=0; row<chessBoard[0].length; row++)
		{
			for(int col=0; col<chessBoard[row].length; col++)
			{
				if(chessBoard[row][col].equals(BoardSquare.BLACKKING))
					numBlackKings=numBlackKings+1;
				else if(chessBoard[row][col].equals(BoardSquare.WHITEKING))
					numWhiteKings=numWhiteKings+1;
			}
		}
		return(numWhiteKings>0 && numBlackKings==0);
	}

	//VALID MOVE METHOD******************************************************
	//condition1 - starting square and ending square actually lie on the board
	//condition2 - starting square is not empty 
	//condition3 - starting and ending square is valid for piece in starting square
	//condition4 - ending square is empty or occupied by same color 
	//condition5 - except for a knight, intermediate squares on the path are empty
	public boolean validMove(char startCol, int startRow, char endCol, int endRow) 
	{		
		BoardSquare startPiece, endPiece;
		int row1, col1, row2, col2;

		//condition1 - check if starting square and ending square lie on the board
		if(getRow(startRow) != -1 && getRow(endRow) != -1 && 
				getColumn(startCol) != -1 && getColumn(endCol) != -1)
		{
			startPiece = chessBoard[getRow(startRow)][getColumn(startCol)];
			endPiece = chessBoard[getRow(endRow)][getColumn(endCol)];

			//Rest of the program uses 0-7, but validMove() uses 1-8
			row1 = getRow(startRow)+1; 
			col1 = getColumn(startCol)+1; 
			row2 = getRow(endRow)+1;
			col2 = getColumn(endCol)+1;

			//condition2 - check if the starting square is NOT empty
			if(!startPiece.isEmptySquare())
			{
				//condition4 - check if the start piece and end piece are opposite colors
				if(endPiece.isEmptySquare() || startPiece.oppositeColors(endPiece))
				{	
					//condition 3 and 5 - valid path and open path
					if(startPiece.isPawn() || startPiece.isKnight() || startPiece.isKing())
						return(validPath(startPiece, row1, col1, row2, col2));

					else if(startPiece.isRook() || startPiece.isBishop() || startPiece.isQueen())
						return(validPath(startPiece, row1, col1, row2, col2) &&
								openPath(row1, col1, row2, col2));
				}
			}
		}
		return false; //some condition not satisfied
	} //end of validMove method

	//MOVE METHOD**********************************************************
	//Step1 - if an opponents piece is occupying the ending square, remove it
	//Step2 - place the starting piece at the ending square
	//Step3 - remove the original piece from the starting square
	public boolean move(char startCol, int startRow, char endCol, int endRow) 
	{
		if(validMove(startCol, startRow, endCol, endRow))
		{
			if(!chessBoard[getRow(endRow)][getColumn(endCol)].isEmptySquare())
				chessBoard[getRow(endRow)][getColumn(endCol)] = BoardSquare.EMPTYSQUARE; //step1

			//step2 and step3 - move the piece then delete from starting square
			chessBoard[getRow(endRow)][getColumn(endCol)] = chessBoard[startRow-1][getColumn(startCol)];
			chessBoard[getRow(startRow)][getColumn(startCol)] = BoardSquare.EMPTYSQUARE;
			return true;
		}
		return false;
	}

	//VALID PATH METHOD - VALIDATE MOVEMENT************************************
	//validPath checks for the piece type, and then depending on the type
	//validates or invalidates the target movement for that piece (Math.abs() used)
	public boolean validPath(BoardSquare piece, int row1, int col1, int row2, int col2)
	{
		//Pawn Check*************************************
		if(piece.isPawn()) //check if pawn move is valid
		{
			if(piece.isWhite())
				return((row2-row1)==1 && col1==col2);
			else if(piece.isBlack())
				return((row2-row1)==-1 && col1==col2);
		}
		
		//Rook Check*************************************
		else if(piece.isRook()) //check is rook move is valid
			return((Math.abs(row2-row1)>0 && col1==col2) ||
					(row1==row2 && Math.abs(col2-col1)>0));

		//Knight Check********************************************
		else if(piece.isKnight()) //check if knight move is valid
			return((Math.abs(row2-row1)*Math.abs(col2-col1))==2);

		//Bishop Check********************************************
		else if(piece.isBishop())
			return((Math.abs(row2-row1)==Math.abs(col2-col1)));

		//Queen Check**********************************************
		else if(piece.isQueen()) 
			return((Math.abs(row2-row1)>0 && col1==col2) ||
					(row1==row2 && Math.abs(col1-col2)>0) ||
					(Math.abs(row2-row1)==Math.abs(col2-col1)));
	
		//King Check************************************************
		else if(piece.isKing()) 
			return((Math.abs(row2-row1)==1 && col1==col2) ||
					(row1==row2 && Math.abs(col1-col2)==1) ||
					(Math.abs(row2-row1)*Math.abs(col2-col1)==1));
		
		return false;
	} //end of validPath method

	//OPEN PATH METHOD - MOVE VALIDATION************************************
	//openPath checks if target path is horizontal, vertical, or diagonal
	//then checks for obstructing pieces, excluding the startSquare and endSquare
	public boolean openPath(int startRow, int startCol, int endRow, int endCol)
	{
		//Convert from 1-8 back to 0-7 so we can work with indexes
		int row1 = startRow-1;
		int col1 = startCol-1;
		int row2 = endRow-1;
		int col2 = endCol-1;

		if(row1==row2) //piece is moving horizontally
		{
			for(int col=col1+1; col<col2; col++)
			{
				if(!chessBoard[row1][col].isEmptySquare())
					return false;
			}
		}
		else if(col1==col2) //piece is moving vertically
		{
			for(int row=row1+1; row<row2; row++)
			{
				if(!chessBoard[row][col1].isEmptySquare())
					return true;
			}
		}
		else if(Math.abs(row2-row1)==Math.abs(col2-col1)) //moving diagonally
		{
			//if(Math.abs(row2-row1)==1)
			//return true; //one diagonal space is fine		

			for(int row=row1+1,col=col1+1; row<row2; row++, col++)
			{
				if(!chessBoard[row][col].isEmptySquare())
					return false; //obstructing piece exists
			}
		}
		return true;
	} //end of openPath method

} //end of Chess class
