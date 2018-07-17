package myTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Knight;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KnightTest {

	@Test
	public void shouldCheckMoveForKnight() throws InvalidMoveException {

		// given
		Knight k = new Knight();
		Board board = new Board();
		Coordinate coordinateWhiteKnight = new Coordinate(1, 0);
		Coordinate coordinateBlackKnight = new Coordinate(6, 7);

		// when
		board.setPieceAt(Piece.WHITE_KNIGHT, coordinateWhiteKnight);
		board.setPieceAt(Piece.BLACK_KNIGHT, coordinateBlackKnight);

		// then
		assertEquals(true, k.checkMove(board, coordinateWhiteKnight, new Coordinate(2, 2)));
		assertEquals(true, k.checkMove(board, coordinateWhiteKnight, new Coordinate(3, 1)));
		assertEquals(true, k.checkMove(board, coordinateWhiteKnight, new Coordinate(0, 2)));

		assertEquals(true, k.checkMove(board, coordinateBlackKnight, new Coordinate(4, 6)));
		assertEquals(true, k.checkMove(board, coordinateBlackKnight, new Coordinate(7, 5)));
	}

	@Test
	public void shouldCheckMoveForKnightByPiece() throws InvalidMoveException {

		// given
		Knight k = new Knight();
		Board board = new Board();
		Coordinate coordinateWhiteKnight = new Coordinate(1, 0);

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
		board.setPieceAt(Piece.WHITE_KNIGHT, coordinateWhiteKnight);
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(true, k.checkMove(board, coordinateWhiteKnight, new Coordinate(2, 2)));
	}
	
	@Test 
	public void shouldCheckMoveForKnightOnChessboardCapture() throws InvalidMoveException {

		// given
		Knight k = new Knight();
		Board board = new Board();
		Coordinate coordinateWhiteKnight = new Coordinate(4, 4);

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
		board.setPieceAt(Piece.WHITE_KNIGHT, coordinateWhiteKnight);
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(true, k.checkMove(board, coordinateWhiteKnight, new Coordinate(3, 6)));
		
	}
	
	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForKnightCaptureWhite() throws InvalidMoveException {

		// given
		Knight k = new Knight();
		Board board = new Board();
		Coordinate coordinateWhiteKnight = new Coordinate(3, 3);

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
		board.setPieceAt(Piece.WHITE_KNIGHT, coordinateWhiteKnight);
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(false, k.checkMove(board, coordinateWhiteKnight, new Coordinate(4, 1)));
	}
}
