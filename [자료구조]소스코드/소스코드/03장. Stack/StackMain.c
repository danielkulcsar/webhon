#include <stdio.h>
#include <string.h>
#include "Stack.h"

void reverseWord(char* word);

void main(void) 
{
    char word[256];

    printf("단어 : ");
    fgets(word, sizeof(word), stdin);
    word[strlen(word)-1] = '\0';

    reverseWord(word);    

    printf("변환 : %s\n", word);
}

void reverseWord(char* word)
{
    char* save = word;

    while(*word)
        push(*word++);

    word = save;
    while(!isEmpty())
        *word++ = pop();
}

/*
[출력결과]
단어 : I Love You!!
변환 : !!uoY evoL I
*/