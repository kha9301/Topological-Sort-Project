
/*		08/15/2016
 * 		Kyeongmin Ha	
 * 		CUNY Queens College - CS 313 Data Structure 	
 * 		Project# 3
 * 		Professor: Joseph Svitak
 * 
 * 		DoublyLinkedList.java
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<AnyType> implements List<AnyType> {
	private static class Node<AnyType> {
		private AnyType data;
		private Node<AnyType> prev;
		private Node<AnyType> next;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			setData(d);
			setPrev(p);
			setNext(n);
		}

		public AnyType getData() {
			return data;
		}

		public void setData(AnyType d) {
			data = d;
		}

		public Node<AnyType> getPrev() {
			return prev;
		}

		public void setPrev(Node<AnyType> p) {
			prev = p;
		}

		public Node<AnyType> getNext() {
			return next;
		}

		public void setNext(Node<AnyType> n) {
			next = n;
		}
	}

	private int theSize;
	private int modCount;
	private Node<AnyType> header;
	private Node<AnyType> trailer;

	public DoublyLinkedList() {
		header = new Node<AnyType>(null, null, null);
		trailer = new Node<AnyType>(null, null, null);
		modCount = 0;
		clear();
	}

	public void clear() {
		header.setNext(trailer);
		trailer.setPrev(header);
		theSize = 0;
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public AnyType get(int index) {
		if (header.getNext().getData() == null)
			throw new IndexOutOfBoundsException("No Node In The List Exist");

		Node<AnyType> current = getNode(index, 0, size());

		if (current.getData() == null)
			throw new NullPointerException("No Such Node In The List Exist: Check The Matrix");

		return current.getData();
	}

	public AnyType set(int index, AnyType newValue) {
		if (index >= theSize) {
			throw new IndexOutOfBoundsException("Index Has Exceeded The Size Of The List");
		}

		if (header.getNext().getData() == null)
			throw new IndexOutOfBoundsException("No Node In The List Exist");

		Node<AnyType> current = getNode(index, 0, size());
		if (current.getData() == null)
			throw new NullPointerException("No Such Node In The List Exist: Check The Matrix");

		modCount++;
		current.setData(newValue);
		return current.getData();
	}

	public boolean add(AnyType newValue) {
		add(size(), newValue);
		return true;
	}

	public void add(int index, AnyType newValue) {

		Node<AnyType> newNode = new Node<AnyType>(newValue, null, null);

		Node<AnyType> current = header;

		for (int i = 0; i < index; i++)
			current = current.getNext();

		Node<AnyType> step1 = current.getNext();
		Node<AnyType> step2 = current;

		step1.setPrev(newNode);
		step2.setNext(newNode);
		newNode.setNext(step1);
		newNode.setPrev(step2);
		theSize++;
		modCount++;
	}

	public AnyType remove(int index) {
		return remove(getNode(index));
	}

	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	Node<AnyType> getNode(int index) {
		return (getNode(index, 0, size()));
	}

	private Node<AnyType> getNode(int index, int lower, int upper) {
		if (header.getNext().getData() == null)
			throw new IndexOutOfBoundsException("No Node In The List Exist");

		Node<AnyType> current = header;
		if (index >= lower && index <= upper) {

			for (int i = 0; i < index; i++)
				current = current.getNext();

			if (current.getData() == null)
				throw new NullPointerException("No Such Node In The List Exist: Check The Matrix");
		}
		return current;
	}

	private AnyType remove(Node<AnyType> currNode) {

		if (header == null) {
			throw new RuntimeException("no list to delete");
		}

		Node<AnyType> prev = currNode.getPrev();
		Node<AnyType> next = currNode.getNext();

		if (currNode.getNext().equals(null)) {
			prev.setNext(null);
		}
		if (!currNode.getNext().equals(null)) {
			prev.setNext(next);
			next.setPrev(prev);
		}

		currNode.setPrev(null);
		currNode.setNext(null);

		theSize--;
		modCount++;
		return currNode.getData();

	}

	private class LinkedListIterator implements Iterator<AnyType> {
		private Node<AnyType> current;
		private int expectedModCount;
		private boolean okToRemove;

		LinkedListIterator() {
			current = header.getNext();
			expectedModCount = modCount;
			okToRemove = false;
		}

		public boolean hasNext() {
			return (current != trailer);
		}

		public AnyType next() {
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();

			AnyType nextValue = current.getData();
			current = current.getNext();
			okToRemove = true;
			return nextValue;
		}

		public void remove() {
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			DoublyLinkedList.this.remove(current.getPrev());
			expectedModCount++;
			okToRemove = false;
		}
	}

}