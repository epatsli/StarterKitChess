package myTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Queen;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class QueenTest {

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForQueen() throws InvalidMoveException {

		// given
		Queen q = new Queen();
		Board board = new Board();
		Coordinate coordinateWhiteBishop = new Coordinate(3, 0);
		Coordinate coordinateBlackBishop = new Coordinate(3, 7);

		// when
		board.setPieceAt(Piece.WHITE_QUEEN, coordinateWhiteBishop);
		board.setPieceAt(Piece.BLACK_QUEEN, coordinateBlackBishop);

		// then
		assertEquals(true, q.checkMove(board, coordinateWhiteBishop, new Coordinate(3, 6)));
		assertEquals(true, q.checkMove(board, coordinateWhiteBishop, new Coordinate(6, 3)));
		assertEquals(true, q.checkMove(board, coordinateWhiteBishop, new Coordinate(0, 0)));
		assertEquals(true, q.checkMove(board, coordinateBlackBishop, new Coordinate(5, 5)));
		assertEquals(true, q.checkMove(board, coordinateBlackBishop, new Coordinate(3, 2)));
		assertEquals(false, q.checkMove(board, coordinateBlackBishop, new Coordinate(2, 3)));

	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForQueenByPiece() throws InvalidMoveException {

		// given
		Queen q = new Queen();
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
		assertEquals(false, q.checkMove(board, new Coordinate(3, 0), new Coordinate(3, 4)));

	}

	@Test
	public void shouldCheckMoveForQueenOnChessboardCapture() throws InvalidMoveException {

		// given
		Queen q = new Queen();
		Coordinate coordinateWhiteQueen = new Coordinate(3, 3);
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
		board.setPieceAt(Piece.WHITE_QUEEN, coordinateWhiteQueen);
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}

		// then
		assertEquals(true, q.checkMove(board, coordinateWhiteQueen, new Coordinate(3, 5)));
		assertEquals(true, q.checkMove(board, new Coordinate(3, 5), new Coordinate(0, 2)));
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(0, 2));
		assertEquals(true, q.checkMove(board, new Coordinate(0, 2), new Coordinate(4, 6)));
	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForQueenJumpOnChessboard() throws InvalidMoveException {

		// given
		Queen q = new Queen();
		Coordinate coordinateWhiteQueen = new Coordinate(4, 4);
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
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(3, 3));
		assertEquals(false, q.checkMove(board, coordinateWhiteQueen, new Coordinate(2, 2)));

	}

	@Test(expected = InvalidMoveException.class)
	public void shouldCheckMoveForQueenJumpOnChessboardByWhite() throws InvalidMoveException {

		// given
		Queen q = new Queen();
		Coordinate coordinateWhiteQueen = new Coordinate(7, 2);
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
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(5, 4));
		assertEquals(false, q.checkMove(board, coordinateWhiteQueen, new Coordinate(4, 5)));

	}

}
