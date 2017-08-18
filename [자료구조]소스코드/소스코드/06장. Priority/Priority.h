#pragma once

typedef struct
{
    int array[512];
    int size;
}
PRIOR_QUEUE;

void init(PRIOR_QUEUE* pq);
void enQueue(PRIOR_QUEUE* pq, int data);
int deQueue(PRIOR_QUEUE* pq);
int isEmpty(PRIOR_QUEUE* pq);

void printArray(PRIOR_QUEUE* pq);
void printShape(PRIOR_QUEUE* pq, int cur, int level);
