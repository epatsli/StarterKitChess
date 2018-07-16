package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Rook implements PiecesMove{

	// @Override
	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		Piece piece = board.getPieceAt(coordinateFrom);
		CheckData check = new CheckData();
		List<Coordinate> listMoveRook = new ArrayList<>();

		if (((coordinateFromX == coordinateToX) || (coordinateFromY == coordinateToY))&&(checkFieldBetweenFromAndTo(board, coordinateFrom, coordinateTo)))
			return true;
		return false;
	}
	
	private boolean checkFieldBetweenFromAndTo(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) throws InvalidMoveException {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();

		Coordinate coordinateBetweenY;
		Coordinate coordinateBetweenX;

		int differentCoordinateX = Math.abs(coordinateFromX - coordinateToX);
		int differentCoordinateY = Math.abs(coordinateFromY - coordinateToY);

		Piece pieceBetween;

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
		return false;
	}

}
