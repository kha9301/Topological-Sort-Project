
/*		08/15/2016
 * 		Kyeongmin Ha	
 * 		CUNY Queens College - CS 313 Data Structure 	
 * 		Project# 3
 * 		Professor: Joseph Svitak
 * 
 * 		Conversion.java
 */

import java.io.BufferedReader;

public class Conversion<AnyType> {
	//Stores Edges (both 0s and 1s)
	List<AnyType> edgesList = new DoublyLinkedList<AnyType>();
	//Stores Vertices
	List<AnyType> verticesList = new DoublyLinkedList<AnyType>();

	Conversion(BufferedReader br) {
		System.out.println("\nThe Matrix \n");

		try {
			String strLine = null;

			/*-------------------------------------STORE VERTEX NAMES INTO LINKED LIST 'b'  --------------------------------------*/

			String fLine = br.readLine();
			String[] vertices = fLine.split("\\s+");
			Vertex[] VerArray = new Vertex[vertices.length];

			for (int i = 0; i < vertices.length; i++) {
				if (!vertices[i].equals(""))
					VerArray[i] = new Vertex(vertices[i]);
				verticesList.add((AnyType) VerArray[i]);
				// a doubly linked list of vertices
				System.out.print(vertices[i] + " ");
			}
			System.out.println();

			/*-------------------------------------STORE VERTEX NAMES INTO LINKED LIST 'b'  --------------------------------------*/

			/*-----------STORE 0s and 1s TO CORRESPONIDNG VERTEX NAMES INTO LINKED LIST 'a'  --------------------------------------*/

			while ((strLine = br.readLine()) != null) {
				int x = 0;// ensures every LinkedList does not count empty space
				String[] splited = strLine.split("\\s+");

				for (int i = 0; i < splited.length; i++) {
					if (splited[i].equals("0")) {
						// 0s stay because they don't need for adjacency list
						edgesList.add((AnyType) new Vertex("0"));
						System.out.print(splited[i] + " ");
						x++;
					}
					if (splited[i].equals("1")) {
						// convert 1s to corresponding vertex
						edgesList.add((AnyType) VerArray[x]);
						// a doubly linked list of vertices.
						x++;
						System.out.print(splited[i] + " ");
					}
				} // for
				System.out.println();
			} // while

		} catch (Exception e) {
			System.out.println(e);
		}
		/*-----------STORE 0s and 1s TO CORRESPONIDNG VERTEX NAMES INTO LINKED LIST 'a'  --------------------------------------*/

		int numOfVertex = verticesList.size(); // reference

		if (isMatrixProper(numOfVertex, edgesList)) {
			DoublyLinkedList[] adjacencyList = new DoublyLinkedList[numOfVertex];

			// STORE ADJACENCY LISTs INTO LINKED LIST ARRAY
			adjacencyList = storeAdjacecyList(numOfVertex, adjacencyList);

			Vertex[] cleanVerArray = new Vertex[adjacencyList.length];
			Vertex[] VericesCarriesAdjList = new Vertex[adjacencyList.length];

			VericesCarriesAdjList = vertexContainAdjList(cleanVerArray, adjacencyList);

			printAdjacencyList(VericesCarriesAdjList);

			//topologicsalSort
			TopologicalSort a = new TopologicalSort(VericesCarriesAdjList);
		} else {
			System.out.println("Matrix is not proper");
		}
	}

	private void printAdjacencyList(Vertex[] vericesCarriesAdjList) {
		// TODO Auto-generated method stub
		System.out.println("\nAdjacency List From The Matrix\n");

		for (int i = 0; i < vericesCarriesAdjList.length; i++) {
			System.out.print(vericesCarriesAdjList[i].getData() + " : ");
			for (int j = 1; j <= vericesCarriesAdjList[i].getAdjList().size(); j++) {
				System.out.print(vericesCarriesAdjList[i].getAdjList().get(j) + " ");
			}
			System.out.println();
		}
	}

	private Vertex[] vertexContainAdjList(Vertex[] cleanVerArray, DoublyLinkedList[] adjacencyList) {
		// cleanVerArray[]

		for (int i = 0; i < cleanVerArray.length; i++) {
			DoublyLinkedList adjList = new DoublyLinkedList();
			for (int j = 1; j < adjacencyList[i].size(); j++) {
				// System.out.println(((Vertex) adjacencyList[i].get(j +
				// 1)).getData());
				adjList.add(((Vertex) adjacencyList[i].get(j + 1)).getData());
			}

			cleanVerArray[i] = new Vertex(((Vertex) adjacencyList[i].get(1)).getData(), adjList);
		}
		return cleanVerArray;
	}

	public DoublyLinkedList[] storeAdjacecyList(int num, DoublyLinkedList[] aLists) {

		for (int i = 0; i < num; i++) {
			DoublyLinkedList answers = new DoublyLinkedList();
			aLists[i] = answers;
			answers.add(verticesList.get(i + 1));
		} // The name of each vertices comes in front of the list.

		int placeholder = 1;
		while (placeholder != num + 1) {
			for (int i = 0; i < num; i++) {
				if (!((Vertex) edgesList.get((i * num) + placeholder)).getData().equals("0"))
					aLists[i].add(edgesList.get((i * num) + placeholder));

				// this stores each adjcaent vertices after each name of the
				// vertices
			}
			placeholder++;
		} // The adjacency list for a vertex comes after each name.

		return aLists;
	}

	public boolean isMatrixProper(int num, List<AnyType> edgesList) {
		if (edgesList.size() != (num * num)) {
			return false;
		}
		return true;
	}

}
