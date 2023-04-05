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
	
	private static List<FifteenPuzzle> buildPath(AStarNodeWrapper nodeWrapper) {
		List<FifteenPuzzle> path = new ArrayList<>();
		while (nodeWrapper != null) {
		  path.add(nodeWrapper.getNode());
		  nodeWrapper = nodeWrapper.getPredecessor();
		}
		Collections.reverse(path);
		return path;
	  }

	public static List<FifteenPuzzle> findShortestPath(
      StateGraph graph, FifteenPuzzle source, FifteenPuzzle target, Double heuristic) {

		Map<FifteenPuzzle, AStarNodeWrapper> nodeWrappers = new HashMap<>();
		TreeSet<AStarNodeWrapper> queue = new TreeSet<>();
		Set<FifteenPuzzle> shortestPathFound = new HashSet<>();
	
		// Add source to queue
		int tile = 1;
		AStarNodeWrapper sourceWrapper =
			new AStarNodeWrapper(source, null, 0.0, source.heuristic(tile));
		nodeWrappers.put(source, sourceWrapper);
		queue.add(sourceWrapper);

		while (!queue.isEmpty()) {
			AStarNodeWrapper nodeWrapper = queue.pollFirst();
			FifteenPuzzle node = nodeWrapper.getNode();
			shortestPathFound.add(node);
	  
			// Have we reached the target? --> Build and return the path
			if (node.equals(target)) {
			  return buildPath(nodeWrapper);
			}
	  
			// Iterate over all neighbors
			Set<FifteenPuzzle> neighbors = graph.adjacentNodes(node);
			for (N neighbor : neighbors) {
			  // Ignore neighbor if shortest path already found
			  if (shortestPathFound.contains(neighbor)) {
				continue;
			  }
	  
			  // Calculate total cost from start to neighbor via current node
			  double cost = graph.edgeValue(node, neighbor).orElseThrow(IllegalStateException::new);
			  double totalCostFromStart = nodeWrapper.getTotalCostFromStart() + cost;
	  
			  // Neighbor not yet discovered?
			  AStarNodeWrapper<N> neighborWrapper = nodeWrappers.get(neighbor);
			  if (neighborWrapper == null) {
				neighborWrapper =
					new AStarNodeWrapper<>(
						neighbor, nodeWrapper, totalCostFromStart, heuristic.apply(neighbor));
				nodeWrappers.put(neighbor, neighborWrapper);
				queue.add(neighborWrapper);
			  }
	  
			  // Neighbor discovered, but total cost via current node is lower?
			  // --> Update costs and predecessor
			  else if (totalCostFromStart < neighborWrapper.getTotalCostFromStart()) {
				// The position in the TreeSet won't change automatically;
				// we have to remove and reinsert the node.
				// Because TreeSet uses compareTo() to identity a node to remove,
				// we have to remove it *before* we change the cost!
				queue.remove(neighborWrapper);
	  
				neighborWrapper.setTotalCostFromStart(totalCostFromStart);
				neighborWrapper.setPredecessor(nodeWrapper);
	  
				queue.add(neighborWrapper);
			  }
			}
		  }
	  
		  // All nodes were visited but the target was not found

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
