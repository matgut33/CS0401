// CS 401 Fall 2019
// Assignment 2 DictionaryTest program
// Simple demonstration of using the Dictionary class to retrieve
// random words and to test if a word is in the Dictionary or not.
// Also demonstrated is the static method distance() to calculate
// the Levenshtein distance between two strings.

import java.io.*;
import java.util.*;
public class DictionaryTest
{
	public static void main(String [] args)
	{
		// Make a dictionary object
		Dictionary D = new Dictionary("dictionary.txt");
		
		// Get some words and show them.  Clearly you will do more
		// with the words than is shown here.
		System.out.println("Here are some words with between 6 and 8 chars");
		for (int i = 1; i <= 10; i++)
		{
			String word = D.randWord(6,8);
			System.out.println("Next word is " + word);
		}
		System.out.println();
		
		// Test some words for membership in dictionary
		String word;
		word = "weasel";		testWord(D, word);
		word = "bogus";			testWord(D, word);
		word = "inconceivable"; testWord(D, word);
		word = "weasely";		testWord(D, word);
		word = "weaselly";	 	testWord(D, word);
		word = "sportsman";		testWord(D, word);
		
		word = "sportsmanlike"; testWord(D, word);
		D.addWord(word);        testWord(D, word);
		
		word = "bodacious"; 	testWord(D, word);
		D.addWord(word);        testWord(D, word);	
		System.out.println();
		// Check some Levenshtein distances.  Note that the distance method
		// is static -- it is called directly from the class and not from an object.
		word = "weasel";  String word2 = "seashell";
		int dist = Dictionary.distance(word, word2);
		System.out.println("Edit distance between " + word + " and " + word2 + " is " + dist);
		word = "through";  word2 = "thorough";
		dist = Dictionary.distance(word, word2);
		System.out.println("Edit distance between " + word + " and " + word2 + " is " + dist);
		word = "weight";  word2 = "wait";
		dist = Dictionary.distance(word, word2);
		System.out.println("Edit distance between " + word + " and " + word2 + " is " + dist);
		word = "macbook";  word2 = "surface";
		dist = Dictionary.distance(word, word2);
		System.out.println("Edit distance between " + word + " and " + word2 + " is " + dist);
	}
	
	public static void testWord(Dictionary DD, String ww)
	{
		if (DD.contains(ww))
			System.out.println("Word " + ww + " is found");
		else
			System.out.println("Word " + ww + " is NOT found");
	}
}