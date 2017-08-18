#include <stdio.h>
#include "Priority.h"

void reheapDown(int* array, int size, int parent);
void reheapUp(int* array, int child);
void swap(int* p1, int* p2);

void init(PRIOR_QUEUE* pq)
{
    pq->size = 0;
}

void enQueue(PRIOR_QUEUE* pq, int data)
{
    pq->array[pq->size] = data;
    reheapUp(pq->array, pq->size++);
}

int deQueue(PRIOR_QUEUE* pq)
{
    pq->size--;

    swap(pq->array, pq->array+pq->size);
    reheapDown(pq->array, pq->size, 0);

    return pq->array[pq->size];
}

int isEmpty(PRIOR_QUEUE* pq)
{
    return pq->size <= 0;
}

void reheapDown(int* array, int size, int parent)
{
    int left, right, max;

    while(1)
    {
        left  = parent*2 + 1;
        right = parent*2 + 2;

        if(left >= size)
            break;

             if(left == size-1)                 max = left;
        else if(array[left] > array[right])     max = left;
        else                                    max = right;

        if(array[parent] >= array[max])
            break;

        swap(array+parent, array+max);
        parent = max;
    }
}

void reheapUp(int* array, int child)
{
    int parent;

    while(child > 0)
    {
        parent = (child-1) / 2;

        if(array[parent] >= array[child])
            break;

        swap(array+parent, array+child);
        child = parent;
    }
}

void swap(int* p1, int* p2)
{
    int tmp;

    tmp = *p1;
    *p1 = *p2;
    *p2 = tmp;
}

void printArray(PRIOR_QUEUE* pq)
{
    int i;
    for(i = 0; i < pq->size; i++)
        printf("%2d ", pq->array[i]);

    printf("\n");
}

void printShape(PRIOR_QUEUE* pq, int cur, int level)
{
    if(cur < pq->size)
    {
        int i;
        printShape(pq, cur*2+2, level+1);

        for(i = 1; i < level; i++)
            printf("      ");
        printf("[%2d]\n", pq->array[cur]);

        printShape(pq, cur*2+1, level+1);
    }
}
