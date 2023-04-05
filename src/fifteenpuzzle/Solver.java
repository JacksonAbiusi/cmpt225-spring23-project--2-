package fifteenpuzzle;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Solver {
	
	public static <FifteenPuzzle extends Comparable<FifteenPuzzle>> List<FifteenPuzzle> findShortestPath(
      StateGraph java, FifteenPuzzle source, FifteenPuzzle target, Function<FifteenPuzzle, Double> heuristic) {
    Map<FifteenPuzzle, AStarNodeWrapper<FifteenPuzzle>> nodeWrappers = new HashMap<>();
    TreeSet<AStarNodeWrapper<FifteenPuzzle>> queue = new TreeSet<>();
    Set<FifteenPuzzle> shortestPathFound = new HashSet<>();

	return null;
	  }

	public static void main(String[] args) throws IOException, BadBoardException {
//		System.out.println("number of arguments: " + args.length);
//		for (int i = 0; i < args.length; i++) {
//			System.out.println(args[i]);
//		}

		if (args.length < 2) {
			System.out.println("File names are not specified");
			System.out.println("usage: java " + MethodHandles.lookup().lookupClass().getName() + " input_file output_file");
			return;
		}
		
		
		// TODO
		//File input = new File(args[0]);
		File input = new File(args[0]);
		// parse the board like normal
		
		FifteenPuzzle puzzle = new FifteenPuzzle(args[0]);
		
		
		// turn puzzle into graph
		// check if it is solved
		// find path from current state to desired state
		// we will need to check if the move we made moved the tile into the desired position
		// keep note of tiles in position
		Random rand = new Random();
		
		Boolean solved = false;
		
		SolvingAlgorithm solve = new SolvingAlgorithm(puzzle);
		


		while(solved == false){
		for(int i = 0; i < puzzle.SIZE; i++){
			for(int j = 0; j < puzzle.SIZE; j++){
				ArrayList<FifteenPuzzle> states = new ArrayList<FifteenPuzzle>();
				
				try {
					FifteenPuzzle temp = puzzle;
					temp.makeMove(temp.getBoard()[i][j], 0);
					temp.moves.add(0);
					states.add(temp);
				} catch (IllegalMoveException e) {
					// TODO: handle exception
				}
				try {
					FifteenPuzzle temp = puzzle;
					temp.makeMove(temp.getBoard()[i][j], 1);
					temp.moves.add(1);
					states.add(temp);
				} catch (IllegalMoveException e) {
					// TODO: handle exception
				}
				try {
					FifteenPuzzle temp = puzzle;
					temp.makeMove(temp.getBoard()[i][j], 2);
					temp.moves.add(2);
					states.add(temp);
				} catch (IllegalMoveException e) {
					// TODO: handle exception
				}
				try {
					FifteenPuzzle temp = puzzle;
					temp.makeMove(temp.getBoard()[i][j], 3);
					temp.moves.add(3);
					states.add(temp);
				} catch (IllegalMoveException e) {
					// TODO: handle exception
				}
			
			
			}
		}

		//puzzle.makeMove(0, 0);

	}




		File output = new File(args[1]);
		// solve...
		//File output = new File(args[1]);

	}
}
