package fifteenpuzzle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

import fifteenpuzzle.FifteenPuzzle;
import fifteenpuzzle.FifteenPuzzle.Pair;

public class AdjacentVerticies {
    private Set<FifteenPuzzle> adj;
    private int size;
    private FifteenPuzzle puzzle;
	

    public AdjacentVerticies(){
        this.adj = new TreeSet<FifteenPuzzle>();
        this.size = adj.size();
    }


	public Set<FifteenPuzzle> adjNodes(FifteenPuzzle puzzle, int tile){
		Pair p = puzzle.blankTile();
		if((p.i - 1) >= 0){
			try {
				FifteenPuzzle temp = new FifteenPuzzle(puzzle, tile);
				int num = puzzle.getBoard()[p.i-1][p.j];
				temp.makeMove(temp.getBoard()[p.i-1][p.j], 1);
				temp.move = ("" + num + " D" + "\n");
				adj.add(temp);
			} catch (IllegalMoveException e) {
				// TODO: handle exception
			}
		}

		if((p.i + 1) <= puzzle.SIZE-1){
			try {
				FifteenPuzzle temp = new FifteenPuzzle(puzzle, tile);
				int num = puzzle.getBoard()[p.i+1][p.j];
				temp.makeMove(temp.getBoard()[p.i+1][p.j], 0);
				temp.move = ("" + num + " U" + "\n");
				adj.add(temp);
			} catch (IllegalMoveException e) {
				// TODO: handle exception
			}
			
		}

		if ((p.j + 1) <= puzzle.SIZE-1) {
			try {
				FifteenPuzzle temp = new FifteenPuzzle(puzzle, tile);
				int num = puzzle.getBoard()[p.i][p.j + 1];
				temp.makeMove(temp.getBoard()[p.i][p.j+1], 2);
				temp.move = ("" + num + " L" + "\n");
				adj.add(temp);
			} catch (IllegalMoveException e) {
				// TODO: handle exception
			}
			
			
		}

		if((p.j-1)>=0){
			try {
				FifteenPuzzle temp = new FifteenPuzzle(puzzle, tile);
				int num = puzzle.getBoard()[p.i][p.j-1];
				temp.makeMove(temp.getBoard()[p.i][p.j - 1], 3);
				temp.move = ("" + num + " R" + "\n");
				adj.add(temp);
			} catch (IllegalMoveException e) {
				// TODO: handle exception
			}
		}


		
		return adj;
	}
		
	public int getSize(){
		return size;
	}
}
