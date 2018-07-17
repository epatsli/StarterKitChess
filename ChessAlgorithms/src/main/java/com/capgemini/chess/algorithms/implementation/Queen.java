package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Queen implements PiecesMove {

	@Override
	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();

		if ((((Math.abs(coordinateToX - coordinateFromX) == Math.abs(coordinateToY - coordinateFromY))
				&& checkFieldBetweenFromAndToBishop(board, coordinateFrom, coordinateTo))
				|| (((coordinateFromX == coordinateToX) || (coordinateFromY == coordinateToY))
						&& (checkFieldBetweenFromAndToRook(board, coordinateFrom, coordinateTo))))
				&& ((checkFieldToisEmpty(board, coordinateTo))
						|| (checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))))
			return true;

		return false;
	}

	private boolean checkFieldBetweenFromAndToBishop(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();

		int differentCoordinateX = Math.abs(coordinateFromX - coordinateToX);
		int differentCoordinateY = Math.abs(coordinateFromY - coordinateToY);

		Piece pieceBetween;
		Coordinate coordinateBetweenXY;

		if ((coordinateFromY > coordinateToY) && (coordinateFromX > coordinateToX)) {
			for (int i = 1; i < differentCoordinateY; i++) {

				coordinateBetweenXY = new Coordinate(coordinateFromX - i, coordinateFromY - i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);

				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		} else if ((coordinateFromY < coordinateToY) && (coordinateFromX < coordinateToX)) {
			for (int i = 1; i < differentCoordinateY; i++) {
				coordinateBetweenXY = new Coordinate(coordinateFromX + i, coordinateFromY + i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);
				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		} else if ((coordinateFromY > coordinateToY) && (coordinateFromX < coordinateToX)) {
			for (int i = 1; i < differentCoordinateY; i++) {
				coordinateBetweenXY = new Coordinate(coordinateFromX + i, coordinateFromY - i);
				pieceBetween = board.getPieceAt(coordinateBetweenXY);
				if (pieceBetween == null)
					;
				else
					throw new InvalidMoveException();
			}
			return true;
		} else if ((coordinateFromY < coordinateToY) && (coordinateFromX > coordinateToX)) {
			for (int i = 1; i < differentCoordinateY; i++) {
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

	private boolean checkFieldBetweenFromAndToRook(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();

		Coordinate coordinateBetweenY;
		Coordinate coordinateBetweenX;

		int differentCoordinateX = Math.abs(coordinateFromX - coordinateToX);
		int differentCoordinateY = Math.abs(coordinateFromY - coordinateToY);

		Piece pieceBetween;
		if ((differentCoordinateY == 1) || (differentCoordinateX == 1))
			return true;
		else {
			if ((coordinateFromY > coordinateToY) && (coordinateFromX == coordinateToX)) {

				for (int i = 1; i < differentCoordinateY; i++) {
					coordinateBetweenY = new Coordinate(coordinateFromX, coordinateFromY - i);
					pieceBetween = board.getPieceAt(coordinateBetweenY);
					if (pieceBetween == null)
						return true;
					else
						throw new InvalidMoveException();

				}
			} else if ((coordinateToY > coordinateFromY) && (coordinateFromX == coordinateToX)) {
				for (int i = 1; i < differentCoordinateY; i++) {
					coordinateBetweenY = new Coordinate(coordinateFromX, coordinateFromY + i);
					pieceBetween = board.getPieceAt(coordinateBetweenY);
					if (pieceBetween == null)
						return true;
					else
						throw new InvalidMoveException();
				}
			} else if ((coordinateFromX > coordinateToX) && (coordinateFromY == coordinateToY)) {
				for (int i = 1; i < differentCoordinateX; i++) {
					coordinateBetweenX = new Coordinate(coordinateFromX - i, coordinateFromY);
					pieceBetween = board.getPieceAt(coordinateBetweenX);
					if (pieceBetween == null)
						return true;
					else
						throw new InvalidMoveException();
				}
			} else if ((coordinateToX > coordinateFromX) && (coordinateFromY == coordinateToY)) {
				for (int i = 1; i < differentCoordinateX; i++) {
					coordinateBetweenX = new Coordinate(coordinateFromX + i, coordinateFromY);
					pieceBetween = board.getPieceAt(coordinateBetweenX);
					if (pieceBetween == null)
						return true;
					else
						throw new InvalidMoveException();
				}
			}
		}
		return false;
	}

}
