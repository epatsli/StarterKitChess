package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

/**
 * Class for managing of basic operations on the Chess Board.
 *
 * @author Michal Bejm
 *
 */
public class BoardManager {

	private Board board = new Board();
	// int[][] numberMoveOnChessboard = new int[Board.SIZE][Board.SIZE];// my

	public BoardManager() {
		initBoard();
	}

	public BoardManager(List<Move> moves) {
		initBoard();
		for (Move move : moves) {
			addMove(move);
		}
	}

	public BoardManager(Board board) {
		this.board = board;
	}

	/**
	 * Getter for generated board
	 *
	 * @return board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Performs move of the chess piece on the chess board from one field to
	 * another.
	 *
	 * @param from
	 *            coordinates of 'from' field
	 * @param to
	 *            coordinates of 'to' field
	 * @return move object which includes moved piece and move type
	 * @throws InvalidMoveException
	 *             in case move is not valid
	 */
	public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {

		Move move = validateMove(from, to);

		addMove(move);

		// changeNumberMadeMove(from, to);// my

		return move;
	}

	/**
	 * Calculates state of the chess board.
	 *
	 * @return state of the chess board
	 * @throws InvalidMoveException
	 */
	public BoardState updateBoardState() throws InvalidMoveException {

		Color nextMoveColor = calculateNextMoveColor();

		boolean isKingInCheck = isKingInCheck(nextMoveColor);
		boolean isAnyMoveValid = isAnyMoveValid(nextMoveColor);

		BoardState boardState;
		if (isKingInCheck) {
			if (isAnyMoveValid) {
				boardState = BoardState.CHECK;
			} else {
				boardState = BoardState.CHECK_MATE;
			}
		} else {
			if (isAnyMoveValid) {
				boardState = BoardState.REGULAR;
			} else {
				boardState = BoardState.STALE_MATE;
			}
		}
		this.board.setState(boardState);
		return boardState;
	}

	/**
	 * Checks threefold repetition rule (one of the conditions to end the chess
	 * game with a draw).
	 *
	 * @return true if current state repeated at list two times, false otherwise
	 */
	public boolean checkThreefoldRepetitionRule() {

		// there is no need to check moves that where before last capture/en
		// passant/castling
		int lastNonAttackMoveIndex = findLastNonAttackMoveIndex();
		List<Move> omittedMoves = this.board.getMoveHistory().subList(0, lastNonAttackMoveIndex);
		BoardManager simulatedBoardManager = new BoardManager(omittedMoves);

		int counter = 0;
		for (int i = lastNonAttackMoveIndex; i < this.board.getMoveHistory().size(); i++) {
			Move moveToAdd = this.board.getMoveHistory().get(i);
			simulatedBoardManager.addMove(moveToAdd);
			boolean areBoardsEqual = Arrays.deepEquals(this.board.getPieces(),
					simulatedBoardManager.getBoard().getPieces());
			if (areBoardsEqual) {
				counter++;
			}
		}

		return counter >= 2;
	}

	/**
	 * Checks 50-move rule (one of the conditions to end the chess game with a
	 * draw).
	 *
	 * @return true if no pawn was moved or not capture was performed during
	 *         last 50 moves, false otherwise
	 */
	public boolean checkFiftyMoveRule() {

		// for this purpose a "move" consists of a player completing his turn
		// followed by his opponent completing his turn
		if (this.board.getMoveHistory().size() < 100) {
			return false;
		}

		for (int i = this.board.getMoveHistory().size() - 1; i >= this.board.getMoveHistory().size() - 100; i--) {
			Move currentMove = this.board.getMoveHistory().get(i);
			PieceType currentPieceType = currentMove.getMovedPiece().getType();
			if (currentMove.getType() != MoveType.ATTACK || currentPieceType == PieceType.PAWN) {
				return false;
			}
		}

		return true;
	}

	// PRIVATE

	private void initBoard() {

		this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
		this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
		this.board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
		this.board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
		this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
		this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
		this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
		}

