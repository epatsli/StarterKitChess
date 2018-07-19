package com.capgemini.chess.algorithms.implementation.exceptions;

public class MoveOutOfBoundException extends InvalidMoveException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3978523718683533515L;

	public MoveOutOfBoundException() {
		super("Out of bound");
	}

}
