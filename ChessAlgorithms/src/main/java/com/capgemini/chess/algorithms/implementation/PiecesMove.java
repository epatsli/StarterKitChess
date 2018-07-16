package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface PiecesMove {

	public boolean checkMove(Board board, Coordinate coordinateFrom, Coordinate coordinateTo) throws InvalidMoveException;

	default List<Coordinate> chessboardStatus(int[][] numberMoveOnChessboard) {
		
		List<Coordinate> listPieceOnBoard = new ArrayList<>();
		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++)
				if (numberMoveOnChessboard[row][column] != -1)
					listPieceOnBoard.add(new Coordinate(row, column));
		}
		return listPieceOnBoard;
		// zaimplementowac liste biezacych figur na planszy
		// dla kazedj wspolrzednej zwraca czy znajduje sie tam jakis element
		// wynik zapisuje do listy (wspolrzedna, puste/pelne)
	}

	default void figuresOnBoardWhite() {
		// wylapac jedynie wspolrzedne, ktore sa pelne
	}

	default void figuresOnBoardBlack() {
		// wylapac jedynie wspolrzedne, ktore sa pelne
	}

	default void Map() {
		// zwarcam mape, gdzie klucze to wspolrzedne a wartosci - mozliwe ruchy
	}

}
