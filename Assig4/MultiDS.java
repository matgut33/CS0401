// CS 0401 Fall 2019
// Shell of MultiDS<T> class to be used with Assignment 4.
// You must complete this class so that:
// 1) It works with test program Assig4A.java
// 2) You can use it in your War program

import java.io.*;
import java.util.*;
public class MultiDS<T> implements PrimQ<T>, Reorder
{
	private T [] theData;  // Note that the data is an array of T
	// You will also need one or more state variables to keep track of your
	// logical data.  The variables you need depend on how you will manage
	// your queue.
	private int size;
	
	private Random R;  // This is needed so that you can shuffle your data

	public MultiDS(int sz)
	{
		theData = (T []) new Object[sz];  // Note how this is created
		R = new Random();
		size = 0;
		// You will also need to initialize your other state variable(s).
		// You will need to create a new array in a similar way in your
		// resize() method.
	}

	// Below you should implement all of the methods in the PrimQ<T> and Reorder
	// interfaces.  See the details for these methods in files PrimQ.java and
	// Reorder.java.  See how these are actually used in the test program Assig4A.java.
	// Once you have completed this class the program Assig4A.java should compile and
	// run and give output identical to that shown in file A4Out.txt (except for the
	// order of the data after shuffling, since that should be random).


	// Add a new Object to the PrimQ<T> in the next available location.  If
	// all goes well, return true.  The iterface allows for this method to
	// return false if the add does not succeed.  [However, in your implementation
	// it should always succeed (i.e you should resize your array if necessary)]
	public boolean addItem(T item) {
		if (full()) resize();
		theData[size] = item;
		size++;
		return true;
	}
	
	// Remove and return the "oldest" item in the PrimQ.  If the PrimQ
	// is empty, return null.
	public T removeItem() {
		if (empty()) return null;
		T tmp = theData[0];
		for (int i = 0; i < (size-1); i++) {
			theData[i] = theData[i+1];
		}
		size--;
		return tmp;
	}
	
	// Return true if the PrimQ is full, and false otherwise.  [In your
	// implementation, this method should always return false]
	public boolean full(){
		if (theData[theData.length-1] != null) resize();
		return false;
	}
	
	// Return true if the PrimQ is empty, and false otherwise
	public boolean empty() {
		return (size == 0);
	}
	
	// Return the number of items currently in the PrimQ
	public int size() {
		return size;
	}

	// Reset the PrimQ to empty status by reinitializing the variables
	// appropriately
	public void clear() {
		size = 0;
		theData = (T []) new Object[3];
		R = new Random();
	}

	public void clear(int s) {
		size = 0;
		theData = (T []) new Object[s];
		R = new Random();
	}

	// Logically reverse the data in the Reorder object so that the item
	// that was logically first will now be logically last and vice
	// versa.  The physical implementation of this can be done in 
	// many different ways, depending upon how you actually implemented
	// your physical MultiDS<T> class
	public void reverse() {
		T[] tmp = theData.clone();
		for (int i = 0; i < size; i ++) {
			theData[i] = tmp[(size-1-i)];
		}
	}

	// Remove the logical last item of the DS and put it at the 
	// front.  As with reverse(), this can be done physically in
	// different ways depending on the underlying implementation.  
	public void shiftRight() {
		if(empty()) return;
		T last = theData[size-1];
		for (int i = size; i > 0; i--) {
				theData[i] = theData[i-1];
			}
		theData[0] = last;
	}

	// Remove the logical first item of the DS and put it at the
	// end.  As above, this can be done in different ways.
	public void shiftLeft() {
		if(empty()) return;
		T tmp = theData[0];
		for (int i = 0; i < (size-1); i++) {
			theData[i] = theData[i+1];
		}
		theData[(size-1)] = tmp;
	}
	
	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in 
	// the Java API).  Thus, after this operation the "oldest" item in the
	// DS could be arbitrary.
	public void shuffle() {
		int sz = size, index = 0;
		for (int i = (size - 1); i > 0; i--) {
			index = R.nextInt(size);
			T tmp = theData[index];
  			theData[index] = theData[i];
      		theData[i] = tmp;
		}
	}

	public void resize() {
		T[] tmp = (T []) new Object[(theData.length*2)];
		for (int i = 0; i < theData.length; i++) {
			tmp[i] = theData[i];
		}
		theData = tmp;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contents:\n");
		sb.append(theData[0]);
		for (int i = 1; i < size; i++) {
			sb.append(" " + theData[i]);
		}
		return sb.toString();
	}

}
