#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include "GraphMatrix.h"

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
    printMatrix();

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
P : (P,Q) (P,G) (P,M) (P,E)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
M : (M,P) (M,E) (M,A) (M,Y)
E : (E,P) (E,U) (E,M) (E,A)
A : (A,U) (A,M) (A,E) (A,Y)
Y : (Y,H) (Y,Q) (Y,M) (Y,A)

[정점+행렬]
  | P H Q G U M E A Y
--+------------------
P | 0 0 1 1 0 1 1 0 0
H | 0 0 0 0 0 0 0 0 1
Q | 1 0 0 0 0 0 0 0 1
G | 1 0 0 0 0 0 0 0 0
U | 0 0 0 0 0 0 1 1 0
M | 1 0 0 0 0 0 1 1 1
E | 1 0 0 0 1 1 0 1 0
A | 0 0 0 0 1 1 1 0 1
Y | 0 1 1 0 0 1 0 1 0

[깊이 우선]
[0:P] [1:Q] [2:Y] [3:H] [4:M] [5:E] [6:U] [7:A] [8:G]

[너비 우선]
[0:P] [1:Q] [2:G] [3:M] [4:E] [5:Y] [6:A] [7:U] [8:H]

// *************************** //

삭제할 정점 : M

[인접 정점]
P E A Y

[정점+간선]
P : (P,Q) (P,G) (P,E)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
Y : (Y,H) (Y,Q) (Y,A)
E : (E,P) (E,U) (E,A)
A : (A,U) (A,Y) (A,E)

삭제할 정점 : Y

[인접 정점]
H Q A

[정점+간선]
P : (P,Q) (P,G) (P,E)
H :
Q : (Q,P)
G : (G,P)
U : (U,A) (U,E)
A : (A,U) (A,E)
E : (E,P) (E,U) (E,A)

삭제할 정점 : 0

// *************************** //

삭제할 간선 : E A

[정점+간선]
P : (P,Q) (P,G) (P,E)
H :
Q : (Q,P)
G : (G,P)
U : (U,A) (U,E)
A : (A,U)
E : (E,P) (E,U)

삭제할 간선 : P E

[정점+간선]
P : (P,Q) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,A) (U,E)
A : (A,U)
E : (E,U)

삭제할 간선 : 0 0
*/
