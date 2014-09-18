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

		// PARSER - initialize variables 
		/* Method that...does things */
		String current = "";
		public void next() {
		}


	public static void main(String[] args) {

		char tempChar = '\0';
		String tempInt = "";
		String[] tempTokens = new String[100];
		// get input from the user
		Scanner input = new Scanner(System.in);
		String expr = input.next();
		expr += "$"; // append $ to end of line

		// loop through each character in the string
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
			System.out.println("TOKENS: " + tokens[i]);
		}
	}
}