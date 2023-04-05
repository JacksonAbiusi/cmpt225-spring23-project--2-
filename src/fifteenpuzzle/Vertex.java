package fifteenpuzzle;

import java.util.ArrayList;

public class Vertex {
  private FifteenPuzzle puzzle;
  private ArrayList<Edge> edges;

  public Vertex(FifteenPuzzle puzzle) {
    this.puzzle = puzzle;
    this.edges = new ArrayList<Edge>();
  }

  public void addEdge(Vertex endVertex, Integer weight) {
    this.edges.add(new Edge(this, endVertex, weight));
  }

  public void removeEdge(Vertex endVertex) {
    this.edges.removeIf(edge -> edge.getEnd().equals(endVertex));
  }

  public ArrayList<Edge> getEdges(){
    return this.edges;
  }
  public FifteenPuzzle getData(){
    return puzzle;
  }
} 
