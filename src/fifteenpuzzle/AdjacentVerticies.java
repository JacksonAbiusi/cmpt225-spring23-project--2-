package fifteenpuzzle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

import fifteenpuzzle.FifteenPuzzle;
import javafx.scene.Node;

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
			temp.moves.add(0);
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 1);
			temp.moves.add(1);
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 2);
			temp.moves.add(2);
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
		try {
			FifteenPuzzle temp = puzzle;
			temp.makeMove(temp.getBoard()[i][j], 3);
			temp.moves.add(3);
			adj.add(temp);
		} catch (IllegalMoveException e) {
			// TODO: handle exception
		}
			
			}
		}
        return adj;
    }
		
}
