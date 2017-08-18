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

    printf("[���� �߰�]\n");
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

    printf("[���� �߰�]\n");
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

    printf("[���� �켱]\n");    
    DepthFirstSearch(getVertex(0));     printf("\n");
    
    printf("[�ʺ� �켱]\n");    
    BreadthFirstSearch(getVertex(0));   printf("\n");

    printf("// *************************** //\n\n");

    while(1)
    {
        fflush(stdin);

        printf("������ ���� : ");
        scanf("%c", &name);

        if(findVertex(name) == -1)
            break;

        printf("\n[���� ����]\n");
        printAdjacent(name);

        deleteVertex(name);

        printf("\n");
        printEdge();
    }

    printf("\n// *************************** //\n\n");

    while(1)
    {
        fflush(stdin);

        printf("������ ���� : ");
        scanf("%c %c", &v, &u);

        if(findVertex(v) == -1 || findVertex(u) == -1)
            break;

        deleteEdge(v, u);

        printf("\n");
        printEdge();
    }
}

/*
[��� ���]
[���� �߰�]
P H Q G U M E A Y

[���� �߰�]
(Y,Q) (A,E) (A,M) (A,Y) (G,P) (P,E) (M,P) (U,A) (E,M) (Y,M)
(Q,P) (E,U) (Y,H)

[����+����]
P : (P,Q) (P,G) (P,M) (P,E)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
M : (M,P) (M,E) (M,A) (M,Y)
E : (E,P) (E,U) (E,M) (E,A)
A : (A,U) (A,M) (A,E) (A,Y)
Y : (Y,H) (Y,Q) (Y,M) (Y,A)

[����+���]
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

[���� �켱]
[0:P] [1:Q] [2:Y] [3:H] [4:M] [5:E] [6:U] [7:A] [8:G]

[�ʺ� �켱]
[0:P] [1:Q] [2:G] [3:M] [4:E] [5:Y] [6:A] [7:U] [8:H]

// *************************** //

������ ���� : M

[���� ����]
P E A Y

[����+����]
P : (P,Q) (P,G) (P,E)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
Y : (Y,H) (Y,Q) (Y,A)
E : (E,P) (E,U) (E,A)
A : (A,U) (A,Y) (A,E)

������ ���� : Y

[���� ����]
H Q A

[����+����]
P : (P,Q) (P,G) (P,E)
H :
Q : (Q,P)
G : (G,P)
U : (U,A) (U,E)
A : (A,U) (A,E)
E : (E,P) (E,U) (E,A)

������ ���� : 0

// *************************** //

������ ���� : E A

[����+����]
P : (P,Q) (P,G) (P,E)
H :
Q : (Q,P)
G : (G,P)
U : (U,A) (U,E)
A : (A,U)
E : (E,P) (E,U)

������ ���� : P E

[����+����]
P : (P,Q) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,A) (U,E)
A : (A,U)
E : (E,U)

������ ���� : 0 0
*/
