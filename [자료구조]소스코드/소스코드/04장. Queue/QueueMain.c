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
        printf("0. 종료 1. 추가 2. 삭제 3. 출력 - ");
        scanf("%d", &menu);
        fflush(stdin);

        if(menu == 0)
            break;

        switch(menu)
        {
        case 1 : 
            printf("문자 : ");
            scanf("%c", &data);

            enQueue(&q, data);
            break;

        case 2 :
            if(isEmpty(&q))
                printf("에러 : 큐가 비었다!!\n");
            else
                printf("문자 : %c\n", deQueue(&q));
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
[출력 결과]
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : R
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : A
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : I
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : N
0. 종료 1. 넣기 2. 빼기 3. 출력 - 3
R A I N *
R A I N
0. 종료 1. 넣기 2. 빼기 3. 출력 - 2
출력 : R
0. 종료 1. 넣기 2. 빼기 3. 출력 - 2
출력 : A
0. 종료 1. 넣기 2. 빼기 3. 출력 - 2
출력 : I
0. 종료 1. 넣기 2. 빼기 3. 출력 - 3
* * * N *
N
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : B
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : O
0. 종료 1. 넣기 2. 빼기 3. 출력 - 1
입력 : W
0. 종료 1. 넣기 2. 빼기 3. 출력 - 3
O W * N B
N B O W
0. 종료 1. 넣기 2. 빼기 3. 출력 - 0
*/
