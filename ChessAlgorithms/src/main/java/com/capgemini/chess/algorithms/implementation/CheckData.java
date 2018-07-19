package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.MoveOutOfBoundException;

public class CheckData {

	public void checkAllWarning(Board board, BoardManager boardManager, Coordinate coordinateFrom,
			Coordinate coordinateTo) throws InvalidMoveException {
		if (!checkFieldIsInSize(board, coordinateFrom))
			throw new MoveOutOfBoundException();
		if (!checkFieldFromisNoEmpty(board, coordinateFrom))
			throw new InvalidMoveException();
		checkEqualsIsFieldFromAndFieldTo(coordinateFrom, coordinateTo);
		if (!checkColorPlayerFrom(board, boardManager, coordinateFrom))
			throw new InvalidMoveException();
	}

	public boolean checkFieldIsInSize(Board board, Coordinate coordinate) {

		int coordinateX = coordinate.getX();
		int coordinateY = coordinate.getY();
		int size = Board.SIZE;

		if ((coordinateX >= 0) && (coordinateX < size) && (coordinateY >= 0) && (coordinateY < size))
			return true;
		return false;

	}

	public boolean checkFieldFromisNoEmpty(Board board, Coordinate coordinateFrom) {

		Piece piece = board.getPieceAt(coordinateFrom);

		if (piece == null)
			return false;

		return true;
	}

	public boolean checkEqualsIsFieldFromAndFieldTo(Coordinate coordinateFrom, Coordinate coordinateTo) {

		if ((coordinateFrom.getX() == coordinateTo.getX()) && (coordinateFrom.getY() == coordinateTo.getY()))
			return false;

		return true;
	}

	public boolean checkColorPlayerFrom(Board board, BoardManager boardManager, Coordinate coordinateFrom) {

		Piece piece = board.getPieceAt(coordinateFrom);

		if (boardManager.calculateNextMoveColor().equals(piece.getColor()))
			return true;

		return false;
	}

	public boolean checkEqualColorPlayerFromAndTo(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) {

		Piece piece1 = board.getPieceAt(coordinateFrom);
		Piece piece2 = board.getPieceAt(coordinateTo);

		if ((piece1.getColor()) != (piece2.getColor()))
			return true;

		return false;
	}

	public boolean checkFieldToisEmpty(Board board, Coordinate coordinateTo) {

		Piece piece = board.getPieceAt(coordinateTo);
		if (piece == null)
			return true;

		return false;
	}
}
