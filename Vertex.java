
/*		08/15/2016
 * 		Kyeongmin Ha	
 * 		CUNY Queens College - CS 313 Data Structure 	
 * 		Project# 3
 * 		Professor: Joseph Svitak
 * 
 * 		Vertex.java
 */

public class Vertex {
	private String vertexName;
	private DoublyLinkedList adjList;

	public Vertex() {
		setData(null);
		setAdjList(null);
	}

	public Vertex(String d) {
		setData(d);
	}

	public Vertex(String d, DoublyLinkedList adjList) {
		setData(d);
		setAdjList(adjList);
	}

	public String getData() {
		return vertexName;
	}

	public void setData(String v) {
		vertexName = v;
	}

	public DoublyLinkedList getAdjList() {
		return adjList;
	}

	public void setAdjList(DoublyLinkedList adjList) {
		this.adjList = adjList;
	}

}
