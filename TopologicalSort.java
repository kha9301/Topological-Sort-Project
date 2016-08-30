
/*		08/15/2016
 * 		Kyeongmin Ha	
 * 		CUNY Queens College - CS 313 Data Structure 	
 * 		Project# 3
 * 		Professor: Joseph Svitak
 * 
 * 		TopologicalSort.java
 */

public class TopologicalSort {

	TopologicalSort(Vertex[] verticesContainsAdjList) {

		Vertex[] verticesCA = verticesContainsAdjList;
		int size = verticesCA.length;

		// following arrays need the maximum capacity of the number of vertices
		int[] indegree = new int[size]; // total # of in-degree for each vertex
		String[] sort = new String[size]; // save topological sort
		String[] organizer = new String[size];

		// variables needed for iterating while loop
		int sortedElement = 0;
		boolean isSorted = true;

		// number of sortedElement and the Size of Vertices
		while (sortedElement != size) {

			// calculate and store the numbers of in-degree in to an array
			indegree = calculateIndegree(verticesCA);

			// check if there is a cycle by using #of indegree
			if (!hasCycle(indegree, verticesCA)) {

				// stores array of name of vertices that has in-degree of zero
				organizer = findIndegreeZero(indegree, verticesCA);

				// store what is in organizer accordingly to the 'sort' array
				for (int i = 0; i < organizer.length; i++) {
					sort[sortedElement] = organizer[i];
					sortedElement++;
				}

				// remove vertices that has in-degree of zero
				removeZeroIndegree(organizer, verticesCA);

			} else {

				System.out.println("Error: Cycle Exists");
				isSorted = false;
				break;
			}
		}

		// print Topological Sort
		if (isSorted)
			printTopologicalSort(sort);
		else
			System.out.println("Topologicalsort List cannot be generated ");

	}

	public void printTopologicalSort(String[] sort) {
		System.out.println("\nTopologicalsort List \n ");

		for (int i = 0; i < sort.length; i++) {
			System.out.print(sort[i] + " ");
		}
	}

	// String[] organize: read in all in-degree of zero
	// Vertex[] verticesCA: replace the vertices with new Vertex with no data,
	// no adjacencyList
	private void removeZeroIndegree(String[] organizer, Vertex[] verticesCA) {
		// TODO Auto-generated method stub
		int size = organizer.length;
		int ph = 0; // placeholder

		// iterate until all of array has "removed"
		while (ph != size) {
			for (int i = 0; i < verticesCA.length; i++) {
				if (verticesCA[i].getData() != null) {
					if (organizer[ph] == verticesCA[i].getData())
						verticesCA[i] = new Vertex();
				}
			}
			ph++;
		}

	}

	private String[] findIndegreeZero(int[] indegree, Vertex[] verticesCA) {
		// TODO Auto-generated method stub
		String[] temp = new String[indegree.length];

		// actual number of in-degree of zero
		int count2 = 0;
		for (int i = 0; i < indegree.length; i++) {
			if (indegree[i] == 0) {
				if (verticesCA[i].getData() != null) {
					temp[count2] = verticesCA[i].getData();
					count2++;
				}
			}
		}
		// create an array with exact number of vertices with in-degree of zero
		String[] clean = new String[count2];

		// filter
		for (int i = 0; i < count2; i++)
			clean[i] = temp[i];

		return clean;
	}

	public boolean hasCycle(int[] indegree, Vertex[] verticesCA) {
		// TODO Auto-generated method stub
		for (int i = 0; i < indegree.length; i++) {
			if (verticesCA[i].getData() != null) {
				if (indegree[i] == 0) {
					// if there is in-degree of zero the cycle does not exist
					return false;
				}
			}
		}
		// if there is no in-degree of zero the cycle exists
		System.out.println("Cycle Exists");
		return true;

	}

	public int[] calculateIndegree(Vertex[] verticesCA) {
		// TODO Auto-generated method stub
		int[] indegreeArray = new int[verticesCA.length];
		int size = verticesCA.length;

		for (int i = 0; i < size; i++) {

			int ph = 0;// placeholder
			if (verticesCA[i].getData() != null) {

				// iterates adjList in vertex
				while (ph != verticesCA[i].getAdjList().size()) {

					for (int j = 0; j < size; j++) {
						// in-degree increments if it finds a same letter
						if (verticesCA[i].getAdjList().get(ph + 1) == verticesCA[j].getData())
							indegreeArray[j]++;
					}
					ph++;
				}
			}
		}
		return indegreeArray;
	}

}
