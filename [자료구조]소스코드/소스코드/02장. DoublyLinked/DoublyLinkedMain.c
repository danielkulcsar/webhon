#pragma warning(disable:4996)
#include <stdio.h>
#include "DoublyLinked.h"

void main(void)
{
    DOUBLE_LIST list = { 0 };
    char s1[] = "BLACK",
         s2[] = "WHITE";
    int i;

    printf("** 원본 **\n");
    for(i = 0; i < 5; i++)
    {
        addHead(&list, s1[i]);
        addTail(&list, s2[i]);
    }

    printNormal(&list);
    printReverse(&list);
    printf("머리 : %c\n", getHead(&list));
    printf("꼬리 : %c\n", getTail(&list));

    printf("\n** 양끝에서 3문자씩 삭제 **\n");
    for(i = 0; i < 3; i++)
    {
        removeHead(&list);
        removeTail(&list);
    }

    printNormal(&list);
    printReverse(&list);
    printf("머리 : %c\n", getHead(&list));
    printf("꼬리 : %c\n", getTail(&list));
}

/*
[출력 결과]
** 원본 **
K C A L B W H I T E
E T I H W B L A C K
머리 : K
꼬리 : E

** 양끝에서 3문자씩 삭제 **
L B W H
H W B L
머리 : L
꼬리 : H
*/