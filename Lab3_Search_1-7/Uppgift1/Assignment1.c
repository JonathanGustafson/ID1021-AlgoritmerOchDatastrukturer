//Assignment1.c

/*
 * @author Jonathan Gustafson
 * 
 * This program reads from a text file, filters out all non-alphabetic 
 * characters and writes it to a new file.
 * 
 */

#include <stdio.h>
#include <ctype.h>

int main(){

    //Create file to read from
    FILE *readFile = fopen("labText.txt", "r");
    //Create file to write to
    FILE *writeFile = fopen("filteredLabText.txt", "w");

    char ch; //Used to read from file

    //While there are characters left write all alphabetic/space/newline in a new file
    while ((ch = fgetc(readFile)) != EOF){
        if(isalpha(ch) != 0 || ch == ' ' || ch == '\n'){
            //putchar(ch);
            fputc(ch, writeFile);
        } 
        else{
            //putchar(' ');
            fputc(' ', writeFile);
        }
    }

    fclose(readFile);
    fclose(writeFile);

    return 0;
}