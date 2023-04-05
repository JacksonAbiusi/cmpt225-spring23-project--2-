package fifteenpuzzle;

import java.io.*;
import java.util.ArrayList;

public class FifteenPuzzle{
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;

	public static int SIZE;

	public int board[][];
	public ArrayList<Integer> moves;// array of moves from intital
	public String solvedPortion;
	

	private void checkBoard() throws BadBoardException {
		int[] vals = new int[SIZE * SIZE];

		// check that the board contains all number 0...15
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j]<0 || board[i][j]>=SIZE*SIZE)
					throw new BadBoardException("found tile " + board[i][j]);
				vals[board[i][j]] += 1;
			}
		}

		for (int i = 0; i < vals.length; i++)
			if (vals[i] != 1)
				throw new BadBoardException("tile " + i +
											" appears " + vals[i] + "");

	}

	/**
	 * @param fileName
	 * @throws FileNotFoundException if file not found
	 * @throws BadBoardException     if the board is incorrectly formatted Reads a
	 *                               board from file and creates the board
	 */
	public FifteenPuzzle(String fileName) throws IOException, BadBoardException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		SIZE = Integer.parseInt(br.readLine());
		board = new int[SIZE][SIZE];
		moves = new ArrayList<Integer>();
		solvedPortion = curSolved();
		int c1, c2, s;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				c1 = br.read();
				c2 = br.read();
				s = br.read(); // skip the space
				if (s != ' ' && s != '\n') {
					br.close();
					throw new BadBoardException("error in line " + i);
				}
				if (c1 == ' ')
					c1 = '0';
				if (c2 == ' ')
					c2 = '0';
				board[i][j] = 10 * (c1 - '0') + (c2 - '0');
			}
		}
		checkBoard();

		br.close();
	}

	private class Pair {
		int i, j;

		Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	private Pair findCoord(int tile) {
		int i = 0, j = 0;
		for (i = 0; i < SIZE; i++)
			for (j = 0; j < SIZE; j++)
				if (board[i][j] == tile)
					return new Pair(i, j);
		return null;
	}

	/**
	 * Get the number of the tile, and moves it to the specified direction
	 * 
	 * @throws IllegalMoveException if the move is illegal
	 */
	public void makeMove(int tile, int direction) throws IllegalMoveException {
		Pair p = findCoord(tile);
		if (p == null)
			throw new IllegalMoveException("tile " + tile + " not found");
		int i = p.i;
		int j = p.j;

		// the tile is in position [i][j]
		switch (direction) {
		case UP: {
			if (i > 0 && board[i - 1][j] == 0) {
				board[i - 1][j] = tile;
				board[i][j] = 0;
				break;
			} else
				throw new IllegalMoveException("" + tile + "cannot move UP");
		}
		case DOWN: {
			if (i < SIZE - 1 && board[i + 1][j] == 0) {
				board[i + 1][j] = tile;
				board[i][j] = 0;
				break;
			} else
				throw new IllegalMoveException("" + tile + "cannot move DOWN");
		}
		case RIGHT: {
			if (j < SIZE - 1 && board[i][j + 1] == 0) {
				board[i][j + 1] = tile;
				board[i][j] = 0;
				break;
			} else
				throw new IllegalMoveException("" + tile + "cannot move LEFT");
		}
		case LEFT: {
			if (j > 0 && board[i][j - 1] == 0) {
				board[i][j - 1] = tile;
				board[i][j] = 0;
				break;
			} else
				throw new IllegalMoveException("" + tile + "cannot move LEFT");
		}
		default:
			throw new IllegalMoveException("Unexpected direction: " + direction);
		}
	

	}

	/**
	 * @return true if and only if the board is solved, i.e., the board has all
	 *         tiles in their correct positions
	 */
	public boolean isSolved() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (board[i][j] != (4 * i + j + 1) % 16)
					return false;
		return true;
	}

	public String curSolved() {
		String s = "";
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (board[i][j] != (4 * i + j + 1) % 16)
					s += board[i][j];
		return s;
	}

	private String num2str(int i) {
		if (i == 0)
			return "  ";
		else if (i < 10)
			return " " + Integer.toString(i);
		else
			return Integer.toString(i);
	}

	public int[][] getBoard(){
		return board;
	}

	public String toString() {
		String ans = "";
		for (int i = 0; i < SIZE; i++) {
			ans += num2str(board[i][0]);
			for (int j = 1; j < SIZE; j++)
				ans += " " + num2str(board[i][j]);
			ans += "\n";
		}
		return ans;
	}

	public Integer nextMove(){
		// find which tile needs to be moved to its spot
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (board[i][j] != (4 * i + j + 1) % 16)
					return board[i][j];
		return null;
	}

	public int distanceMove(int tile, int direction) throws IllegalMoveException{
		// calculate distance in moves from where tile should be to where it is
		Pair curr = findCoord(tile);
		Pair dest = new Pair((tile%4)-1, (tile/4));
		int score = 0;

		switch (direction) {
			case UP: {
				if(curr.j > dest.j)
				score++;
				if (curr.j < dest.j)
				score--;
			}
			case DOWN: {
				if(curr.j < dest.j)
					score++;
				if (curr.j > dest.j)
					score++;
			}
			case RIGHT: {
				if(curr.i < dest.i)
					score++;
				if (curr.i > dest.i)
					score--;
			}
			case LEFT: {
				if (curr.i > dest.i)
					score++;
				if (curr.i < dest.i)
					score--;
			
			}
			
		}
		return score;
	
	}

	@Override
	public boolean equals(Object other) {
		if(this.toString().equals(other.toString()))
		return true;

		return false;
	}

	@Override
	public int hashCode() {
		return board.hashCode() + solvedPortion.hashCode();
	}


	public int compareTo(FifteenPuzzle other, int tile) {
		int num = 0;
		

		if(this.heuristic(tile) < other.heuristic(tile)){
			num = 1;
		}

		if(solvedPortion.length() > other.solvedPortion.length())
			num = 1;

		if (solvedPortion.length() == other.solvedPortion.length())
			num = 0;

		if (solvedPortion.length() < other.solvedPortion.length())
			num = -1;
		
		return num;
	}

	public double heuristic(int tile){
		Pair curr = findCoord(tile);
		Pair dest = new Pair((tile%4)-1, (tile/4));
		double dx = Math.abs(curr.i - dest.i);
    	double dy = Math.abs(curr.j - dest.j);
    	return  (dx + dy);

	}

	

	


}
