package myTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Bishop;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class BishopTest {

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForBihop() throws InvalidMoveException {

		// given
		Bishop b = new Bishop();
		Board board = new Board();
		Coordinate coordinateWhiteBishop = new Coordinate(2, 0);
		Coordinate coordinateBlackBishop = new Coordinate(5, 7);

		// when
		board.setPieceAt(Piece.WHITE_BISHOP, coordinateWhiteBishop);
		board.setPieceAt(Piece.BLACK_BISHOP, coordinateBlackBishop);

		// then
		assertEquals(true, b.checkMove(board, coordinateWhiteBishop, new Coordinate(0, 2)));
		assertEquals(true, b.checkMove(board, coordinateWhiteBishop, new Coordinate(5, 3)));
		assertEquals(true, b.checkMove(board, coordinateBlackBishop, new Coordinate(7, 5)));
		assertEquals(true, b.checkMove(board, coordinateBlackBishop, new Coordinate(0, 2)));
		assertEquals(false, b.checkMove(board, coordinateWhiteBishop, new Coordinate(4, 1)));

	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForBishopByPiece() throws InvalidMoveException {

		// given
		Bishop b = new Bishop();
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
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(false, b.checkMove(board, new Coordinate(5, 0), new Coordinate(7, 2)));

	}

	@Test
	public void shouldCheckMoveForBihopOnChessboardCapture() throws InvalidMoveException {

		// given
		Bishop b = new Bishop();
		Coordinate coordinateWhiteBishop = new Coordinate(3, 3);
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
		board.setPieceAt(Piece.WHITE_BISHOP, coordinateWhiteBishop);
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(true, b.checkMove(board, coordinateWhiteBishop, new Coordinate(5, 5)));
		assertEquals(true, b.checkMove(board, new Coordinate(5, 5), new Coordinate(7, 3)));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(7, 3));
		assertEquals(true, b.checkMove(board, new Coordinate(7, 3), new Coordinate(4, 6)));
	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForBihopJumpOnChessboard() throws InvalidMoveException {

		// given
		Bishop b = new Bishop();
		Coordinate coordinateWhiteBishop = new Coordinate(4, 3);
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
		board.setPieceAt(Piece.WHITE_BISHOP, coordinateWhiteBishop);
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 4));
		assertEquals(true, b.checkMove(board, coordinateWhiteBishop, new Coordinate(2, 5)));

	}
}
