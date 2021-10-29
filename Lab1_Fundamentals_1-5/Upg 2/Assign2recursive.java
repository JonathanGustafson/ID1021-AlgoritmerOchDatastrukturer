/********************************************
 * @author Jonathan Gustafson
 * 
 * This program reads a line from standard
 * input and reverses the string using a
 * recursive algorithm.
 * 
 *******************************************/

import java.util.*;

public class Assign2recursive {
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

        //exit condition
        if (str.isEmpty())
            return str;

        //Recursive call
        return reverseString(str.substring(1)) + str.charAt(0);

    }

    /*******************
     * 1 ABCDE
     * 2 BCDE
     * 3 CDE
     * 4 DE
     * 5 E
     * 6
     * 5 E
     * 4 E + D
     * 3 ED + C
     * 2 EDC + B
     * 1 EDCB + A
     ******************/
    // https://beginnersbook.com/2017/09/java-program-to-reverse-a-string-using-recursion/
}
