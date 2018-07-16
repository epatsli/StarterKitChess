package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;
import com.capgemini.chess.algorithms.implementation.CheckData;
import com.capgemini.chess.algorithms.data.enums.*;

public class CheckDataTest {

	@Test
	public void shouldCheckFieldFromIsInSize() {

		// given
		CheckData FieldFrom = new CheckData();
		Coordinate c1 = new Coordinate(2, 7);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		assertEquals(true, FieldFrom.checkFieldFromIsInSize(boardManager.getBoard(), c1));
		
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldCheckFieldFromIsInSizeTrowsException() throws Exception {

		// given
		CheckData FieldFrom = new CheckData();
		Coordinate c1 = new Coordinate(0, 8);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		FieldFrom.checkFieldFromIsInSize(boardManager.getBoard(), c1);
	}

	@Test
	public void shouldCheckFieldToIsInSize() {

		// given
		CheckData FieldFrom = new CheckData();
		Coordinate c1 = new Coordinate(2, 7);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		assertEquals(true, FieldFrom.checkFieldToIsInSize(boardManager.getBoard(), c1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldCheckFieldToIsInSizeTrowsException() throws Exception {

		// given
		CheckData FieldFrom = new CheckData();
		Coordinate c1 = new Coordinate(-2, 4);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		FieldFrom.checkFieldToIsInSize(boardManager.getBoard(), c1);
	}

	@Test
	public void shouldCheckFieldFromisNoEmpty() {

		// given
		CheckData FieldFrom = new CheckData();
		Coordinate c1 = new Coordinate(4, 4);
		Coordinate c2 = new Coordinate(3, 0);

		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		assertEquals(false, FieldFrom.checkFieldFromisNoEmpty(boardManager.getBoard(), c1));
		assertEquals(true, FieldFrom.checkFieldFromisNoEmpty(boardManager.getBoard(), c2));

	}

	@Test
	public void shouldCheckEqualsIsFieldFromAndFieldTo() {

		// given
		CheckData checkFieldFrom = new CheckData();

		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);

		Coordinate c1 = new Coordinate(5, 1);
		Coordinate c2 = new Coordinate(5, 2);

		// then
		assertEquals(true, checkFieldFrom.checkEqualsIsFieldFromAndFieldTo(c1, c2));

		Coordinate c3 = new Coordinate(5, 1);

		assertEquals(false, checkFieldFrom.checkEqualsIsFieldFromAndFieldTo(c1, c3));
	}

	@Test
	public void shouldCheckColorPlayerFrom() {

		// given
		CheckData FieldFrom = new CheckData();
		Coordinate c1 = new Coordinate(0, 7);
		Coordinate c2 = new Coordinate(7, 0);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		assertEquals(false, FieldFrom.checkColorPlayerFrom(boardManager.getBoard(), boardManager, c1));
		assertEquals(true, FieldFrom.checkColorPlayerFrom(boardManager.getBoard(), boardManager, c2));

	}

	@Test
	public void shouldCheckEqualColorPlayerFromAndTo() {

		// given
		CheckData Field = new CheckData();
		Coordinate c1 = new Coordinate(0, 7);
		Coordinate c2 = new Coordinate(7, 0);
		Coordinate c3 = new Coordinate(7, 1);
		Coordinate c4 = new Coordinate(6, 1);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		assertEquals(true, Field.checkEqualColorPlayerFromAndTo(boardManager.getBoard(), c1, c2));
		assertEquals(false, Field.checkEqualColorPlayerFromAndTo(boardManager.getBoard(), c3, c4));

	}

	@Test
	public void shouldCheckFieldToisEmpty() {

		// given
		CheckData FieldTo = new CheckData();
		Coordinate c1 = new Coordinate(6, 1);
		Coordinate c2 = new Coordinate(4, 4);
		List<Move> moves = new ArrayList<>();

		// when
		BoardManager boardManager = new BoardManager(moves);

		// then
		assertEquals(false, FieldTo.checkFieldToisEmpty(boardManager.getBoard(), c1));
		assertEquals(true, FieldTo.checkFieldToisEmpty(boardManager.getBoard(), c2));

	}

}
