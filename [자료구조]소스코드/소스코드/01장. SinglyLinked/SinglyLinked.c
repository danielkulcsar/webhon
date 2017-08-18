#include <stdio.h>
#include <stdlib.h>
#include "SinglyLinked.h"

void addHead(NODE* pHead, char data)
{
    NODE* pNode = malloc(sizeof(NODE));
    pNode->data = data;

    pNode->next = pHead->next;
    pHead->next = pNode;
}

void addTail(NODE* pHead, char data)
{
    NODE* cur = pHead;
    while(cur->next != NULL)
        cur = cur->next;

    addHead(cur, data);
}

void removeHead(NODE* pHead)
{
    NODE* pNode = pHead->next;
    pHead->next = pNode->next;
    free(pNode);
}

void removeTail(NODE* pHead)
{
    NODE* cur = pHead;
    while(cur->next->next != NULL)
        cur = cur->next;

    removeHead(cur);
}

void print(NODE* pHead)
{
    NODE* cur = pHead->next;
    while(cur != NULL)
    {
        printf("%c ", cur->data);
        cur = cur->next;
    }

    printf("\n");
}

char getHead(NODE* pHead)
{
    return pHead->next->data;
}

char getTail(NODE* pHead)
{
    NODE* cur = pHead;
    while(cur->next != NULL)
        cur = cur->next;

    return cur->data;
}

int isEmpty(NODE* pHead)
{
    return pHead->next == NULL;
}

int countNode(NODE* pHead)
{
    int count;
    NODE* cur = pHead->next;

    for(count = 0; cur != NULL; count++)
        cur = cur->next;

    return count;
}

void clear(NODE* pHead)
{
    while(isEmpty(pHead) == 0)
        removeHead(pHead);
}

NODE* findData(NODE* pHead, char data)
{
    NODE* cur = pHead->next;
    while(cur != NULL)
    {
        if(cur->data == data)
            break;

        cur = cur->next;
    }

    return cur;
}

void insertData(NODE* pHead, char data)
{
    NODE* cur = pHead;
    while(cur->next != NULL)
    {
        if(cur->next->data >= data)
            break;

        cur = cur->next;
    }

    addHead(cur, data);
}

void deleteData(NODE* pHead, char data)
{
    NODE* cur = pHead;
    while(cur->next != NULL)
    {
        if(cur->next->data == data)
        {
            removeHead(cur);
            break;
        }

        cur = cur->next;
    }
}
