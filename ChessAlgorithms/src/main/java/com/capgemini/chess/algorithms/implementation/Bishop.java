package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Bishop implements PiecesMove {

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		CheckData valid=new CheckData();

		if ((Math.abs(coordinateToX - coordinateFromX) == Math.abs(coordinateToY - coordinateFromY))
				&& checkFieldBetweenFromAndTo(board, coordinateFrom, coordinateTo)
				&& ((valid.checkFieldToisEmpty(board, coordinateTo))
						|| (valid.checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))))
			return true;
		else
			throw new InvalidMoveException();
	}

	private boolean checkFieldBetweenFromAndTo(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();

		int differentCoordinateY = Math.abs(coordinateFromY - coordinateToY) - 1;

		Piece pieceBetween;
		Coordinate coordinateBetweenXY;

		if ((coordinateFromY > coordinateToY) && (coordinateFromX > coordinateToX)) {
			for (int i = 1; i <= differentCoordinateY; i++) {

				coordinateBetweenXY = new Coordinate(coordinateFromX - i, coordinateFromY - i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);

				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		} else if ((coordinateFromY < coordinateToY) && (coordinateFromX < coordinateToX)) {
			for (int i = 1; i <= differentCoordinateY; i++) {
				coordinateBetweenXY = new Coordinate(coordinateFromX + i, coordinateFromY + i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);
				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		} else if ((coordinateFromY > coordinateToY) && (coordinateFromX < coordinateToX)) {
			for (int i = 1; i <= differentCoordinateY; i++) {
				coordinateBetweenXY = new Coordinate(coordinateFromX + i, coordinateFromY - i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);
				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		} else if ((coordinateFromY < coordinateToY) && (coordinateFromX > coordinateToX)) {
			for (int i = 1; i <= differentCoordinateY; i++) {
				coordinateBetweenXY = new Coordinate(coordinateFromX - i, coordinateFromY + i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);
				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		}
		return false;
	}
}
