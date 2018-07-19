package myTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Pawn;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PawnTest {
	
	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForPawn() throws InvalidMoveException {


		// given
		Pawn p = new Pawn();
		Board board = new Board();
		Coordinate coordinateWhitePawn = new Coordinate(0, 1);
		Coordinate coordinateBlackPawn = new Coordinate(5, 6);

		// when
		board.setPieceAt(Piece.WHITE_PAWN, coordinateWhitePawn);
		board.setPieceAt(Piece.BLACK_PAWN, coordinateBlackPawn);

		// then
		assertEquals(true, p.checkMove(board, coordinateWhitePawn, new Coordinate(0, 2)));
		assertEquals(true, p.checkMove(board, coordinateWhitePawn, new Coordinate(0, 3)));
		assertEquals(true, p.checkMove(board, coordinateBlackPawn, new Coordinate(5, 5)));
		assertEquals(true, p.checkMove(board, coordinateBlackPawn, new Coordinate(5, 4)));
		
		assertEquals(false, p.checkMove(board, coordinateWhitePawn, new Coordinate(0, 4)));

	}

	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForPawnBackwards() throws InvalidMoveException {
		
		// given
		Pawn p = new Pawn();
		Coordinate coordinateWhitePawn=new Coordinate(0, 1);
		Board board = new Board();
		
		// when
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
		}

		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE-1; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(7, 2));
		
		// then
		assertEquals(false,  p.checkMove(board, coordinateWhitePawn, new Coordinate(7, 1)));
		
	}
	
	@Test (expected = InvalidMoveException.class)
	public void shouldCheckCaptureForPawn() throws InvalidMoveException {
		
		// given
		Pawn p = new Pawn();
		Coordinate coordinateWhitePawn=new Coordinate(3, 5);
		Board board = new Board();
		
		// when
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
		}

		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE-1; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(3, 5));
		
		// then
		assertEquals(true,  p.checkMove(board, coordinateWhitePawn, new Coordinate(2, 6)));
		assertEquals(true,  p.checkMove(board, coordinateWhitePawn, new Coordinate(4, 6)));
		assertEquals(true,  p.checkMove(board, coordinateWhitePawn, new Coordinate(3, 6)));
		
	}

}
