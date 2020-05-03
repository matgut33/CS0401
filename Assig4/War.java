//CS 0401 Fall 2019
//Matthew Gutkin
//Assignment 4 War Game

import java.util.Scanner;

public class War {
	//declare all static card deck variables and two current cards for players
	static MultiDS<Card> p1 = new MultiDS<Card>(52);
	static MultiDS<Card> p2 = new MultiDS<Card>(52);
	static MultiDS<Card> disc1 = new MultiDS<Card>(52);
	static MultiDS<Card> disc2 = new MultiDS<Card>(52);
	static MultiDS<Card> wars = new MultiDS<Card>(6);
	static Card currOne;
	static Card currTwo;

	public static void main(String[] args) {
		Scanner r = new Scanner(System.in);
		System.out.println("Welcome to the game of war!");
		System.out.print("How many rounds to play? > ");
		int rounds = r.nextInt(); //take in  number of rounds
		dealCards(); //actually declare cards
		for (int pl = 1; pl <= rounds; pl++) {
			if (p1.empty() || p2.empty()) {
				discarded(); //if any deck is empty, round up cards
			}
			playRound(pl); //play a normal round
		}
		discarded(); //for counting cards if no one won during number of rounds
		System.out.println("\nAfter " + rounds + " rounds, here are the stats: ");
		System.out.println("Player 2 had " + p2.size() + " and player 1 had " + p1.size() + " cards.");
		if (p1.size() > p2.size()) winner(1);
		else if (p1.size() < p2.size()) winner(2);
		else if (p1.size() == p2.size()) winner(0);
	}

	public static void dealCards() {
		System.out.println("\nDealing the cards...\n");
		MultiDS<Card> deck = new MultiDS<Card>(52); //create a deck of cards
		for (Card.Ranks rank : Card.Ranks.values()) {
			deck.addItem(new Card(Card.Suits.Spades, rank)); //add all Spades
		}
		for (Card.Ranks rank : Card.Ranks.values()) {
			deck.addItem(new Card(Card.Suits.Clubs, rank)); //add all Clubs
		}
		for (Card.Ranks rank : Card.Ranks.values()) {
			deck.addItem(new Card(Card.Suits.Diamonds, rank)); //add all Diamonds
		}
		for (Card.Ranks rank : Card.Ranks.values()) {
			deck.addItem(new Card(Card.Suits.Hearts, rank)); //add all Hearts
		}
		deck.shuffle(); //shuffle deck
		p1 = new MultiDS<Card>(52);
		p2 = new MultiDS<Card>(52);
		while(!deck.empty()) { //distribute cards to each player
			p1.addItem(deck.removeItem());
			p2.addItem(deck.removeItem());
		}
		System.out.println("\nHere is player 1's deck:"); //show user each players cards
		System.out.println(p1.toString());
		System.out.println("\nHere is player 2's deck:");
		System.out.println(p2.toString() + "\n");
	}

	public static void playRound(int r) {
		currOne = (Card)p1.removeItem(); //get card for p1
		currTwo = (Card)p2.removeItem(); //get card for p2
		int c = currOne.compareTo(currTwo); //compare two cards
		if (c > 0) { //player 2 wins the round
			System.out.println("Player 2 wins round " + r + ": " + currOne.toString() + " beats " + currTwo.toString());
			disc2.addItem(currOne);
			disc2.addItem(currTwo);
		} else if (c < 0) { //player 1 wins round
			System.out.println("Player 1 wins round " + r + ": " + currTwo.toString() + " beats " + currOne.toString());
			disc1.addItem(currOne);
			disc1.addItem(currTwo);
		} else if (c == 0) { //war!
			war(currOne, currTwo, r);
		}
	}

	public static void war(Card c1, Card c2, int r) {
		System.out.println("\tWAR: " + c1.toString() + " ties " + c2.toString());
		discarded(); //check for empty player decks
		Card useless1 = (Card)p1.removeItem();
		Card useless2 = (Card)p2.removeItem();
		System.out.println("\tPlayer 1's " + useless1.toString() + " and Player 2's " + useless2.toString() + " are at stake!"); //show cards at stake for war
		wars.addItem(useless1);
		wars.addItem(useless2);
		Card war1 = (Card)p1.removeItem(); //get actual war cards
		Card war2 = (Card)p2.removeItem();
		int comp = war1.compareTo(war2);
		if (comp < 0) { //player 2 won war and give all cards to them
			System.out.println("Player 2 wins round " + r + ": " + war2.toString() + " beats " + war1.toString());
			disc2.addItem(war1);
			disc2.addItem(war2); //add all war cards to discard pile
			disc2.addItem(c1);
			disc2.addItem(c2);
			while (!wars.empty()) {
				disc2.addItem(wars.removeItem());
			}
		} else if (comp > 0) { //player 1 won war and give all cards to them
			System.out.println("Player 1 wins round " + r + ": " + war1.toString() + " beats " + war2.toString());
			disc1.addItem(war1);
			disc1.addItem(war2); //add all war cards to discard pile
			disc1.addItem(c1);
			disc1.addItem(c2);
			while (!wars.empty()) {
				disc1.addItem(wars.removeItem());
			}
		} else if (comp == 0) { //war, again.
			System.out.println("\tWAR AGAIN!");
			wars.addItem(c1);
			wars.addItem(c2);
			war(war1,war2,r);
		}
	}


	public static void discarded() {
		if (!wars.empty()) {}
		else if (p1.empty() && disc1.empty()) { //check for winner before getting cards
			System.out.println("\tPlayer 1 ran out of cards!");
			winner(2);
		}
		else if (p2.empty() && disc2.empty()) {
			System.out.println("\tPlayer 2 ran out of cards!");
			winner(1);
		}
		if (p1.empty()) System.out.println("\tGetting and shuffling the pile for player 1.");
		while(!disc1.empty()) { //add discard pile to normal pile for p1
			p1.addItem(disc1.removeItem());
		}
		if (p2.empty()) System.out.println("\tGetting and shuffling the pile for player 2.");
		while(!disc2.empty()) { //add discard pile to normal pile for p2
			p2.addItem(disc2.removeItem());
		}
		p1.shuffle(); //shuffle player decks
		p2.shuffle();
	}

	public static void winner(int win) {
		if (win == 1) {
			System.out.println("\n\tPlayer 1 won!"); //player 1 won
			System.exit(0);
		} else if (win == 2) {
			System.out.println("\n\tPlayer 2 won!"); //player 2 won
			System.exit(0);
		} else if (win == 0) {
			System.out.println("\n\tGame ended in a tie!"); //no one won :(
		} else {
			System.exit(win);
		}
	}
}