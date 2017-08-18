#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tree.h"

void main(void)
{
    NODE* pRoot = NULL;
    int i, count, key;

    // srand((unsigned) time(NULL));

    printf("���� : ");
    for(i = count = 0; i < 20; i++)
    {
        key = rand()%100;
        printf("%d ", key);

        count += insertNode(&pRoot, key);
    }
    printf("\n");
    printf("���� : %d\n\n", count);

    printf("[Ʈ��]\n");     
    printShape(pRoot, 1); 
    printf("\n");

    printf("���� : "); 
    printPreOrder(pRoot); 
    printf("\n");

    printf("���� : "); 
    printInOrder(pRoot);
    printf("\n");

    printf("���� : "); 
    printPostOrder(pRoot);
    printf("\n\n");

    /* ----------------------------------- */

    printf("** �����Ϸ��� ������ �Է��ϼ��� **\n");
    while(1)
    {
        printf("key : ");
        scanf("%d", &key);

        if(key < 0)
            break;

        if(findKey(pRoot, key) != NULL)
        {
            deleteNode(&pRoot, key);

            // printShape(pRoot, 1); 
            printInOrder(pRoot); 
            printf("\n");
        }
    }
}

/*
[��� ���]
���� : 41 67 34 0 69 24 78 58 62 64 5 45 81 27 61 91 95 42 27 36
���� : 19

[Ʈ��]
                                    [95]
                              [91]
                        [81]
                  [78]
            [69]
      [67]
                        [64]
                  [62]
                        [61]
            [58]
                  [45]
                        [42]
[41]
            [36]
      [34]
                        [27]
                  [24]
                        [ 5]
            [ 0]

���� : 41 34 0 24 5 27 36 67 58 45 42 62 61 64 69 78 81 91 95
���� : 0 5 24 27 34 36 41 42 45 58 61 62 64 67 69 78 81 91 95
���� : 5 27 24 0 36 34 42 45 61 64 62 58 95 91 81 78 69 67 41

** �����Ϸ��� ������ �Է��ϼ��� **
key : 50
key : 41
0 5 24 27 34 36 42 45 58 61 62 64 67 69 78 81 91 95
key : 95
0 5 24 27 34 36 42 45 58 61 62 64 67 69 78 81 91
key : 0
5 24 27 34 36 42 45 58 61 62 64 67 69 78 81 91
key : -1
*/