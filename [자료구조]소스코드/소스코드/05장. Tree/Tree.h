#pragma once

typedef struct _NODE
{
    int key;
    struct _NODE* left, * right;
}
NODE;

void printPreOrder(NODE* pRoot);
void printInOrder(NODE* pRoot);
void printPostOrder(NODE* pRoot);
void printShape(NODE* pRoot, int level);

NODE* makeNode(int key);
int insertNode(NODE** ppRoot, int key);
int deleteNode(NODE** ppRoot, int key);
NODE* findKey(NODE* pRoot, int key);
