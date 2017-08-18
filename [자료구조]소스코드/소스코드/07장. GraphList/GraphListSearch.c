#pragma warning(disable:4996)
#include <stdio.h>
#include "SinglyLinked.h"
#include "GraphList.h"

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

extern int  g_count;
extern NODE g_list[MAX_VERTEX];

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
    char v = g_list[index].data;

    g_visit[index] = 1;
    printf("[%d:%c] ", order, v);

    for(i = 0; i < g_count; i++)
    {
        if(g_visit[i] == 0 && checkEdge(v, g_list[i].data) == 1)
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

        v = g_list[index].data;
        printf("[%d:%c] ", order++, v);

        for(i = 0; i < g_count; i++)
        {
            if(g_visit[i] == 0 && checkEdge(v, g_list[i].data) == 1)
            {
                g_visit[i] = 1;
                enQueue(i);
            }
        }
    }
    printf("\n");
}
