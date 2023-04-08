package fifteenpuzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
      StateGraph graph, FifteenPuzzle source) {

		Map<FifteenPuzzle, AStarNodeWrapper> nodeWrappers = new HashMap<>();
		TreeSet<AStarNodeWrapper> queue = new TreeSet<>();
		Set<FifteenPuzzle> shortestPathFound = new HashSet<FifteenPuzzle>();
	
		// Add source to queue
		int tile = 1;
		AStarNodeWrapper sourceWrapper =
			new AStarNodeWrapper(source, null, 0.0, source.heuristic(tile));
		nodeWrappers.put(source, sourceWrapper);
		queue.add(sourceWrapper);

		while (!queue.isEmpty()) {
			AStarNodeWrapper nodeWrapper = queue.pollFirst();
			FifteenPuzzle node = nodeWrapper.getNode();
		
			tile = node.solvedPortion.length() + 1;
			
			System.out.println(node);
			System.out.println("added node" + "Score:" + node.getScore() + " Move:" + node.move);
			shortestPathFound.add(node);
			
			

			// Have we reached the target? --> Build and return the path
			if (node.isSolved()) {
			  return buildPath(nodeWrapper);
			}
			// The algorithm should not choose states that lower the score
			// Iterate over all neighbors
			AdjacentVerticies adj = new AdjacentVerticies();
			Set<FifteenPuzzle> neighbors = adj.adjNodes(node, tile);
			
			for (FifteenPuzzle neighbor : neighbors) {
			  // Ignore neighbor if shortest path already found
			  System.out.println(neighbor.score(tile));

			  if (shortestPathFound.contains(neighbor)) {
				continue;
			  }
			  // Calculate total cost from start to neighbor via current node
			  node.heuristic(tile);
			  neighbor.heuristic(tile);
			
			  double cost = graph.edgeValue(node, neighbor);
			  double totalCostFromStart = nodeWrapper.getTotalCostFromStart() + cost;
	  
			  // Neighbor not yet discovered?
			  AStarNodeWrapper neighborWrapper = nodeWrappers.get(neighbor);
			  if (neighborWrapper == null) {
				neighborWrapper =
					new AStarNodeWrapper(
						neighbor, nodeWrapper, totalCostFromStart, node.heuristic(tile));
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
		System.out.println("number of arguments: " + args.length);
		for (int i = 0; i < args.length; i++) {
		System.out.println(args[i]);
		}

		if (args.length < 2) {
			System.out.println("File names are not specified");
			System.out.println("usage: java " + MethodHandles.lookup().lookupClass().getName() + " input_file output_file");
			return;
		}
		
		
		// TODO
		//File input = new File(args[0]);
		// parse the board like normal
		
		FifteenPuzzle in = new FifteenPuzzle(args[0]);
		
		//FifteenPuzzle out = new FifteenPuzzle(args[1]);
		BufferedReader r = new BufferedReader(new FileReader(args[1]));
		StringBuilder str = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while((line = r.readLine()) != null){
			str.append(line);
			str.append(ls);
		}
		str.deleteCharAt(str.length()-1);
		r.close();
		String outString = str.toString();
		StateGraph graph = new StateGraph();
		List<FifteenPuzzle> solvedList = Solver.findShortestPath(graph, in);

		Iterator<FifteenPuzzle> it = solvedList.iterator();
		String funcString = ""; //String from the list derived from shortestPath
		while(it.hasNext()){
			funcString += it.next().move;
		}
		if(funcString.equals(outString)){
			System.out.println("Perfect Sol");
		} else{
			System.out.println("solution");
			System.out.println(funcString);
			System.out.println(outString);
			System.out.println("Moves Required " + (solvedList.size()-1));
		}
		
		
		
		// turn puzzle into graph
		// check if it is solved
		// find path from current state to desired state
		// we will need to check if the move we made moved the tile into the desired position
		// keep note of tiles in position




		File output = new File(args[1]);
		// solve...
		//File output = new File(args[1]);

	}
}
