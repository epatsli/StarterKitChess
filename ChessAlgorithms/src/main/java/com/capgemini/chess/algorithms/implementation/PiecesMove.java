package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface PiecesMove {

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException;

	default void figuresOnBoard(Board board) {

		Piece piece;
		int coordinateX = 0;
		int coordinateY = 0;
		Coordinate coordinate;
		List<Coordinate> listWhitePiece = new ArrayList<Coordinate>();
		List<Coordinate> listBlackPiece = new ArrayList<Coordinate>();

		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				coordinate = new Coordinate(coordinateX + row, coordinateY + column);
				piece = board.getPieceAt(coordinate);
				if ((piece != null) && (piece.getColor() == Color.WHITE))
					listWhitePiece.add(coordinate);
				else if ((piece != null) && (piece.getColor() == Color.BLACK))
					listBlackPiece.add(coordinate);
			}
		}
	}

	default void allPossibleMovePerColor(List<Coordinate> listWhitePiece, List<Coordinate> listBlackPiece, Board board)
			throws InvalidMoveException {

		Set<Coordinate> setAllMoveWhite = new HashSet<>();
		Set<Coordinate> setAllMoveBlack = new HashSet<>();
		Coordinate coordinateFrom = new Coordinate(0, 0);
		Coordinate coordinateTo;
		int coordinateX = 0;
		int coordinateY = 0;

		for (int index = 0; index < listWhitePiece.size(); index++) {
			coordinateFrom = listWhitePiece.get(index);
			for (int row = 0; row < Board.SIZE; row++) {
				for (int column = 0; column < Board.SIZE; column++) {
					coordinateTo = new Coordinate(coordinateX, coordinateY);
					if (checkMove(board, coordinateFrom, coordinateTo))
						setAllMoveWhite.add(coordinateTo);
				}
			}
		}
		
		for (int index = 0; index < listBlackPiece.size(); index++) {
			coordinateFrom = listBlackPiece.get(index);
			for (int row = 0; row < Board.SIZE; row++) {
				for (int column = 0; column < Board.SIZE; column++) {
					coordinateTo = new Coordinate(coordinateX, coordinateY);
					if (checkMove(board, coordinateFrom, coordinateTo))
						setAllMoveBlack.add(coordinateTo);
				}
			}
		}
	}
}
