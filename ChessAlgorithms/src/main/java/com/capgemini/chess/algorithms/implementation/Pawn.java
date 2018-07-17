package com.capgemini.chess.algorithms.implementation;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Pawn implements PiecesMove {

	public Pawn() {
	}

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo)
			throws InvalidMoveException {

		int coordinateFromY = coordinateFrom.getY();
		int coordinateToY = coordinateTo.getY();
		CheckData valid=new CheckData();

		if ((((Math.abs(coordinateFromY - coordinateToY) == 1) && (checkMoveJumpOne(board, coordinateFrom, coordinateTo)))
				|| ((Math.abs(coordinateFromY - coordinateToY) == 2)
						&& (checkMoveJumpTwo(board, coordinateFrom, coordinateTo))))&& ((valid.checkFieldToisEmpty(board, coordinateTo))
								|| (valid.checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))))
			return true;
		else
			throw new InvalidMoveException();
	}

	private boolean checkMoveJumpOne(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		Piece piece = board.getPieceAt(coordinateFrom);
		CheckData check = new CheckData();

		if ((check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.WHITE)) {
			if ((coordinateToX - coordinateFromX == 0) && (coordinateToY - coordinateFromY == 1))
				return true;
		}

		else if ((check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.BLACK)) {
			if ((coordinateToX - coordinateFromX == 0) && (coordinateFromY - coordinateToY == 1))
				return true;
		}

		else if ((!check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.WHITE)) {
			if ((Math.abs(coordinateToX - coordinateFromX) == 1) && (coordinateToY - coordinateFromY == 1))
				return true;
		}

		else if ((!check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.BLACK)) {
			if ((Math.abs(coordinateToX - coordinateFromX) == 1) && (coordinateFromY - coordinateToY == 1))
				return true;
		}
		return false;
	}

	private boolean checkMoveJumpTwo(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		Piece pieceFrom = board.getPieceAt(coordinateFrom);
		Coordinate coordinateBetweenWhite = new Coordinate(coordinateFromX, coordinateFromY + 1);
		Coordinate coordinateBetweenBlack = new Coordinate(coordinateFromX, coordinateFromY - 1);
		Piece pieceBetweenWhite = board.getPieceAt(coordinateBetweenWhite);
		Piece pieceBetweenBlack = board.getPieceAt(coordinateBetweenBlack);

		CheckData check = new CheckData();

		if ((check.checkFieldToisEmpty(board, coordinateTo)) && (pieceFrom.getColor() == Color.WHITE)) {
			if ((pieceBetweenWhite == null) && (coordinateFromY == 1) && (coordinateToX - coordinateFromX == 0)
					&& (coordinateToY - coordinateFromY == 2))
				return true;
		}

		else if ((check.checkFieldToisEmpty(board, coordinateTo)) && (pieceFrom.getColor() == Color.BLACK)) {
			if ((pieceBetweenBlack == null) && (coordinateFromY == 6) && (coordinateToX - coordinateFromX == 0)
					&& (coordinateFromY - coordinateToY == 2))
				return true;
		}
		return false;
	}
}
