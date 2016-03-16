package graphalg;

public class Edge implements Comparable{
	protected Object vert1;
	protected Object vert2;
	protected VertexPair vPair;
	protected int weight;

	public Edge(Object v1, Object v2, int weight) {
		vert1 = v1;
		vert2 = v2;
		this.weight = weight;
	}

	public int compareTo(Object o) {
		Edge compEdge = (Edge) o;
		if (this.weight < compEdge.weight) {
			return -1;
		} else if (this.weight == compEdge.weight) {
			return 0;
		} else {
			return 1;
		}
	}

	public String toString() {
		return "["+vert1+","+vert2+"]";
	}
}