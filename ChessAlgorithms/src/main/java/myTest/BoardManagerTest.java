package myTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.CheckData;

public class BoardManagerTest {

	@Test
	public void shouldChangeNumberMadeMove() {

		// given
		BoardManager boardManager = new BoardManager();
		Coordinate c1 = new Coordinate(0, 0);
		Coordinate c2 = new Coordinate(3, 3);
		Coordinate c3 = new Coordinate(5, 0);
		boardManager.numberMadeMove();

		// when
		boardManager.changeNumberMadeMove(c1,c2);
		System.out.println();
		boardManager.changeNumberMadeMove(c2,c3);
		
		// then

		
	}
}
