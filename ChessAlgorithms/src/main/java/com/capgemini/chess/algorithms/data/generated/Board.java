package com.capgemini.chess.algorithms.data.generated;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;

/**
 * Board representation. Board objects are generated based on move history.
 * 
 * @author Michal Bejm
 *
 */
public class Board {

	public static final int SIZE = 8;

	private Piece[][] pieces = new Piece[SIZE][SIZE];
	private List<Move> moveHistory = new ArrayList<>();
	private BoardState state;

	public Board() {
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public BoardState getState() {
		return state;
	}

	public void setState(BoardState state) {
		this.state = state;
	}

	/**
	 * Sets chess piece on board based on given coordinates
	 * 
	 * @param piece
	 *            chess piece
	 * @param board
	 *            chess board
	 * @param coordinate
	 *            given coordinates
	 */
	public void setPieceAt(Piece piece, Coordinate coordinate) {
		pieces[coordinate.getX()][coordinate.getY()] = piece;
	}

	/**
	 * Gets chess piece from board based on given coordinates
	 * 
	 * @param coordinate
	 *            given coordinates
	 * @return chess piece
	 */
	public Piece getPieceAt(Coordinate coordinate) {
		return pieces[coordinate.getX()][coordinate.getY()];
	}

	public List<Coordinate> listWhiteFiguresOnBoard() {

		Piece piece;
		Coordinate coordinate;
		List<Coordinate> listWhitePiece = new ArrayList<Coordinate>();

		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				coordinate = new Coordinate(row, column);
				piece = getPieceAt(coordinate);
				if ((piece != null) && (piece.getColor() == Color.WHITE))
					listWhitePiece.add(coordinate);
			}
		}
		return listWhitePiece;
	}

	public List<Coordinate> listBlackFiguresOnBoard() {

		Piece piece;
		Coordinate coordinate;
		List<Coordinate> listBlackPiece = new ArrayList<Coordinate>();

		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				coordinate = new Coordinate(row, column);
				piece = getPieceAt(coordinate);
				if ((piece != null) && (piece.getColor() == Color.BLACK))
					listBlackPiece.add(coordinate);
			}
		}
		return listBlackPiece;
	}

	public Coordinate returnPositionWhiteKing() {
		Coordinate possitionWhiteKing = new Coordinate(0, 0);
		Coordinate helpPossitionWhiteKing;
		Piece piece;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				helpPossitionWhiteKing = new Coordinate(row, column);
				piece = getPieceAt(helpPossitionWhiteKing);
				if (piece == (Piece.WHITE_KING))
					possitionWhiteKing = helpPossitionWhiteKing;
			}
		}
		return possitionWhiteKing;
	}

	public Coordinate returnPositionBlackKing() {
		Coordinate possitionBlackKing = new Coordinate(0, 0);
		Coordinate helpPossitionBlackKing;
		Piece piece;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				helpPossitionBlackKing = new Coordinate(row, column);
				piece = getPieceAt(helpPossitionBlackKing);
				if (piece == (Piece.BLACK_KING))
					possitionBlackKing = helpPossitionBlackKing;
			}
		}
		return possitionBlackKing;
	}

}
