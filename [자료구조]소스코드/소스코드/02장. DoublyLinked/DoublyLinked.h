#pragma once

typedef struct _NODE
{
    char data;
    struct _NODE* prev, * next;
}
NODE;

typedef struct 
{
    NODE* pHead, * pTail;
}
DOUBLE_LIST;

NODE* makeNode(char data);
void addHead(DOUBLE_LIST* pList, char data);
void addTail(DOUBLE_LIST* pList, char data);
void removeHead(DOUBLE_LIST* pList);
void removeTail(DOUBLE_LIST* pList);
void printNormal(DOUBLE_LIST* pList);
void printReverse(DOUBLE_LIST* pList);
char getHead(DOUBLE_LIST* pList);
char getTail(DOUBLE_LIST* pList);
int isEmpty(DOUBLE_LIST* pList);
