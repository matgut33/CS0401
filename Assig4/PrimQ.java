// CS 0401 Fall 2019
// Assignment 4 PrimQ<T> interface
// Carefully read the specifications for each of the operations and
// implement them correctly in your MultiDS class.

// The overall logic of the PrimQ<T> interface is the following:
//		Data is logically "added" in the same order that it is "removed".
// However, there is no requirement for the physical storage of the actual
// data.  Theoretically, you could represent your data in several different
// ways, each of which could be correct.  However, for this project you
// must use an array for your primary data.  See MultiDS.java for an idea of
// how to set this up.

public interface PrimQ<T>
{
	// Add a new Object to the PrimQ<T> in the next available location.  If
	// all goes well, return true.  The iterface allows for this method to
	// return false if the add does not succeed.  [However, in your implementation
	// it should always succeed (i.e you should resize your array if necessary)]
	public boolean addItem(T item);
	
	// Remove and return the "oldest" item in the PrimQ.  If the PrimQ
	// is empty, return null.
	public T removeItem();
	
	// Return true if the PrimQ is full, and false otherwise.  [In your
	// implementation, this method should always return false]
	public boolean full();
	
	// Return true if the PrimQ is empty, and false otherwise
	public boolean empty();
	
	// Return the number of items currently in the PrimQ
	public int size();

	// Reset the PrimQ to empty status by reinitializing the variables
	// appropriately
	public void clear();
}