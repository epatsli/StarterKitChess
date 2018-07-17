package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Knight implements PiecesMove {

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();

		if ((((Math.abs(coordinateFromX - coordinateToX) == 1) && (Math.abs(coordinateFromY - coordinateToY) == 2))
				|| ((Math.abs(coordinateFromX - coordinateToX) == 2)
						&& (Math.abs(coordinateFromY - coordinateToY) == 1)))
				&& ((checkFieldToisEmpty(board, coordinateTo))
						|| (checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))))
			return true;
		else
			throw new InvalidMoveException();
	}

	private boolean checkEqualColorPlayerFromAndTo(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) {

		Piece piece1 = board.getPieceAt(coordinateFrom);
		Piece piece2 = board.getPieceAt(coordinateTo);

		if ((piece1.getColor()) != (piece2.getColor()))
			return true;

		return false;
	}

	private boolean checkFieldToisEmpty(Board board, Coordinate coordinateTo) {

		Piece piece = board.getPieceAt(coordinateTo);
		if (piece == null)
			return true;

		return false;
	}
}
