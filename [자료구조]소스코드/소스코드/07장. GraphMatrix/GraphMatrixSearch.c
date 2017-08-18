#pragma warning(disable:4996)
#include <stdio.h>
#include "GraphMatrix.h"

void enQueue(int data);
int  deQueue(void);
int  isEmptyQueue(void);

static int g_queue[512];
static int g_front, g_rear;

void enQueue(int data)
{
    g_queue[g_rear] = data;
    g_rear = (g_rear+1) % 512;
}

int deQueue(void)
{
    int save = g_front;
    g_front = (g_front+1) % 512;

    return g_queue[save];
}

int isEmptyQueue(void)
{
    return g_front == g_rear;
}

///////////////////////////////////////

int DepthFirstSearchReal(int index, int order);

extern char g_vertex[MAX_VERTEX];
extern int  g_count;
extern int  g_matrix[MAX_VERTEX][MAX_VERTEX];

static int  g_visit[MAX_VERTEX];

void DepthFirstSearch(char v)
{
    int i, find = findVertex(v);

    if(find != -1)
    {
        for(i = 0; i < g_count; i++)
            g_visit[i] = 0;

        DepthFirstSearchReal(find, 0);
        printf("\n");
    }
}

int DepthFirstSearchReal(int index, int order)
{
    int  i;
    char v = g_vertex[index];

    g_visit[index] = 1;
    printf("[%d:%c] ", order, v);

    for(i = 0; i < g_count; i++)
    {
        if(g_visit[i] == 0 && checkEdge(v, g_vertex[i]) == 1)
            order = DepthFirstSearchReal(i, order+1);
    }

    return order;
}

void BreadthFirstSearch(char v)
{
    int  i, index, order = 0, find = findVertex(v);

    if(find == -1)
        return;

    for(i = 0; i < g_count; i++)
        g_visit[i] = 0;

    g_visit[find] = 1;
    enQueue(find);

    while(!isEmptyQueue())
    {
        index = deQueue();

        v = g_vertex[index];
        printf("[%d:%c] ", order++, v);

        for(i = 0; i < g_count; i++)
        {
//            if(g_visit[i] == 0 && g_matrix[index][i] == 1)
            if(g_visit[i] == 0 && checkEdge(v, g_vertex[i]) == 1)
            {
                g_visit[i] = 1;
                enQueue(i);
            }
        }
    }
    printf("\n");
}
