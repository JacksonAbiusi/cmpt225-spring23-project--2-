package fifteenpuzzle;

import java.util.ArrayList;

public class StateGraph {
  private ArrayList<Vertex> vertices;


  public StateGraph(boolean inputIsWeighted, boolean inputIsDirected) {
    this.vertices = new ArrayList<Vertex>();

  }

  public Vertex addVertex(FifteenPuzzle data) {
    Vertex newVertex = new Vertex(data);
    this.vertices.add(newVertex);
    return newVertex;
  }

  public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
    
    vertex1.addEdge(vertex2, weight);

  }

  public void removeEdge(Vertex vertex1, Vertex vertex2) {
    vertex1.removeEdge(vertex2);
  }

  public void removeVertex(Vertex vertex) {
    this.vertices.remove(vertex);
  }

  public ArrayList<Vertex> getVertices() {
    return this.vertices;
  }




  public Vertex getVertexByValue(FifteenPuzzle value) {
    for(Vertex v: this.vertices) { 
      if (v.getData() == value) {
        return v;
      }
    }

    return null;
  }
	
  public void print() {
    for(Vertex v: this.vertices) {
     
    }
  }
	
}