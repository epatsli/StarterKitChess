package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.Rook;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class RookTest {

	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForRook() throws InvalidMoveException {


		// given
		Rook r = new Rook();
		Board board = new Board();
		Coordinate coordinateWhiteRook = new Coordinate(0, 0);
		Coordinate coordinateBlackRook = new Coordinate(7, 7);
		board.setPieceAt(Piece.WHITE_ROOK, coordinateWhiteRook);
		board.setPieceAt(Piece.BLACK_ROOK, coordinateBlackRook);

		// when
		BoardManager boardManager = new BoardManager(board);

		// then
		assertEquals(true, r.checkMove(board, coordinateWhiteRook, new Coordinate(0, 7)));
		assertEquals(true, r.checkMove(board, coordinateWhiteRook, new Coordinate(7, 0)));
		assertEquals(true, r.checkMove(board, coordinateBlackRook, new Coordinate(3, 7)));
		assertEquals(true, r.checkMove(board, coordinateBlackRook, new Coordinate(7, 4)));
		
		assertEquals(false, r.checkMove(board, coordinateWhiteRook, new Coordinate(4, 1)));

	}

	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForRookByPiece() throws InvalidMoveException {
		
		// given
		Rook r=new Rook();
		Coordinate coordinateWhiteRook=new Coordinate(0, 0);
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
		assertEquals(false,  r.checkMove(board, coordinateWhiteRook, new Coordinate(0, 7)));
		
	}
	
	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForRookOnChessboardCapture() throws InvalidMoveException {
		
		// given
		Rook r=new Rook();
		Coordinate coordinateWhiteRook=new Coordinate(0, 2);
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

		board.setPieceAt(Piece.WHITE_ROOK, coordinateWhiteRook);
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
		assertEquals(true,  r.checkMove(board, coordinateWhiteRook, new Coordinate(0, 5)));
		assertEquals(true,  r.checkMove(board, new Coordinate(0, 5), new Coordinate(5, 5)));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(5, 5));
		assertEquals(true,  r.checkMove(board, new Coordinate(5, 5), new Coordinate(5, 6)));
		assertEquals(true,  r.checkMove(board, new Coordinate(5, 5), new Coordinate(5, 3)));
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(5, 3));
		assertEquals(false,  r.checkMove(board, new Coordinate(5, 3), new Coordinate(5, 1)));
	}
	
	@Test (expected = InvalidMoveException.class)
	public void shouldCheckMoveForRookOnChessboardByPiece() throws InvalidMoveException {
		
		// given
		Rook r=new Rook();
		Coordinate coordinateWhiteRook=new Coordinate(0, 0);
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

		board.setPieceAt(Piece.WHITE_ROOK, coordinateWhiteRook);
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
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(0, 3));
		assertEquals(true,  r.checkMove(board, new Coordinate(0, 2), new Coordinate(0, 4)));

	}
}
