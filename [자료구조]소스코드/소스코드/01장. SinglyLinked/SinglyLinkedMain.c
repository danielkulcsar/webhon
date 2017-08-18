#pragma warning(disable:4996)
#include <stdio.h>
#include "SinglyLinked.h"

void main(void)
{
    NODE head = { 0 };
    char str[] = "BLACK";
    int i;

    printf("** 원본 **\n");
    for(i = 0; str[i] != '\0'; i++)
    {
        addHead(&head, str[i]);
        addTail(&head, str[i]);
    }

    print(&head);
    printf("머리 : %c\n", getHead(&head));
    printf("꼬리 : %c\n", getTail(&head));

    printf("\n** 양끝에서 3문자씩 삭제 **\n");
    for(i = 0; i < 3; i++)
    {
        removeHead(&head);
        removeTail(&head);
    }

    print(&head);
    printf("개수 : %d\n", countNode(&head));

    printf("\n** 모두 삭제 후 초기값 삽입 **\n");
    clear(&head);
    for(i = 0; str[i] != '\0'; i++)
        insertData(&head, str[i]);

    print(&head);

    printf("\n** 처음, 마지막, 중간 삭제 **\n");
    deleteData(&head, 'A');
    deleteData(&head, 'L');
    deleteData(&head, 'C');

    print(&head);
    clear(&head);
}

/*
[출력결과]
** 원본 **
K C A L B B L A C K
머리 : K
꼬리 : K

** 양끝에서 3문자씩 삭제 **
L B B L
개수 : 4

** 모두 삭제 후 초기값 삽입 **
A B C K L

** 처음, 마지막, 중간 삭제 **
B K
*/
