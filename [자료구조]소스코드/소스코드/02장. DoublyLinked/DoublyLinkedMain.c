#pragma warning(disable:4996)
#include <stdio.h>
#include "DoublyLinked.h"

void main(void)
{
    DOUBLE_LIST list = { 0 };
    char s1[] = "BLACK",
         s2[] = "WHITE";
    int i;

    printf("** ���� **\n");
    for(i = 0; i < 5; i++)
    {
        addHead(&list, s1[i]);
        addTail(&list, s2[i]);
    }

    printNormal(&list);
    printReverse(&list);
    printf("�Ӹ� : %c\n", getHead(&list));
    printf("���� : %c\n", getTail(&list));

    printf("\n** �糡���� 3���ھ� ���� **\n");
    for(i = 0; i < 3; i++)
    {
        removeHead(&list);
        removeTail(&list);
    }

    printNormal(&list);
    printReverse(&list);
    printf("�Ӹ� : %c\n", getHead(&list));
    printf("���� : %c\n", getTail(&list));
}

/*
[��� ���]
** ���� **
K C A L B W H I T E
E T I H W B L A C K
�Ӹ� : K
���� : E

** �糡���� 3���ھ� ���� **
L B W H
H W B L
�Ӹ� : L
���� : H
*/