//SeparatePosNeg.c

/*

@author Jonathan Gustafson

This program includes a method with an algorithm which takes user 
input for an integer list, and separates negative integers from 
the positive integers.

          Size of list
               |
Test example: "6 1 -1 2 -2 3 -3"
--------------------------------------------

Time complexity:

Similar to assignment 2. If the input size is n then the
amount of loops will be (depening a bit on where negative
numbers are or are not placed)

1 + 2 + ... + (n-1) = n(n-1)/2

giving the algorithm a time complexity of O(n^2)

*/

#include <stdio.h>

void separatePosNeg();

int main()
{  
    int i, length;

    //take user input for length of array
    printf("Enter length: ");
    scanf("%d", &length);

    int list[length];

    //take user input for the content of the array
    printf("Enter array elements (%d): \n", length);

    for(i = 0; i < length; i++)
        scanf("%d", &(list[i]));
    
    //separate the positive and the negative numbers
    separatePosNeg(list, length);

    for(i = 0; i < length; i++)
        printf("%d ", list[i]);

    return 0;
}

/*
separatePosNeg work in the way that it checks elements from the left,
checks if it is positive, and if it is, it looks to the right in the 
array for a negative element to swap places with

*/
void separatePosNeg(int list[], int length){

    for(int i = 0; i < length - 1; i++){
        int j = i + 1;
        //if the element is positive, check for negative elements to the right
        if(list[i] >= 0){
            while(list[j] >= 0 && j < length-1){
                j++;
            }
            //check if a negative number was found
            if(list[j] < 0){

               //swap the elements 
               int temp = list[i];
               list[i] = list[j];
               list[j] = temp;
               if(j + 1 == length){break;}
            }
        }
    }

}