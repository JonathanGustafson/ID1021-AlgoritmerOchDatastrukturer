/**********************************************
 * @author Jonathan Gustafson
 * 
 * This program reads a line from standard
 * input and reverses the string using an
 * algorithm that puts all characters in
 * a stack, and pops them into a new string
 * to reverse the order.
 * 
 *********************************************/

import java.util.Scanner;
import myAPI.*;

public class Assign2iterative {

	public static void main(String[] args) {
		
        //creat scanner to read user input
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter text: ");
        String str = scanner.nextLine();

        //print the reversed string
        System.out.println("Your text but reversed: " + reverseString(str));

        scanner.close();
    }

    public static String reverseString(String str){

        //Creat a editable string and a stack
        StringBuilder s = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        //push all characters from user string to the stack
        for(int i = 0; i < str.length(); i++)
            stack.push(str.charAt(i));

        //pop all the characters to the stringbuilder for
        for(int j = 0; j < str.length();j++)
            s.append(stack.pop());
        
        return s.toString();
    }
}
