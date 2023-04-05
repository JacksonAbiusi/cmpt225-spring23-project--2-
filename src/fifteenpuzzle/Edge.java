package fifteenpuzzle;



public class Edge {
    private Vertex start;
    private Vertex end;

    public Edge(Vertex startV, Vertex endV, Integer inputWeight) {
        this.start = startV;
        this.end = endV;
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vertex getEnd() {
        return this.end;
    }

 
    

}