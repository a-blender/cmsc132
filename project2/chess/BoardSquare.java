package chess;

/* Note that this class has only private data, and does not have any getters
 * or setters, and it doesn't need any.  Code from outside the class can
 * create BoardSquare objects, and test what color and type they are,
 * without ever having to know the details of exactly how BoardSquare
 * objects are stored internally.
 */

public class BoardSquare {

  private Type type;
  private Color color;
  private enum Type { PAWN, BISHOP, KNIGHT, ROOK, QUEEN, KING, NONE }
  private enum Color { BLACK, WHITE, NONE }


  public static final BoardSquare BLACKPAWN= new BoardSquare(Type.PAWN,
                                                             Color.BLACK);
  public static final BoardSquare BLACKBISHOP= new BoardSquare(Type.BISHOP,
                                                               Color.BLACK);
  public static final BoardSquare BLACKKNIGHT= new BoardSquare(Type.KNIGHT,
                                                               Color.BLACK);
  public static final BoardSquare BLACKROOK= new BoardSquare(Type.ROOK,
                                                             Color.BLACK);
  public static final BoardSquare BLACKQUEEN= new BoardSquare(Type.QUEEN,
                                                              Color.BLACK);
  public static final BoardSquare BLACKKING= new BoardSquare(Type.KING,
                                                             Color.BLACK);
  public static final BoardSquare WHITEPAWN= new BoardSquare(Type.PAWN,
                                                             Color.WHITE);
  public static final BoardSquare WHITEBISHOP= new BoardSquare(Type.BISHOP,
                                                               Color.WHITE);
  public static final BoardSquare WHITEKNIGHT= new BoardSquare(Type.KNIGHT,
                                                               Color.WHITE);
  public static final BoardSquare WHITEROOK= new BoardSquare(Type.ROOK,
                                                             Color.WHITE);
  public static final BoardSquare WHITEQUEEN= new BoardSquare(Type.QUEEN,
                                                              Color.WHITE);
  public static final BoardSquare WHITEKING= new BoardSquare(Type.KING,
                                                             Color.WHITE);
  public static final BoardSquare EMPTYSQUARE= new BoardSquare(Type.NONE,
                                                               Color.NONE);

  public BoardSquare(Type type, Color color) {
    this.type= type;
    this.color= color;
  }

  public boolean isPawn() {
    return type == Type.PAWN;
  }

  public boolean isBishop() {
    return type == Type.BISHOP;
  }

  public boolean isKnight() {
    return type == Type.KNIGHT;
  }

  public boolean isRook() {
    return type == Type.ROOK;
  }

  public boolean isQueen() {
    return type == Type.QUEEN;
  }

  public boolean isKing() {
    return type == Type.KING;
  }

  public boolean isBlack() {
    return color == Color.BLACK;
  }

  public boolean isWhite() {
    return color == Color.WHITE;
  }

  public boolean isEmptySquare() {
    return type == Type.NONE && color == Color.NONE;
  }

  public boolean sameTypes(BoardSquare piece) {
    return type == piece.type;
  }

  public boolean sameColors(BoardSquare piece) {
    return color == piece.color;
  }

  // returns true if the current object and the parameter BoardSquare have
  // opposite colors, meaning black and white or white and black (note that
  // neither can be NONE for true to be returned)
  public boolean oppositeColors(BoardSquare piece) {
    return color == Color.WHITE && piece.color == Color.BLACK ||
           color == Color.BLACK && piece.color == Color.WHITE;
  }

  public boolean equals(Object obj) {
    BoardSquare b;
    boolean result= false;

    if (obj == this)
      result= true;
    else
      if (obj instanceof BoardSquare) {
        b= (BoardSquare) obj;
        result= sameTypes(b) && sameColors(b);
      }

    return result;
  }

  // if we implement equals() we must implement hashCode() (this will be
  // covered later)
  public int hashCode() {
    return 0;
  }

  public String toString() {
    switch (type) {
      case PAWN:
        if (color == Color.BLACK)
          return "p";
        else return "P"; 
      case BISHOP:
        if (color == Color.BLACK)
          return "b";
        else return "B"; 
      case KNIGHT:
        if (color == Color.BLACK)
          return "n";
        else return "N"; 
      case ROOK:
        if (color == Color.BLACK)
          return "r";
        else return "R"; 
      case QUEEN:
        if (color == Color.BLACK)
          return "q";
        else return "Q"; 
      case KING:
        if (color == Color.BLACK)
          return "k";
        else return "K"; 
      case NONE:
        return "-";
    }

    return "-";  // make compiler happy even though we should never reach here
  }

}
