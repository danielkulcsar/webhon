#pragma warning(disable:4996)
#include <stdio.h>
#include "GraphMatrix.h"

char g_vertex[MAX_VERTEX];
int  g_count;
int  g_matrix[MAX_VERTEX][MAX_VERTEX];

void init(void)
{
    int i, j;
    for(i = 0; i < MAX_VERTEX; i++)
        g_vertex[i] = '*';

    for(i = 0; i < MAX_VERTEX; i++)
    for(j = 0; j < MAX_VERTEX; j++)
        g_matrix[i][j] = 0;

    g_count = 0;
}

void insertVertex(char name)
{
    g_vertex[g_count] = name;
    g_count++;
}

int insertEdge(char v, char u)
{
    int vi = findVertex(v),
        ui = findVertex(u);

    if(vi == -1 || ui == -1)
        return 0;

    g_matrix[vi][ui] = g_matrix[ui][vi] = 1;
    return 1;
}

int deleteVertex(char name)
{
    int i, find = findVertex(name);

    if(find == -1)
        return 0;

    g_vertex[find] = g_vertex[--g_count];

    for(i = 0; i < g_count; i++)
        g_matrix[i][find] = g_matrix[find][i] = g_matrix[i][g_count];

    g_matrix[find][find] = 0;
    return 1;
}

int deleteEdge(char v, char u)
{
    int vi = findVertex(v),
        ui = findVertex(u);

    if(vi == -1 || ui == -1)
        return 0;

    g_matrix[vi][ui] = g_matrix[ui][vi] = 0;
    return 1;
}

int isEmptyGraph(void)
{
    return g_count <= 0;
}

int checkEdge(char v, char u)
{
    int vi = findVertex(v),
        ui = findVertex(u);

    return g_matrix[vi][ui];
}

char getVertex(int index)
{
    return g_vertex[index];
}

int findVertex(char name)
{
    int i;
    for(i = 0; i < g_count; i++)
    {
        if(g_vertex[i] == name)
            return i;
    }

    return -1;
}

void printMatrix(void)
{
    int i, j;
    printf("[Á¤Á¡+Çà·Ä]\n");

    // À§ÂÊ ¶Ñ²±(µÎ ÁÙ)
    printf("  | ");
    for(i = 0; i < g_count; i++)
        printf("%c ", g_vertex[i]);
    printf("\n");

    printf("--+");
    for(i = 0; i < g_count; i++)
        printf("--");
    printf("\n");
    
    // ½ÇÁ¦ µ¥ÀÌÅÍ
    for(i = 0; i < g_count; i++)
    {
        // ¿ÞÂÊ ¶Ñ²±
        printf("%c | ", g_vertex[i]);

        for(j = 0; j < g_count; j++)
            printf("%d ", g_matrix[i][j]);

        printf("\n");
    }

    printf("\n");
}

void printVertex(void)
{
    int i;
    printf("[Á¤Á¡]\n");
    for(i = 0; i < g_count; i++)
        printf("%c ", g_vertex[i]);

    printf("\n\n");
}

void printEdge(void)
{
    int i, j;
    printf("[Á¤Á¡+°£¼±]\n");
    for(i = 0; i < g_count; i++)
    {
        printf("%c : ", g_vertex[i]);

        for(j = 0; j < g_count; j++)
        {
            if(g_matrix[i][j] == 1)
                printf("(%c,%c) ", g_vertex[i], g_vertex[j]);
        }
    
        printf("\n");
    }

    printf("\n");
}

void printAdjacent(char name)
{
    int i, find = findVertex(name);

    if(find != -1)
    {
        for(i = 0; i < g_count; i++)
        {
            if(g_matrix[find][i] == 1)
                printf("%c ", g_vertex[i]);
        }

        printf("\n");
    }
}
