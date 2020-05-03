//Matthew Gutkin
//CS0401 Assignment 2

import java.util.*;
import java.io.*;

public class GamePlayer {
	private String name, pass;
	private int oScore, score, optimal, success, failure, edits, rounds;
	private File playerFile;
	private PrintWriter pw;
	private Scanner r;

	public GamePlayer(String n, String p, int r, int s, int o, int t) throws IOException {
		name = n; //intiialize all varaiables
		pass = p;
		rounds = r;
		success = s;
		optimal = o;
		edits = t;
		failure = rounds - success;
	}

	public GamePlayer(String n) throws IOException {
		name = n; //intiialize all varaiables
	}


	public GamePlayer(GamePlayer p) throws IOException {
		name = p.getName(); //intiialize all varaiables
		rounds = p.getRounds();
		success = p.getSuccess();
		optimal = p.getOptimal();
		edits = p.getEdits();
		failure = rounds - success;
	}

	public void save() throws IOException {
		playerFile.delete(); //deletes old file
		playerFile.createNewFile(); //creates new file
		pw = new PrintWriter(playerFile);
		pw.println(toStringFile()); //prints player info to file
		pw.close();
	}

	public int getScore() {return score;}

	public int getEdits() {return edits;}

	public int getOptimal() {return optimal;}

	public String getPass() {return pass;}

	public String getName() {return name;}

	public int getSuccess() {return success;}

	public int getRounds() {return rounds;}


	public void failure() {
		rounds++; 
		failure++;
	}

	public void success(int nec, int op) {
		rounds++;
		success++;
		edits += nec;
		optimal += op;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("     Name: ");
		s.append(name);
		s.append("\n");
		s.append("     Rounds: ");
		s.append(rounds);
		s.append("\n");
		s.append("     Successes: ");
		s.append(success);
		s.append("\n");
		s.append("     Failures: ");
		s.append(failure);
		s.append("\n");
		s.append("     Ratio (successes only): ");
		double ratio = ((double)edits) / optimal;
		if (optimal == 0 || optimal == Double.NaN) ratio = 1.0;
		s.append(ratio);
		return s.toString();
	}

	public String toStringFile() {
		StringBuilder s = new StringBuilder();
		s.append(name);
		s.append(",");
		s.append(pass);
		s.append(",");
		s.append(rounds);
		s.append(",");
		s.append(success);
		s.append(",");
		s.append(optimal);
		s.append(",");
		s.append(edits);
		return s.toString();
	}

	public boolean testPass() {
		System.out.println("Welcome back, " + getName() + ".");
		r = new Scanner(System.in);
		for (int i = 0; i < 3; i ++) { //allow user to try password three times before returning false
			System.out.print("Enter your password (case sensative) > ");
			String tmp = r.nextLine();
			if (this.getPass().equals(tmp)) return true;
			else System.out.println("Sorry, your password does not match.  Please try again. " + (2-i) + " tries remaining.");
		}
		return false;
	}

	public void setPass(String p) {
		pass = p;
	}

	public boolean equals(GamePlayer p) {
		if (this.getName().equals(p.getName())) return true;
		return false;
	}

}