		this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
		this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		this.board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		this.board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}
	}

	private void addMove(Move move) {

		addRegularMove(move);

		if (move.getType() == MoveType.CASTLING) {
			addCastling(move);
		} else if (move.getType() == MoveType.EN_PASSANT) {
			addEnPassant(move);
		}

		this.board.getMoveHistory().add(move);
	}

	private void addRegularMove(Move move) {
		Piece movedPiece = this.board.getPieceAt(move.getFrom());
		this.board.setPieceAt(null, move.getFrom());
		this.board.setPieceAt(movedPiece, move.getTo());

		performPromotion(move, movedPiece);
	}

	private void performPromotion(Move move, Piece movedPiece) {
		if (movedPiece == Piece.WHITE_PAWN && move.getTo().getY() == (Board.SIZE - 1)) {
			this.board.setPieceAt(Piece.WHITE_QUEEN, move.getTo());
		}
		if (movedPiece == Piece.BLACK_PAWN && move.getTo().getY() == 0) {
			this.board.setPieceAt(Piece.BLACK_QUEEN, move.getTo());
		}
	}

	private void addCastling(Move move) {
		if (move.getFrom().getX() > move.getTo().getX()) {
			Piece rook = this.board.getPieceAt(new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() + 1, move.getTo().getY()));
		} else {
			Piece rook = this.board.getPieceAt(new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() - 1, move.getTo().getY()));
		}
	}

	private void addEnPassant(Move move) {
		Move lastMove = this.board.getMoveHistory().get(this.board.getMoveHistory().size() - 1);
		this.board.setPieceAt(null, lastMove.getTo());
	}

	private Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException, KingInCheckException {

		// TODO please add implementation here

		CheckData valid = new CheckData();
		if (!valid.checkAllWarning(board, this, from, to))
			throw new InvalidMoveException();
		checkPossibilityMove(from, to);
		return settingType(from, to);
	}

	private boolean isKingInCheck(Color kingColor) throws InvalidMoveException {

		// TODO please add implementation here

		if ((kingColor == Color.WHITE) && (checkWhiteKingInCheck(board)))
			return true;
		else if ((kingColor == Color.BLACK) && (checkBlackKingInCheck(board)))
			return true;

		return false;

	}

	private boolean isAnyMoveValid(Color nextMoveColor) throws InvalidMoveException {

		// TODO please add implementation here

		if ((nextMoveColor == Color.WHITE) && (areAnyPossibleMoveWhite()))
			return true;
		else if ((nextMoveColor == Color.BLACK) && (areAnyPossibleMoveBlack()))
			return true;

		return false;
	}

	public Color calculateNextMoveColor() {// change with private
		if (this.board.getMoveHistory().size() % 2 == 0) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	private int findLastNonAttackMoveIndex() {
		int counter = 0;
		int lastNonAttackMoveIndex = 0;

		for (Move move : this.board.getMoveHistory()) {
			if (move.getType() != MoveType.ATTACK) {
				lastNonAttackMoveIndex = counter;
			}
			counter++;
		}

		return lastNonAttackMoveIndex;
	}

	private Move settingType(Coordinate coordinateFrom, Coordinate coordinateTo) {
		CheckData valid = new CheckData();

		Piece piece = board.getPieceAt(coordinateFrom);
		MoveType moveType = MoveType.ATTACK;

		if (valid.checkFieldToisEmpty(board, coordinateTo))
			moveType = MoveType.ATTACK;
		else if (valid.checkEqualColorPlayerFromAndTo(board, coordinateFrom, coordinateTo))
			moveType = MoveType.CAPTURE;

		Move move = new Move(coordinateFrom, coordinateTo, moveType, piece);
		return move;
	}

	private List<Coordinate> listWhiteFiguresOnBoard(Board board) {

		Piece piece;
		int coordinateX = 0;
		int coordinateY = 0;
		Coordinate coordinate;
		List<Coordinate> listWhitePiece = new ArrayList<Coordinate>();

		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				coordinate = new Coordinate(coordinateX + row, coordinateY + column);
				piece = board.getPieceAt(coordinate);
				if ((piece != null) && (piece.getColor() == Color.WHITE))
					listWhitePiece.add(coordinate);
			}
		}
		return listWhitePiece;
	}

	private List<Coordinate> listBlackFiguresOnBoard(Board board) {

		Piece piece;
		int coordinateX = 0;
		int coordinateY = 0;
		Coordinate coordinate;
		List<Coordinate> listBlackPiece = new ArrayList<Coordinate>();

		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				coordinate = new Coordinate(coordinateX + row, coordinateY + column);
				piece = board.getPieceAt(coordinate);
				if ((piece != null) && (piece.getColor() == Color.BLACK))
					listBlackPiece.add(coordinate);
			}
		}
		return listBlackPiece;
	}

	private Set<Coordinate> allPossibleMoveForWhite(Board board) throws InvalidMoveException {

		Set<Coordinate> setAllMoveWhite = new HashSet<>();
		Coordinate coordinateFrom = new Coordinate(0, 0);
		Coordinate coordinateTo;
		int coordinateX = 0;
		int coordinateY = 0;

		List<Coordinate> listWhitePiece = listWhiteFiguresOnBoard(board);

		for (int index = 0; index < listWhitePiece.size(); index++) {
			coordinateFrom = listWhitePiece.get(index);
			for (int row = 0; row < Board.SIZE; row++) {
				for (int column = 0; column < Board.SIZE; column++) {
					coordinateTo = new Coordinate(coordinateX + row, coordinateY + column);
					try {
						checkPossibilityMove(coordinateFrom, coordinateTo);
					} catch (InvalidMoveException e) {
						continue;
					}
					setAllMoveWhite.add(coordinateTo);
				}
			}
		}
		return setAllMoveWhite;
	}

	private boolean areAnyPossibleMoveWhite() throws InvalidMoveException {

		Set<Coordinate> possibleMoveInCheck = possibleMoveWhenWhiteKingIsInCheck();

		if (possibleMoveInCheck.isEmpty())
			return false;
		else
			return true;

	}

	private Set<Coordinate> possibleMoveWhenWhiteKingIsInCheck() throws InvalidMoveException {

		Set<Coordinate> setAllMoveWhite = allPossibleMoveForWhite(board);
		Set<Coordinate> setAllMoveBlack = allPossibleMoveForBlack(board);
		Set<Coordinate> possibleMoveInCheck = new HashSet<>();
		for (Coordinate iterator : setAllMoveWhite) {
			if (setAllMoveBlack.contains(iterator))
				;
			else {
				possibleMoveInCheck.add(iterator);
			}
		}
		return possibleMoveInCheck;
	}

	private Set<Coordinate> possibleMoveWhenBlackKingIsInCheck() throws InvalidMoveException {

		Set<Coordinate> setAllMoveWhite = allPossibleMoveForWhite(board);
		Set<Coordinate> setAllMoveBlack = allPossibleMoveForBlack(board);
		Set<Coordinate> possibleMoveInCheck = new HashSet<>();
		for (Coordinate iterator : setAllMoveBlack) {
			if (setAllMoveWhite.contains(iterator))
				;
			else {
				possibleMoveInCheck.add(iterator);
			}
		}
		return possibleMoveInCheck;
	}

	private boolean areAnyPossibleMoveBlack() throws InvalidMoveException {

		Set<Coordinate> possibleMoveInCheck = possibleMoveWhenBlackKingIsInCheck();

		if (possibleMoveInCheck.isEmpty())
			return false;
		else
			return true;
	}

	private Set<Coordinate> allPossibleMoveForBlack(Board board) throws InvalidMoveException {

		Set<Coordinate> setAllMoveBlack = new HashSet<>();
		Coordinate coordinateFrom = new Coordinate(0, 0);
		Coordinate coordinateTo;
		List<Coordinate> listBlackPiece = listBlackFiguresOnBoard(board);

		for (int index = 0; index < listBlackPiece.size(); index++) {
			coordinateFrom = listBlackPiece.get(index);
			for (int row = 0; row < Board.SIZE; row++) {
				for (int column = 0; column < Board.SIZE; column++) {
					coordinateTo = new Coordinate(row, column);
					try {
						checkPossibilityMove(coordinateFrom, coordinateTo);
					} catch (InvalidMoveException e) {
						continue;
					}
					setAllMoveBlack.add(coordinateTo);
				}
			}
		}
		return setAllMoveBlack;
	}

	private Coordinate returnPositionWhiteKing(Board board) throws InvalidMoveException {
		Coordinate possitionWhiteKing = new Coordinate(0, 0);
		Coordinate helpPossitionWhiteKing;
		Piece piece;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				helpPossitionWhiteKing = new Coordinate(row, column);
				piece = board.getPieceAt(helpPossitionWhiteKing);
				if (piece == (Piece.WHITE_KING))
					possitionWhiteKing = helpPossitionWhiteKing;
			}
		}
		return possitionWhiteKing;
	}

	private Coordinate returnPositionBlackKing(Board board) throws InvalidMoveException {
		Coordinate possitionBlackKing = new Coordinate(0, 0);
		Coordinate helpPossitionBlackKing;
		int coordinateX = 0;
		int coordinateY = 0;
		Piece piece;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int column = 0; column < Board.SIZE; column++) {
				helpPossitionBlackKing = new Coordinate(coordinateX + row, coordinateY + column);
				piece = board.getPieceAt(helpPossitionBlackKing);
				if (piece == (Piece.BLACK_KING))
					possitionBlackKing = helpPossitionBlackKing;
			}
		}
		return possitionBlackKing;
	}

	private boolean checkWhiteKingInCheck(Board board) throws InvalidMoveException {
		Coordinate possitionWhiteKing = returnPositionWhiteKing(board);
		Set<Coordinate> setAllMoveBlack = allPossibleMoveForBlack(board);
		if (setAllMoveBlack.contains(possitionWhiteKing))
			return true;
		return false;
	}

	private boolean checkBlackKingInCheck(Board board) throws InvalidMoveException {
		Coordinate possitionBlackKing = returnPositionBlackKing(board);
		Set<Coordinate> setAllMoveWhite = allPossibleMoveForWhite(board);
		if (setAllMoveWhite.contains(possitionBlackKing))
			return true;
		return false;
	}

	private void checkPossibilityMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		PieceType pieceType = board.getPieceAt(from).getType();

		if (pieceType.equals(PieceType.BISHOP)) {
			Bishop pieceBishop = new Bishop();
			pieceBishop.checkMove(board, from, to);

		} else if (pieceType.equals(PieceType.PAWN)) {
			Pawn piecePawn = new Pawn();
			piecePawn.checkMove(board, from, to);

		} else if (pieceType.equals(PieceType.KNIGHT)) {
			Knight pieceKnight = new Knight();
			pieceKnight.checkMove(board, from, to);
		} else if (pieceType.equals(PieceType.ROOK)) {
			Rook pieceRook = new Rook();
			pieceRook.checkMove(board, from, to);
		} else if (pieceType.equals(PieceType.QUEEN)) {
			Queen pieceQueen = new Queen();
			pieceQueen.checkMove(board, from, to);
		} else if (pieceType.equals(PieceType.KING)) {
			King pieceKing = new King();
			pieceKing.checkMove(board, from, to);
		}
	}

}
