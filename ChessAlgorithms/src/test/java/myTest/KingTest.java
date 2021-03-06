package myTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.King;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KingTest {

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForKing() throws InvalidMoveException {

		// given
		King k = new King();
		Board board = new Board();
		Coordinate coordinateWhiteKing = new Coordinate(4, 0);
		Coordinate coordinateBlackKing = new Coordinate(4, 7);

		// when
		board.setPieceAt(Piece.WHITE_KING, coordinateWhiteKing);
		board.setPieceAt(Piece.BLACK_KING, coordinateBlackKing);

		// then
		assertEquals(true, k.checkMove(board, coordinateWhiteKing, new Coordinate(3, 0)));
		assertEquals(true, k.checkMove(board, coordinateWhiteKing, new Coordinate(3, 1)));
		assertEquals(true, k.checkMove(board, coordinateWhiteKing, new Coordinate(5, 1)));
		assertEquals(true, k.checkMove(board, coordinateWhiteKing, new Coordinate(5, 0)));
		assertEquals(true, k.checkMove(board, coordinateBlackKing, new Coordinate(3, 7)));
		assertEquals(true, k.checkMove(board, coordinateBlackKing, new Coordinate(3, 6)));
		assertEquals(true, k.checkMove(board, coordinateBlackKing, new Coordinate(4, 6)));
		assertEquals(true, k.checkMove(board, coordinateBlackKing, new Coordinate(5, 6)));
		assertEquals(false, k.checkMove(board, coordinateBlackKing, new Coordinate(4, 5)));

	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForKingByPiece() throws InvalidMoveException {

		// given
		King k = new King();
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

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 1));
		}

		// then
		assertEquals(false, k.checkMove(board, new Coordinate(3, 0), new Coordinate(3, 4)));

	}

	@Test
	public void shouldCheckMoveStepBack() throws InvalidMoveException {

		// given
		King k = new King();
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
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 4));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(true, k.checkMove(board, new Coordinate(4, 4), new Coordinate(4, 3)));

	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckCapture() throws InvalidMoveException {

		// given
		King k = new King();
		Board board = new Board();

		// when
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
		board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 3));
		board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
		}

		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 4));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 4));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(true, k.checkMove(board, new Coordinate(4, 4), new Coordinate(5, 3)));
		assertEquals(true, k.checkMove(board, new Coordinate(4, 4), new Coordinate(3, 4)));
	}

}
