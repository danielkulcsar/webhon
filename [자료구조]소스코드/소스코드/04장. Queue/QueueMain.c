#pragma warning(disable:4996)
#include <stdio.h>
#include "Queue.h"

void printQueue(QUEUE* pq);
void printQueueData(QUEUE* pq);

void main(void)
{
    QUEUE q;
    char data;
    int  menu;

    initQueue(&q);

    while(1)
    {
        printf("0. ���� 1. �߰� 2. ���� 3. ��� - ");
        scanf("%d", &menu);
        fflush(stdin);

        if(menu == 0)
            break;

        switch(menu)
        {
        case 1 : 
            printf("���� : ");
            scanf("%c", &data);

            enQueue(&q, data);
            break;

        case 2 :
            if(isEmpty(&q))
                printf("���� : ť�� �����!!\n");
            else
                printf("���� : %c\n", deQueue(&q));
            break;

        case 3 :
            printQueue(&q);
            printQueueData(&q);
            break;
        }
    }
}

void printQueue(QUEUE* pq)
{
    int i;

    if(pq->front <= pq->rear)
    {
        for(i = 0; i < QUEUE_SIZE; i++)
        {
            if(i >= pq->front && i < pq->rear)
                printf("%c ", pq->array[i]);
            else
                printf("* ");
        }
    }
    else
    {
        for(i = 0; i < QUEUE_SIZE; i++)
        {
            if(i >= pq->front || i < pq->rear)
                printf("%c ", pq->array[i]);
            else
                printf("* ");
        }
    }
    printf("\n");
}

void printQueueData(QUEUE* pq)
{
    int i;
    for(i = pq->front; i != pq->rear; i=(i+1)%QUEUE_SIZE)
        printf("%c ", pq->array[i]);

    printf("\n");
}

/*
[��� ���]
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : R
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : A
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : I
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : N
0. ���� 1. �ֱ� 2. ���� 3. ��� - 3
R A I N *
R A I N
0. ���� 1. �ֱ� 2. ���� 3. ��� - 2
��� : R
0. ���� 1. �ֱ� 2. ���� 3. ��� - 2
��� : A
0. ���� 1. �ֱ� 2. ���� 3. ��� - 2
��� : I
0. ���� 1. �ֱ� 2. ���� 3. ��� - 3
* * * N *
N
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : B
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : O
0. ���� 1. �ֱ� 2. ���� 3. ��� - 1
�Է� : W
0. ���� 1. �ֱ� 2. ���� 3. ��� - 3
O W * N B
N B O W
0. ���� 1. �ֱ� 2. ���� 3. ��� - 0
*/
