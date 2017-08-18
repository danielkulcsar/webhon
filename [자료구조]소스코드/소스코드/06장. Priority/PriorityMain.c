#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include "Priority.h"

void main(void)
{
    PRIOR_QUEUE priorQueue;
    int i, n;

    init(&priorQueue);

    printf("���� : ");
    for(i = 0; i < 12; i++)
    {
        printf("%2d ", n = rand()%100);
        enQueue(&priorQueue, n);
    }
    printf("\n");

    printf("���� : ");
    printArray(&priorQueue);
    printf("\n");

    printShape(&priorQueue, 0, 1);
    printf("\n");

    printf("[������� ����]\n");
    for(i = 12; !isEmpty(&priorQueue); i--)
    {
        printf("%4d : ", i);
        printArray(&priorQueue);

        printf("���� : %2d\n", deQueue(&priorQueue));
    }
}

/*
[��� ���]
���� : 41 67 34  0 69 24 78 58 62 64  5 45
���� : 78 67 69 62 64 45 34  0 58 41  5 24

            [34]
      [69]
            [45]
                  [24]
[78]
                  [ 5]
            [64]
                  [41]
      [67]
                  [58]
            [62]
                  [ 0]

[������� ����]
  12 : 78 67 69 62 64 45 34  0 58 41  5 24
���� : 78
  11 : 69 67 45 62 64 24 34  0 58 41  5
���� : 69
  10 : 67 64 45 62 41 24 34  0 58  5
���� : 67
   9 : 64 62 45 58 41 24 34  0  5
���� : 64
   8 : 62 58 45  5 41 24 34  0
���� : 62
   7 : 58 41 45  5  0 24 34
���� : 58
   6 : 45 41 34  5  0 24
���� : 45
   5 : 41 24 34  5  0
���� : 41
   4 : 34 24  0  5
���� : 34
   3 : 24  5  0
���� : 24
   2 :  5  0
���� :  5
   1 :  0
���� :  0
*/
