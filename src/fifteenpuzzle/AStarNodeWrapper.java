package fifteenpuzzle;

/**
 * Data structure containing a node, its predecessor, its total cost from the start, its minimum
 * remaining cost, and the cost sum.
 *
 * <p>Used by {@link AStarWithTreeSet} and {@link AStarWithPriorityQueue}.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class AStarNodeWrapper {
  private final FifteenPuzzle node;
  private AStarNodeWrapper predecessor;
  private double totalCostFromStart;
  private final double minimumRemainingCostToTarget;
  private double costSum;

  public AStarNodeWrapper(
      FifteenPuzzle node,
      AStarNodeWrapper predecessor,
      double totalCostFromStart,
      double minimumRemainingCostToTarget) {
    this.node = node;
    this.predecessor = predecessor;
    this.totalCostFromStart = totalCostFromStart;
    this.minimumRemainingCostToTarget = minimumRemainingCostToTarget;
    calculateCostSum();
  }

  private void calculateCostSum() {
    this.costSum = this.totalCostFromStart + this.minimumRemainingCostToTarget;
  }

  public FifteenPuzzle getNode() {
    return node;
  }

  public void setPredecessor(AStarNodeWrapper predecessor) {
    this.predecessor = predecessor;
  }

  public AStarNodeWrapper getPredecessor() {
    return predecessor;
  }

  public void setTotalCostFromStart(double totalCostFromStart) {
    this.totalCostFromStart = totalCostFromStart;
    calculateCostSum();
  }

  public double getTotalCostFromStart() {
    return totalCostFromStart;
  }

  @Override
  public int compare(AStarNodeWrapper other) {
    int compare = Double.compare(this.costSum, other.costSum);
    if (compare == 0) {
      compare = node.compare(other.node);
    }
    return compare;
  }

  public double heuristic(int tile){
		Pair curr = findCoord(tile);
		Pair dest = new Pair((tile%4)-1, (tile/4));
		double dx = Math.abs(curr.i - dest.i);
    	double dy = Math.abs(curr.j - dest.j);
    	return  (dx + dy);

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
				if (node.board[i][j] == tile)
					return new Pair(i, j);
		return null;
	}
  // Not overriding equals() and hashcode(), to use Object's methods.
  // Object's methods use object identity, which is much faster.
  // It's sufficient as within the algorithm, we have only one AStarNodeWrapper
  // instance per node.

  @Override
  public String toString() {
    return "AStarNodeWrapperForTreeSet{"
        + "node="
        + node
        + ", predecessor="
        + (predecessor != null ? predecessor.getNode().toString() : "")
        + ", totalCostFromStart="
        + totalCostFromStart
        + ", minimumRemainingCostToTarget="
        + minimumRemainingCostToTarget
        + ", costSum="
        + costSum
        + '}';
  }


} 
