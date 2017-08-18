#pragma warning(disable:4996)
#include <stdio.h>
#include "SinglyLinked.h"
#include "GraphList.h"

int  g_count;
NODE g_list[MAX_VERTEX];

void init(void)
{
    int i;
    for(i = 0; i < MAX_VERTEX; i++)
    {
        g_list[i].data = '*';
        g_list[i].next = NULL;
    }

    g_count = 0;
}

void insertVertex(char name)
{
    g_list[g_count].data = name;
    g_count++;
}

int insertEdge(char v, char u)
{
    int vi = findVertex(v),
        ui = findVertex(u);

    if(vi == -1 || ui == -1)
        return 0;

    addHead(g_list+vi, u);
    addHead(g_list+ui, v);
    return 1;
}

int deleteVertex(char name)
{
    int i, find = findVertex(name);

    if(find != -1)
    {
        g_list[find].data = g_list[--g_count].data;

        clear(g_list+find);
        g_list[find] = g_list[g_count];

        for(i = 0; i < g_count; i++)
            deleteData(g_list+i, name);
    }

    return find != -1;
}

// 정점은 반드시 존재해야 한다
int deleteEdge(char v, char u)
{
    int ret = 0;
    int vi = findVertex(v),
        ui = findVertex(u);

    if(vi != -1 && ui != -1 && checkEdge(v, u) == 1)
    {
        deleteData(g_list+vi, u);
        deleteData(g_list+ui, v);
    }

    return ret;
}

int isEmptyGraph(void)
{
    return g_count <= 0;
}

// 정점은 반드시 존재해야 한다
int checkEdge(char v, char u)
{
    int vi = findVertex(v);
    return findData(g_list+vi, u) != NULL;
}

char getVertex(int index)
{
    return g_list[index].data;
}

int findVertex(char name)
{
    int i;
    for(i = 0; i < g_count; i++)
    {
        if(g_list[i].data == name)
            return i;
    }

    return -1;
}

void printVertex(void)
{
    int i;
    printf("[정점]\n");
    for(i = 0; i < g_count; i++)
        printf("%c ", g_list[i].data);

    printf("\n\n");
}

void printEdge(void)
{
    int i;
    NODE* cur;

    printf("[정점+간선]\n");
    for(i = 0; i < g_count; i++)
    {
        printf("%c : ", g_list[i].data);

        for(cur = g_list[i].next; cur != NULL; cur = cur->next)
            printf("(%c,%c) ", g_list[i].data, cur->data);
    
        printf("\n");
    }

    printf("\n");
}

void printAdjacent(char name)
{
    int find = findVertex(name);

    if(find != -1)
    {
        NODE* cur;
        for(cur = g_list[find].next; cur != NULL; cur = cur->next)
            printf("%c ", cur->data);
    
        printf("\n");
    }
}
