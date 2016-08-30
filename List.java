
/*		08/15/2016
 * 		Kyeongmin Ha	
 * 		CUNY Queens College - CS 313 Data Structure 	
 * 		Project# 3
 * 		Professor: Joseph Svitak
 * 
 * 		List.java
 */

import java.util.Iterator;

public interface List<AnyType> {
	void clear();

	int size();

	boolean isEmpty();

	AnyType get(int index);

	AnyType set(int index, AnyType newValue);

	boolean add(AnyType newValue);

	void add(int index, AnyType newValue);

	AnyType remove(int index);

	Iterator<AnyType> iterator();

}