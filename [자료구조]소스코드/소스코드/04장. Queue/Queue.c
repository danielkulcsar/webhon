#include "Queue.h"

void initQueue(QUEUE* pq)
{
    pq->front = pq->rear = 0;
}

void enQueue(QUEUE* pq, char data)
{
    pq->array[pq->rear] = data;
    pq->rear = (pq->rear+1) % QUEUE_SIZE;
}

char deQueue(QUEUE* pq)
{
    int save = pq->front;
    pq->front = (pq->front+1) % QUEUE_SIZE;

    return pq->array[save];
}

int isEmpty(QUEUE* pq)
{
    return pq->front == pq->rear;
}
