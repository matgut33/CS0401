// CS 401 Fall 2019
// Assignment 2 Demonstration of the MyTimer class.  Use this to see how MyTimer
// works so that you can use it in your program.
import java.io.*;
import java.util.*;
public class TimerTest
{
    public static void main(String[] args)
    {
    	Scanner inScan = new Scanner(System.in);
  		testTimer(inScan);	// Demonstrate MyTimer
  		testTimer(inScan);	// Demonstrate again
		
		// Because it uses threads, a MyTimer object will keep the main program from
		// terminating until it expires.  This could be an issue if a user does not need
		// all of the time on the timer.  We can (gracefully) terminate our timer object
		// with a call to stop().  Run the program as is to see this delayed termination.
		// Uncomment the stop() method call to see how this fixes the problem.  Note that
		// stopping a thread during its execution is a non-trivial issue, due to resource
		// access and sharing.  You will likely cover this issue when you take some 
		// systems classes.
		System.out.println("Demonstrating delay in program termination due to timer...");
		MyTimer T = new MyTimer(10000);
		T.start();
		//T.stop();
    }
    
    public static void testTimer(Scanner S)
    {
    	int inVal, count = 0;
    	System.out.println("How long will the timer run (in ms)?");
    	int duration = S.nextInt();
    	// convert from milliseconds to seconds to show user
    	double dur = (double) duration / 1000;
    	MyTimer T = new MyTimer(duration);
    	System.out.println("You have " + dur + " seconds to enter as many ints as you can");
    	System.out.println("Separate them by spaces or newlines");
    	T.start();
    	
    	// Note the two calls to check() here.  This is necessary since when the MyTimer
    	// expires it does not interrupt the execution.  Thus, T.check() could be true at
    	// the beginning of the loop but be false by the time the user actually enters the
    	// value.  Make sure you take this approach in your program as well.
    	while (T.check())
    	{
    		inVal = S.nextInt();
    		if (T.check())
    		{
    			System.out.println("Number " + count + ": " + inVal);
    			count++;
    		}
    		else
    			System.out.println("Sorry, time is up..." + inVal + " was not counted");
    	}
    	System.out.println("You entered " + count + " numbers!");
 	}   	
}
	