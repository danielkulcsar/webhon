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
//    printMatrix();

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
P : (P,Q) (P,M) (P,E) (P,G)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
M : (M,Y) (M,E) (M,P) (M,A)
E : (E,U) (E,M) (E,P) (E,A)
A : (A,U) (A,Y) (A,M) (A,E)
Y : (Y,H) (Y,M) (Y,A) (Y,Q)

[���� �켱]
[0:P] [1:Q] [2:Y] [3:H] [4:M] [5:E] [6:U] [7:A] [8:G]

[�ʺ� �켱]
[0:P] [1:Q] [2:G] [3:M] [4:E] [5:Y] [6:A] [7:U] [8:H]

// *************************** //

������ ���� : M

[���� ����]
Y E P A

[����+����]
P : (P,Q) (P,E) (P,G)
H : (H,Y)
Q : (Q,P) (Q,Y)
G : (G,P)
U : (U,E) (U,A)
Y : (Y,H) (Y,A) (Y,Q)
E : (E,U) (E,P) (E,A)
A : (A,U) (A,Y) (A,E)

������ ���� : Y

[���� ����]
H A Q

[����+����]
P : (P,Q) (P,E) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,E) (U,A)
A : (A,U) (A,E)
E : (E,U) (E,P) (E,A)

������ ���� : 0

// *************************** //

������ ���� : E A

[����+����]
P : (P,Q) (P,E) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,E) (U,A)
A : (A,U)
E : (E,U) (E,P)

������ ���� : P E

[����+����]
P : (P,Q) (P,G)
H :
Q : (Q,P)
G : (G,P)
U : (U,E) (U,A)
A : (A,U)
E : (E,U)

������ ���� : 0 0
*/
