#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include "GraphList.h"

#define VERTEX_COUNT   9
#define EDGE_COUNT    13

void main(void)
{
    const char* vertex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int  i;
    char v, u, name;

    init();

    printf("[정점 추가]\n");
    for(i = 0; i < VERTEX_COUNT; )
    {
        name = vertex[rand()%26];

        if(findVertex(name) == -1)
        {
            printf("%c ", name);
            insertVertex(name);
            i++;
        }
    }
    printf("\n\n");

    printf("[간선 추가]\n");
    for(i = 0; i < EDGE_COUNT; )
    {
        v = getVertex(rand()%VERTEX_COUNT);
        u = getVertex(rand()%VERTEX_COUNT);

        if(v == u || checkEdge(v, u) == 1)
            continue;

        printf("(%c,%c) ", v, u);
        if(i%10 == 9)
            printf("\n");
        
        insertEdge(v, u);
        i++;
    }

    printf("\n\n");
    printEdge();
//    printMatrix();

    printf("[깊이 우선]\n");    
    DepthFirstSearch(getVertex(0));     printf("\n");
    
    printf("[너비 우선]\n");    
    BreadthFirstSearch(getVertex(0));   printf("\n");

    printf("// *************************** //\n\n");

    while(1)
    {
        fflush(stdin);

        printf("삭제할 정점 : ");
        scanf("%c", &name);

        if(findVertex(name) == -1)
            break;

        printf("\n[인접 정점]\n");
        printAdjacent(name);

        deleteVertex(name);

        printf("\n");
        printEdge();
    }

    printf("\n// *************************** //\n\n");

    while(1)
    {
        fflush(stdin);

        printf("삭제할 간선 : ");
        scanf("%c %c", &v, &u);

        if(findVertex(v) == -1 || findVertex(u) == -1)
            break;

        deleteEdge(v, u);

        printf("\n");
        printEdge();
    }
}

/*
[출력 결과]
[정점 추가]
P H Q G U M E A Y

[간선 추가]
(Y,Q) (A,E) (A,M) (A,Y) (G,P) (P,E) (M,P) (U,A) (E,M) (Y,M)
(Q,P) (E,U) (Y,H)

[정점+간선]
P : (P,Q) (P,M) (P,E) (P,G)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
M : (M,Y) (M,E) (M,P) (M,A)
E : (E,U) (E,M) (E,P) (E,A)
A : (A,U) (A,Y) (A,M) (A,E)
Y : (Y,H) (Y,M) (Y,A) (Y,Q)

[깊이 우선]
[0:P] [1:Q] [2:Y] [3:H] [4:M] [5:E] [6:U] [7:A] [8:G]

[너비 우선]
[0:P] [1:Q] [2:G] [3:M] [4:E] [5:Y] [6:A] [7:U] [8:H]

// *************************** //

삭제할 정점 : M

[인접 정점]
Y E P A

[정점+간선]
P : (P,Q) (P,E) (P,G)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
Y : (Y,H) (Y,A) (Y,Q)
E : (E,U) (E,P) (E,A)
A : (A,U) (A,Y) (A,E)

삭제할 정점 : Y

[인접 정점]
H A Q

[정점+간선]
P : (P,Q) (P,E) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,E) (U,A)
A : (A,U) (A,E)
E : (E,U) (E,P) (E,A)

삭제할 정점 : 0

// *************************** //

삭제할 간선 : E A

[정점+간선]
P : (P,Q) (P,E) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,E) (U,A)
A : (A,U)
E : (E,U) (E,P)

삭제할 간선 : P E

[정점+간선]
P : (P,Q) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,E) (U,A)
A : (A,U)
E : (E,U)

삭제할 간선 : 0 0
*/
