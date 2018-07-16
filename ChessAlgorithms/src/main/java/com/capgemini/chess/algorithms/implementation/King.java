package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class King implements PiecesMove {

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		List<Coordinate> listMoveKing = new ArrayList<>();

		if ((((Math.abs(coordinateFromX - coordinateToX) == 1) && (Math.abs(coordinateFromY - coordinateToY) == 0))
				|| ((Math.abs(coordinateFromY - coordinateToY) == 1))
						&& (Math.abs(coordinateFromX - coordinateToX) == 0))
				|| ((Math.abs(coordinateFromX - coordinateToX) == 1)
						&& (Math.abs(coordinateFromY - coordinateToY) == 1)))
			return true;
		return false;
	}
	// dodac skok o 2
}
