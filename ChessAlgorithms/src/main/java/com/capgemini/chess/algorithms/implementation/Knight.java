package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

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
		List<Coordinate> listMoveKnight = new ArrayList<>();

		if (((Math.abs(coordinateFromX - coordinateToX) == 1) && (Math.abs(coordinateFromY - coordinateToY) == 2))
				|| ((Math.abs(coordinateFromX - coordinateToX) == 2)
						&& (Math.abs(coordinateFromY - coordinateToY) == 1)))
			return true;
		return false;
	}

}
