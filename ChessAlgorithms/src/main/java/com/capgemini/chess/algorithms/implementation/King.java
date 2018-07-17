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
		CheckData valid=new CheckData();
		
		if (((((Math.abs(coordinateFromX - coordinateToX) == 1) && (Math.abs(coordinateFromY - coordinateToY) == 0))
				|| ((Math.abs(coordinateFromY - coordinateToY) == 1))
						&& (Math.abs(coordinateFromX - coordinateToX) == 0))
				|| ((Math.abs(coordinateFromX - coordinateToX) == 1)
						&& (Math.abs(coordinateFromY - coordinateToY) == 1)))
				&& ((valid.checkFieldToisEmpty(board, coordinateTo))
						|| ((valid.checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))&& ((board.getPieceAt(coordinateTo).getType())!=PieceType.KING))))
			return true;
		else
			throw new InvalidMoveException();
	}
	// jump 2

}
