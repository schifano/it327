/*
	Name: Rachel A Schifano
	Professor: Dr. Li
	Course: IT 327
	Assignment 1: Implement a recursive-descent parser using LL(1) parsing table
*/
import java.util.StringTokenizer;
import java.util.Scanner;
import java.lang.String;

public class LL1 {

	// Initialize variables
	static char tempChar = '\0';
	static String tempInt = "";
	static String expr = "";
	static String current = "";
	static String[] tempTokens = new String[100];

	/* Method that creates a token array. */
	static void tokenize() {
		// loop through chars to store numbers and operators
		int j = 0;
		for(int i = 0; i < expr.length(); i++) {
			tempChar = expr.charAt(i);
			if (Character.isDigit(tempChar)) {
				tempInt += tempChar;
			} else {
				if (!tempInt.equals("")) {
					tempTokens[j++] = tempInt;
				}
				tempInt = "";
			}
			if (!Character.isDigit(tempChar)) {
				tempTokens[j++] = Character.toString(tempChar);
			}
		}
		int size = 0;
		for(int i = 0; i < tempTokens.length; i++) {
			if (tempTokens[i] != null) {
				size++;
			}
		}
		String[] tokens = new String[size];
		for(int i = 0; i < tokens.length; i++) {
			tokens[i] = tempTokens[i];
			System.out.println("TOKENS: HI HI HI HI " + tokens[i]); // TEST
		}
	}

	// PARSER METHODS
	/* Method that retrieves the next token to test*/
	static void next() {

	}


	public static void main(String[] args) {
		// Get input expression from the user
		Scanner input = new Scanner(System.in);
		expr = input.next();
		expr += "$"; // append $ to end of line to indicate EOL
		
		tokenize(); // tokenize the input string

	}
}