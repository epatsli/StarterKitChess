package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class Pawn implements PiecesMove{

	public Pawn() {
	}

	public void checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) throws InvalidMoveException {

		int coordinateFromY = coordinateFrom.getY();
		int coordinateToY = coordinateTo.getY();
		if (Math.abs(coordinateFromY - coordinateToY) == 1)
			checkMoveJumpOne(board, coordinateFrom, coordinateTo);
		else
			checkMoveJumpTwo(board, board, coordinateFrom, coordinateTo);

	}

	private boolean checkMoveJumpOne(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) {

		int coordinateFromX = coordinateFrom.getX();
		int coordinateFromY = coordinateFrom.getY();
		int coordinateToX = coordinateTo.getX();
		int coordinateToY = coordinateTo.getY();
		Piece piece = board.getPieceAt(coordinateFrom);
		CheckData check = new CheckData();
		List<Coordinate> ListMovePawn = new ArrayList<>();

		if ((check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.WHITE)) {
			if ((coordinateToX - coordinateFromX == 0) && (coordinateToY - coordinateFromY == 1))
				ListMovePawn.add(coordinateTo);
		}

		else if ((check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.BLACK)) {
			if ((coordinateToX - coordinateFromX == 0) && (coordinateFromY - coordinateToY == 1))
				ListMovePawn.add(coordinateTo);
		}

		else if ((!check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.WHITE)) {
			if ((Math.abs(coordinateToX - coordinateFromX) == 1) && (coordinateToY - coordinateFromY == 1))
				ListMovePawn.add(coordinateTo);
		}

		else if ((!check.checkFieldToisEmpty(board, coordinateTo)) && (piece.getColor() == Color.BLACK)) {
			if ((Math.abs(coordinateToX - coordinateFromX) == 1) && (coordinateFromY - coordinateToY == 1))
				ListMovePawn.add(coordinateTo);
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

		Coordinate coordinateBetweenY;
		Coordinate coordinateBetweenX;
		
		CheckData check = new CheckData();
		List<Coordinate> ListMovePawn = new ArrayList<>();

		if ((check.checkFieldToisEmpty(board, coordinateTo)) && (pieceFrom.getColor() == Color.WHITE)) {
			if ((pieceBetweenWhite == null)
					
					&& (pieceFrom.cjdfnfdkjnbkjfnbkjfnbjfnbkjngfbjngfbjgfbgf)
					&& (coordinateToX - coordinateFromX == 0) && (coordinateToY - coordinateFromY == 2))
				ListMovePawn.add(coordinateTo);
		}

		else if ((check.checkFieldToisEmpty(board, coordinateTo)) && (pieceFrom.getColor() == Color.BLACK)) {
			if ((pieceBetweenBlack == null)
					&& (boardManager.numberMoveOnChessboard[coordinateFromX][coordinateFromY] == 0)
					&& (coordinateToX - coordinateFromX == 0) && (coordinateFromY - coordinateToY == 2))
				ListMovePawn.add(coordinateTo);
		}
		return false;
	}



}
