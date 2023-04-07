package fifteenpuzzle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

import fifteenpuzzle.FifteenPuzzle;

public class AdjacentVerticies {
    private Set<FifteenPuzzle> adj;
    private int size;
    private FifteenPuzzle puzzle;
	

    public AdjacentVerticies(FifteenPuzzle puzzle){
        adj = new TreeSet<FifteenPuzzle>();
        this.puzzle = puzzle;
        size = adj.size();
    }

    public Set<FifteenPuzzle> adjacentNodes(){

        for(int i = 0; i < puzzle.SIZE; i++){
			for(int j = 0; j < puzzle.SIZE; j++){
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 0);
			temp.move =(""+( 4 * i + j + 1 % 16) + "U" + "\n");
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 1);
			temp.move =(""+( 4 * i + j + 1 % 16) + "D" + "\n");
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 2);
			temp.move =(""+( 4 * i + j + 1 % 16) + "L" + "\n");
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 3);
			temp.move =(""+( 4 * i + j + 1 % 16) + "R" + "\n");
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
			
			}
		}
        return adj;
    }
		
}
