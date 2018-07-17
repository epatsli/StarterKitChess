package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class King implements PiecesMove {

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		//PieceType pieceTo = board.getPieceAt(coordinateTo).getType();
		
		if (((((Math.abs(coordinateFromX - coordinateToX) == 1) && (Math.abs(coordinateFromY - coordinateToY) == 0))
				|| ((Math.abs(coordinateFromY - coordinateToY) == 1))
						&& (Math.abs(coordinateFromX - coordinateToX) == 0))
				|| ((Math.abs(coordinateFromX - coordinateToX) == 1)
						&& (Math.abs(coordinateFromY - coordinateToY) == 1)))
				&& ((checkFieldToisEmpty(board, coordinateTo))
						|| ((checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))&& ((board.getPieceAt(coordinateTo).getType())!=PieceType.KING))))
			return true;
		else
			throw new InvalidMoveException();
	}
	// jump 2

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
