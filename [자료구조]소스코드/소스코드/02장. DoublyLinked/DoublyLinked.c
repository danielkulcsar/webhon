#include <stdio.h>
#include <stdlib.h>
#include "DoublyLinked.h"

NODE* makeNode(char data)
{
    NODE* node = malloc(sizeof(NODE));
    node->data = data;
    node->prev = node->next = NULL;

    return node;
}

void addHead(DOUBLE_LIST* pList, char data)
{
    NODE* node = makeNode(data);

    if(isEmpty(pList) == 1)
    {
        pList->pHead = pList->pTail = node;
    }
    else
    {
        node->prev = NULL;
        node->next = pList->pHead;

        pList->pHead->prev = node;
        pList->pHead       = node;
    }
}

void addTail(DOUBLE_LIST* pList, char data)
{
    NODE* node = makeNode(data);

    if(isEmpty(pList) == 1)
    {
        pList->pHead = pList->pTail = node;
    }
    else
    {
        node->prev = pList->pTail;
        node->next = NULL;

        pList->pTail->next = node;
        pList->pTail       = node;
    }
}

void removeHead(DOUBLE_LIST* pList)
{
    if(pList->pHead != NULL)
    {
        NODE* del = pList->pHead;

        if(pList->pHead == pList->pTail)
        {
            pList->pHead = pList->pTail = NULL;
        }
        else
        {
            pList->pHead->next->prev = NULL;
            pList->pHead             = pList->pHead->next;
        }

        free(del);
    }
}

void removeTail(DOUBLE_LIST* pList)
{
    if(pList->pTail != NULL)
    {
        NODE* del = pList->pTail;

        if(pList->pHead == pList->pTail)
        {
            pList->pHead = pList->pTail = NULL;
        }
        else
        {
            pList->pTail->prev->next = NULL;
            pList->pTail             = pList->pTail->prev;
        }

        free(del);
    }
}

void printNormal(DOUBLE_LIST* pList)
{
    NODE* cur;
    for(cur = pList->pHead; cur != NULL; cur = cur->next)
        printf("%c ", cur->data);

    printf("\n");
}

void printReverse(DOUBLE_LIST* pList)
{
    NODE* cur;
    for(cur = pList->pTail; cur != NULL; cur = cur->prev)
        printf("%c ", cur->data);

    printf("\n");
}

char getHead(DOUBLE_LIST* pList)
{
    return pList->pHead->data;
}

char getTail(DOUBLE_LIST* pList)
{
    return pList->pTail->data;
}

int isEmpty(DOUBLE_LIST* pList)
{
    return pList->pHead == NULL;
}
