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

    printf("원본 : ");
    for(i = count = 0; i < 20; i++)
    {
        key = rand()%100;
        printf("%d ", key);

        count += insertNode(&pRoot, key);
    }
    printf("\n");
    printf("성공 : %d\n\n", count);

    printf("[트리]\n");     
    printShape(pRoot, 1); 
    printf("\n");

    printf("전위 : "); 
    printPreOrder(pRoot); 
    printf("\n");

    printf("중위 : "); 
    printInOrder(pRoot);
    printf("\n");

    printf("후위 : "); 
    printPostOrder(pRoot);
    printf("\n\n");

    /* ----------------------------------- */

    printf("** 종료하려면 음수를 입력하세요 **\n");
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
[출력 결과]
원본 : 41 67 34 0 69 24 78 58 62 64 5 45 81 27 61 91 95 42 27 36
성공 : 19

[트리]
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

전위 : 41 34 0 24 5 27 36 67 58 45 42 62 61 64 69 78 81 91 95
중위 : 0 5 24 27 34 36 41 42 45 58 61 62 64 67 69 78 81 91 95
후위 : 5 27 24 0 36 34 42 45 61 64 62 58 95 91 81 78 69 67 41

** 종료하려면 음수를 입력하세요 **
key : 50
key : 41
0 5 24 27 34 36 42 45 58 61 62 64 67 69 78 81 91 95
key : 95
0 5 24 27 34 36 42 45 58 61 62 64 67 69 78 81 91
key : 0
5 24 27 34 36 42 45 58 61 62 64 67 69 78 81 91
key : -1
*/