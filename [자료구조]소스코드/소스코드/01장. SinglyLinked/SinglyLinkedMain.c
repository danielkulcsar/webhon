#pragma warning(disable:4996)
#include <stdio.h>
#include "SinglyLinked.h"

void main(void)
{
    NODE head = { 0 };
    char str[] = "BLACK";
    int i;

    printf("** ���� **\n");
    for(i = 0; str[i] != '\0'; i++)
    {
        addHead(&head, str[i]);
        addTail(&head, str[i]);
    }

    print(&head);
    printf("�Ӹ� : %c\n", getHead(&head));
    printf("���� : %c\n", getTail(&head));

    printf("\n** �糡���� 3���ھ� ���� **\n");
    for(i = 0; i < 3; i++)
    {
        removeHead(&head);
        removeTail(&head);
    }

    print(&head);
    printf("���� : %d\n", countNode(&head));

    printf("\n** ��� ���� �� �ʱⰪ ���� **\n");
    clear(&head);
    for(i = 0; str[i] != '\0'; i++)
        insertData(&head, str[i]);

    print(&head);

    printf("\n** ó��, ������, �߰� ���� **\n");
    deleteData(&head, 'A');
    deleteData(&head, 'L');
    deleteData(&head, 'C');

    print(&head);
    clear(&head);
}

/*
[��°��]
** ���� **
K C A L B B L A C K
�Ӹ� : K
���� : K

** �糡���� 3���ھ� ���� **
L B B L
���� : 4

** ��� ���� �� �ʱⰪ ���� **
A B C K L

** ó��, ������, �߰� ���� **
B K
*/
