//Matthew Gutkin
//CS0401 Assignment 2

import java.util.*;
import java.io.*;

public class GamePlayer {
	private String name;
	private int oScore, score, optimal, success, failure, edits, rounds;
	private File playerFile;
	private PrintWriter pw;
	private Scanner r;

	public GamePlayer(String n, int r, int s, int o, int t) throws IOException {
		doName(n); //intiialize all varaiables, and send name to be checked for current stats
		rounds = r;
		success = s;
		optimal = o;
		edits = t;
		failure = rounds - success;
	}

	public GamePlayer(String n) throws IOException {
		doName(n); //send name to be checked for current stats
	}

	public void doName(String n) throws IOException {
		name = n; //set player name

		StringBuilder sb = new StringBuilder();
		sb.append("players/"); 
		playerFile = new File(sb.toString()); //check if player dir exists, if not create
		if(!playerFile.exists()) playerFile.mkdir();
		sb.append(n);
		sb.append(".txt");

		playerFile = new File(sb.toString());
		if (!playerFile.isFile()) {
			playerFile.createNewFile(); //if player file does not exist, create player and welcome them
			System.out.println("Welcome new player!");
			save();
		} else {
			restore(playerFile); //if player exists, restore data
		}
	}

	public void save() throws IOException {
		playerFile.delete(); //deletes old file
		playerFile.createNewFile(); //creates new file
		pw = new PrintWriter(playerFile);
		pw.println(toStringFile()); //prints player info to file
		pw.close();
	}

	public int getScore() {return score;}

	public String getName() {return name;}

	public int getSuccess() {return success;}

	public int getRounds() {return rounds;}

	public void restore(File p) throws IOException {
		r = new Scanner(p);
		rounds = r.nextInt();
		success = r.nextInt();
		optimal = r.nextInt();
		edits = r.nextInt();
		failure = rounds - success;
		System.out.println("Successfully restored player. Welcome back!");
	}

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
		if (optimal == 0) ratio = 1.0;
		s.append(ratio);
		return s.toString();
	}

	public String toStringFile() {
		StringBuilder s = new StringBuilder();
		s.append(rounds);
		s.append("\n");
		s.append(success);
		s.append("\n");
		s.append(optimal);
		s.append("\n");
		s.append(edits);
		s.append("\n");
		return s.toString();
	}
}