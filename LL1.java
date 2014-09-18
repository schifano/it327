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
	static int x = 0, y = 0, z = 0;
	static int size = 0;
	static char tempChar = '\0';
	static String tempInt = "";
	static String expr = "";
	static String current = "";
	static String[] tokens;
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
		for(int i = 0; i < tempTokens.length; i++) {
			if (tempTokens[i] != null) {
				size++;
			}
		}
		tokens = new String[size];
		for(int i = 0; i < tokens.length; i++) {
			tokens[i] = tempTokens[i];
			// System.out.println("TOKENS: HI HI HI HI " + tokens[i]); // TEST
		}
	}

	/* Method that returns an error message to the user when expression is invalid */
	static void error() {
		System.out.println("Invalid expression.");
	}
	
	/* Method that retrieves the next token to test */
	static void next() {
		current = tokens[y];
		System.out.println("CURRENT" + current); // TEST 
		y++;
	}

	/* PARSER METHODS - Methods exist for each non-terminal symbol.
		1. E -> TE'
		2. E' -> +TE'
		3. E' -> lambda
		4. T -> FT'
		5. T' -> *FT'
		6. T' -> lambda
		7. F -> (E)
		8. F -> n
	*/

	/* Method for E -> TE' */
	static int parseE() {
		int x = parseT();
		return parseEPrime(x);
	}

	/* Method for E' -> TE' */
	static int parseEPrime(int x) {
		if (current.equals("+")) {
			next();
			int z = parseT();
			return parseEPrime(x+z);
		} else if (current.equals(")") || current.equals("$")) {
			return x;
		} else {
			error();
			return x;
		}
	}

	/* Method for T -> FT' */
	static int parseT() {
		int x = parseF();
		return parseTPrime(x);
	}

	/* Method for T1 -> *FT' */
	static int parseTPrime(int x) {
		if (current.equals("*")) {
			next();
			int z = parseF();
			return parseTPrime(x*z);
		} else if (current.equals("+") || current.equals(")") || current.equals("$")) {
			return x;
		} else {
			error();
			return x;
		}
	}
   
  /* Method for F -> (E) */
  static int parseF() {
  	if (current.equals("(")) {
  		next();
  		int x = parseE();
  		if (current.equals(")")) {
  			next();
  			return x;
  		} else {
  			error();
  			return -1;
  		}
  	} else try {
  		int x = Integer.valueOf(current).intValue();
	    next();
	    return x;
		} catch(NumberFormatException e) {
	    error();
	    return -1; // to make compiler happy
		}
  } 

	public static void main(String[] args) {
		// Get input expression from the user
		Scanner input = new Scanner(System.in);
		expr = input.next();
		expr += "$"; // append $ to end of line to indicate EOL
		
		tokenize(); // tokenize the input string
		next();
		int x = parseE(); // begin at root E
		if(current.equals("$")) {
		    System.out.println("OK "+ x);
		} else {
		    error();
		} 
	}
}