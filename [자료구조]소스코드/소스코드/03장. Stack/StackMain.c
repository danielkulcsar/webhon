#include <stdio.h>
#include <string.h>
#include "Stack.h"

void reverseWord(char* word);

void main(void) 
{
    char word[256];

    printf("�ܾ� : ");
    fgets(word, sizeof(word), stdin);
    word[strlen(word)-1] = '\0';

    reverseWord(word);    

    printf("��ȯ : %s\n", word);
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
[��°��]
�ܾ� : I Love You!!
��ȯ : !!uoY evoL I
*/