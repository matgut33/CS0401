//Matthew Gutkin
//CS0401 Assignment 2

import java.util.*;
import java.io.*;

public class assig3 {
	public static GamePlayer player;
    public static PlayerList players;

    public static void main(String args[]) throws IOException {
        Scanner r = new Scanner(System.in);
        String tmp = "";
        boolean played = false;
        Dictionary words;
        players = new PlayerList("players.txt");
        while(true) {
        	try {
		        System.out.print("What is the name of the dictionay file? > ");
		        tmp = r.nextLine();
		        words = new Dictionary(tmp); //create new dictionary file
		        tmp = words.randWord(0,9); //check if dictionary file exists
		        break;
		    } catch (Exception e) {
		    	System.out.println(" "); //catch nullpointer and ask user for another dictionary file
		    }
	    }
        Dictionary used = new Dictionary();
        StringBuilder w1 = new StringBuilder(words.randWord(5,9)); //generate first word
        used.addWord(w1.toString());
        StringBuilder w2 = new StringBuilder(words.randWord(5,9)); //generate second word
        while(true) {
        	if (used.contains(w2.toString())) w2 = new StringBuilder(words.randWord(0,9)); //check that first word is not the same as the second
        	else {
        		used.addWord(w2.toString()); //add word to the used dictionary to not be used again
        		break;
        	}
        }
        System.out.println("Welcome to the Word Changer Program"); //give directions to player
        System.out.println(" ");
        System.out.println("Here is how to play:");
        System.out.println("        For each round you will see two randomly selected words.");
        System.out.println("        You will have 1 minute to convert the first word to the second");
        System.out.println("        using only the following changes:");
        System.out.println("                Insert a character at position k (with k starting at 0)");
        System.out.println("                Delete a character at position k (with k starting at 0)");
        System.out.println("                Change a character at position k (with k starting at 0)");
        System.out.println("        For example, to change the word 'WEASEL' to 'SEASHELL' you could");
        System.out.println("        do the following:");
        System.out.println("                1) Change 'W' at position 0 to 'S': SEASEL");
        System.out.println("                2) Insert 'H' at position 4: SEASHEL");
        System.out.println("                3) Insert 'L' at position 7: SEASHELL");
        System.out.println("        Your goal is to make this conversion with the fewest changes.");
        System.out.println("Note that there may be more than one way to achieve this goal.");
        System.out.println(" ");

        newPlayer();
        while(true) {
        	if (played) {
	    		System.out.print("Enter 0 to play, 1 for new player, or any other number to exit. > ");
	    		tmp = r.nextLine();
	    		if (Integer.parseInt(tmp) == 1) { //save old player and generate new one
	    			players.saveList();
	    			newPlayer();
	    			used = new Dictionary(); //clear used dictionary for new player
	    		}
	    		else if (Integer.parseInt(tmp) == 0) {} //continue to new game
	    		else exit(); //exit if user wants to
	    		w1 = new StringBuilder(words.randWord(5,9));
		        while(true) { //run loop until a new word is found that is not in used dictionary
		        	if (used.contains(w1.toString())) w1 = new StringBuilder(words.randWord(0,9));
		        	else {
		        		used.addWord(w1.toString());
		        		break;
		    		}
		        }
		        w2 = new StringBuilder(words.randWord(5,9));
		        while(true) { //run loop until a new word is found that is not in used dictionary
		        	if (used.contains(w2.toString())) w2 = new StringBuilder(words.randWord(0,9));
		        	else {
		        		used.addWord(w2.toString());
		        		break;
		    		}
		        }
        	}
		    System.out.println(" ");
		    System.out.println("Okay, " + player.getName() + ". You ready? You will have one minute. Press enter to start the game!");
		    System.out.print("Your goal is to change '" + w1.toString() + "' into '" + w2.toString() + "' in " + Dictionary.distance(w1.toString(),w2.toString()) + " edits.");
		    try{System.in.read();} //alow the user to read, wait for input (enter key) to continue to game
			catch(Exception e){}
			game(w1,w2,r,player); //play actual game
			players.saveList();
			played = true;
		}

    }

    public static String options(StringBuilder w1, StringBuilder w2, Scanner r) {
    	System.out.println(" ");
    	System.out.printf("%-15s %s%n", "Index:", "0123456789");
    	System.out.printf("%-15s %s%n", "------", "----------");
    	System.out.printf("%-15s %s%n", "Current Word:", w1.toString());
    	System.out.printf("%-15s %s%n", "Word 2:", w2.toString());
    	System.out.println("Here are your options:");
    	System.out.println("     C k v -- Change char at location k to value v");
    	System.out.println("     I k v -- Insert char at location k with value v");
    	System.out.println("     D k   -- Delete char at location k");
    	System.out.print("Option choice > ");
    	return r.nextLine();
    }

    public static void game(StringBuilder w1, StringBuilder w2, Scanner r, GamePlayer p) {
    	int e = 0, o; //initilize edits and optimal numbers
    	o = Dictionary.distance(w1.toString(),w2.toString()); //calculate distance
    	MyTimer timer = new MyTimer(60000); //create timer for 1 minute (60000 milliseconds)
    	timer.start(); //start timer for game

    	while(timer.check()) { //check if timer is still running
    		System.out.println(" ");
    		if (w1.toString().equals(w2.toString())) {
    			timer.stop();
    			break; //check if last attempt was successful, and break loop
    		}
	    	String tmpA[] = options(w1,w2,r).split(" ");
	    	if(timer.check()) {
	    		try {
			        if (tmpA[0].equalsIgnoreCase("C")) w1.setCharAt(Integer.parseInt(tmpA[1]),tmpA[2].charAt(0)); //if first character c, change char at value
			        else if (tmpA[0].equalsIgnoreCase("I")) w1.insert(Integer.parseInt(tmpA[1]),tmpA[2].charAt(0)); //if first character i, insert char at value
			        else if (tmpA[0].equalsIgnoreCase("D")) w1.deleteCharAt(Integer.parseInt(tmpA[1])); //if fist character d, delete character at value
			        e++;
			    } catch (Exception a) {
			    	System.out.println("That input was invalid."); //catch input exceptions, i.e. if user enters wrong input
			    	continue;
			    }
		    } else System.out.println("Sorry- timer ran out. Last option not entered."); //if user enters anything after timer, notify
	    }
	    if (w1.toString().equals(w2.toString())) {
	    	p.success(e, o);                       //if the two words match add to success with edits and optimal
	    	System.out.println("Wow! Great job!"); //notify user
	    }
	    else p.failure();
	    System.out.println("After " + e + " changes ("+ o + " optimal) your final guess was " + w1.toString()); //tell user what the two words are
	    System.out.println("Here are your current stats:"); //print user stats for them
	    System.out.println(p.toString());
    }

    public static void exit() throws IOException {
    	players.saveList(); //make sure player is saved to text file and exit
    	System.exit(0);
    }

    public static void newPlayer() throws IOException {
    	Scanner scan = new Scanner(System.in); //generates new player based on name
        while(true) {
        	System.out.print("Enter a name for your account (no spaces) > ");
            String temp = scan.next();
            player = players.getPlayer(temp);
            if (!(player == null)) {
                boolean passed = player.testPass();
                if (!passed) {
                    System.out.println("Invalid password. Choose a new name.");
                    continue;
                }
            }
            else {
                GamePlayer p = new GamePlayer(temp);
                players.addPlayer(p);
                player = players.getPlayer(p.getName());
                System.out.print("Set a password (no spaces, case sensative) > ");
                player.setPass(scan.next());
            }
            break;
        }
    }
}