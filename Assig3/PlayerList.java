// CS 0401 Fall 2019
// Shell of the PlayerList class.
// This class represents a collection of players -- a very simple database.  The
// collection can be represented in various ways.  However, for this assignment
// you are required to use an array of GamePlayer.

// Note the imports below.  java.util.* is necessary for ArrayList and java.io.* is
// necessary for the file reading and writing.
import java.util.*;
import java.io.*;

public class PlayerList
{
	private GamePlayer [] players;  // array of GamePlayer
	private int size;				// logical size
	private String file;			// name of file associated with this PlayerList
	
	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new GamePlayer object for each line
	// and putting them into the array.  Your initial size for the array MUST be 3 and
	// if you fill it should resize by doubling the array size.  
	
	// Note that this method throws IOException. Because of this, any method that CALLS
	// this method must also either catch IOException or throw IOException.  Note that 
	// the main() in PlayerListTest.java throws IOException.  Keep this in mind for your
	// main program (Assig3.java).  Note that your saveList() method will also need
	// throws IOException in its header, since it is also accessing a file.
	public PlayerList(String fName) throws IOException
	{
		File fi = new File(fName); //creat file out of string file name
		file = fName; //set class var file to file name string
		players = new GamePlayer[3]; //intialize first 3 player vars
		size = 0; //set logical size to 0
		Scanner rq = new Scanner(fi); //create scanner out of playe file
		int c = 0; //intiialize counter for number of lines
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while (reader.readLine() != null) c++; //count number of lines in text file
		reader.close();
		for(int i = 0; i < c; i++) {
			String tmp = ""; //initialize temp string
			tmp = rq.nextLine(); //read player line
			if (tmp.equals(" ") || tmp.equals("")) break; //if line is empty skip
			String[] f = tmp.split(","); //split line by comma
			String name = f[0]; //set name var
			String pass = f[1]; //set password
			int rounds = Integer.parseInt(f[2]); //set rounds
			int success = Integer.parseInt(f[3]); //set successes
			int optimal = Integer.parseInt(f[4]); //set opimals
			int edits = Integer.parseInt(f[5]); //set num of edits
			newPlayer(name,pass,rounds,success,optimal,edits); //create new player
		}
	}


	private void newPlayer(String n, String p, int r, int s, int o, int e) throws IOException {
		checkSize(); //make sure there is enough room in physical array for new player
		players[size] = new GamePlayer(n,p,r,s,o,e); //initialize new player
		size = size +1; //increase logical size
		
	}

	public int size() {
		return size;
	}

	public void checkSize() {
		if ((size+1) >= capacity()) {
			GamePlayer[] tmp = new GamePlayer[(players.length*2)]; //if theres no room for another player in physical array
			for (int i = 0; i < size; i++) {                       //double array size and copy old arry to new
				tmp[i] = players[i];
			}
			players = tmp;
		}
	}

	public int capacity() {
		return players.length;
	}

	public boolean containsName(String n) {
		for (int i = 0; i < size; i++) { //iterate through array to check is name exists
			if (players[i].getName().equalsIgnoreCase(n)) return true;
		}
		return false;
	}

	public boolean addPlayer(GamePlayer p) throws IOException{
		if (containsName(p.getName())) return false; //check if name is in array first. if found return false
		checkSize(); //check physical array size
		newPlayer(p.getName(),p.getPass(),p.getRounds(),p.getSuccess(),p.getOptimal(),p.getEdits()); //create new player
		return true;
	}

	public void saveList() throws IOException {
		StringBuilder sb = new StringBuilder();
		File fi = new File(file);
		PrintWriter pw = new PrintWriter(fi);
		for (int i = 0; i < size; i++) {
			sb.append(players[i].toStringFile()); //print out stats to file
			sb.append("\n");
		}
		pw.println(sb.toString());
		pw.close();
	}

	public GamePlayer authenticate(GamePlayer p) {
		for (int i = 0; i < size; i++) { //iterate through array and see if player exists in array matching name and pass
			if (equals(p, players[i])) return p;
		}
		return null; //if not found, return null
	}

	public String toString() {
		StringBuilder sbb = new StringBuilder();
		sbb.append("Total Players: " + size);
		sbb.append("\n");
		for (int i = 0; i < size; i++) {
			sbb.append(players[i].toString()); //create string of all players, iterate through and build
			if (i != (size-1)) {
				sbb.append("\n");
				sbb.append("\n");
			}
		}
		return sbb.toString();
	}

	public GamePlayer getPlayer(int i) {
		return players[i]; //return player of index value
	}

	public GamePlayer getPlayer(String s) {
		for (int i = 0; i < size; i++) { //return player of name, if not found return null
			if (players[i].getName().equals(s)) return players[i];
		}
		return null;
	}

	public boolean equals(GamePlayer check, GamePlayer p) { //if name and pass match then return true, else return false
		if (p.getName().equalsIgnoreCase(check.getName()) && p.getPass().equals(check.getPass())) return true;
		return false; 

	}

	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the modified
	// GamePlayer class before the PlayerList class will work, since your array is an
	// array of GamePlayer objects.
	
	// You may also want to add some methods that are not tested in PlayerListTest.java.
	// Think about what you need to do to a PlayerList in your Assig3 program and write 
	// the methods to achieve those tasks.  However, make sure you are always thinking
	// about encapsulation and data abstraction.
}