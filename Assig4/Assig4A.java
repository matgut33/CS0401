// CS 0401 Fall 2019
// Assig4A driver program.  This program must work as is with your
// MultiDS<T> class.  Look carefully at all of the method calls so that
// you create your MultiDS<T> methods correctly.  For example, note the
// constructor calls and the toString() method call.  The output should
// be identical to my sample output, with the exception of the result of
// the shuffle() methods -- since this should be random yours should not
// match mine.  Also read over the interfaces PrimQ<T> and Reorder.
public class Assig4A
{
	public static void main(String [] args)
	{
		// Testing constructor and PrimQ<T> interface
		// The initial array within the MultiDS should be set to
		// the size indicated by the parameter.  However, the logical
		// size of the MultiDS should be set to 0.
		PrimQ<Integer> theQ = new MultiDS<Integer>(3);

		// Testing addItem and resize (based on the initial size of the
		// array above and the fact that you should double the array size
		// when you resize, the loop below should cause your array to resize
		// two times).  Clearly you will need to write the resize method 
		// for your MultiDS class that will be called from the addItem() 
		// method.
		System.out.println("Adding items to the queue...");
		for (int i = 0; i < 7; i++)
		{
			Integer newItem = new Integer(2 * i);
			if (!theQ.full())   // item should always be added since
								// full should always return false
				theQ.addItem(newItem);
		}

		// Testing removeItem
		System.out.println("Removing items from the queue...");
		while (!(theQ.empty()))
		{
			Integer oldItem = theQ.removeItem();
			System.out.println(oldItem + " retrieved from Q");
		}
		// removeItem() should return null if Q is empty
		Integer noItem = theQ.removeItem();
		if (noItem == null)
			System.out.println("Nothing in the Q");

		// Testing array management
		int count = 1;
		PrimQ<String> theQ2 = new MultiDS<String>(5);
		String theItem = new String("Item " + count);
		System.out.println("Adding " + theItem);
		theQ2.addItem(theItem);
		for (int i = 0; i < 8; i++)
		{
			count++;
			theItem = new String("Item " + count);
			System.out.println("Adding " + theItem);
			theQ2.addItem(theItem);
			theItem = theQ2.removeItem();
			System.out.println("Removing " + theItem);
		}
		int sz = theQ2.size();
		System.out.println("There are " + sz + " items in the Q");

		// This code will test the toString() method and the Reorder
		// interface.
		System.out.println("\nAbout to test Reorder methods");
		MultiDS<Integer> newDS = new MultiDS<Integer>(15);
		for (int i = 0; i < 8; i++)
		{
			newDS.addItem(new Integer(i));
		}
		System.out.println(newDS.toString());
		System.out.println("Reversing");
		newDS.reverse();
		System.out.println(newDS.toString());
		System.out.println("Removing 3 items then adding 1");
		Integer bogus = newDS.removeItem();
		bogus = newDS.removeItem();
		bogus = newDS.removeItem();
		newDS.addItem(new Integer(8));
		System.out.println(newDS.toString());
		System.out.println("Reversing");
		newDS.reverse();
		System.out.println(newDS.toString());
		System.out.println("Shifting right");
		newDS.shiftRight();
		System.out.println(newDS.toString());
		System.out.println("Shifting left twice");
		newDS.shiftLeft();
		newDS.shiftLeft();
		System.out.println(newDS.toString());
		
		System.out.println("\nAbout to test shuffle...");
		newDS.clear();
		for (int i = 0; i < 10; i++)
		{
			newDS.addItem(new Integer(i));
		}
		System.out.println(newDS.toString());
		System.out.println("Shuffling...");
		newDS.shuffle();
		System.out.println(newDS.toString());
		System.out.println("Removing 2 items and adding 1");
		bogus = newDS.removeItem();
		bogus = newDS.removeItem();
		newDS.addItem(new Integer(22));
		System.out.println(newDS.toString());
		System.out.println("Shuffling again");
		newDS.shuffle();
		System.out.println(newDS.toString());
		System.out.println();
		// Accessing again as a Queue
		theQ = (PrimQ) newDS;
		while (!theQ.empty())
		{
			bogus = theQ.removeItem();
			System.out.println("Next item: " + bogus);
		}
	}
}
