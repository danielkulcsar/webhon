#include <stdio.h>
#include <stdlib.h>
#include "Tree.h"

void printPreOrder(NODE* pRoot)
{
    if(pRoot != NULL)
    {
        printf("%d ", pRoot->key);
        printPreOrder(pRoot->left);
        printPreOrder(pRoot->right);
    }
}

void printInOrder(NODE* pRoot)
{
    if(pRoot != NULL)
    {
        printInOrder(pRoot->left);
        printf("%d ", pRoot->key);
        printInOrder(pRoot->right);
    }
}

void printPostOrder(NODE* pRoot)
{
    if(pRoot != NULL)
    {
        printPostOrder(pRoot->left);
        printPostOrder(pRoot->right);
        printf("%d ", pRoot->key);
    }
}

void printShape(NODE* pRoot, int level)
{
    if(pRoot != NULL)
    {
        int i;
        printShape(pRoot->right, level+1);

        for(i = 1; i < level; i++)
            printf("      ");
        printf("[%2d]\n", pRoot->key);

        printShape(pRoot->left, level+1);
    }
}

NODE* makeNode(int key)
{
    NODE* node = malloc(sizeof(NODE));

    node->key = key;
    node->left = node->right = NULL;

    return node;
}

int insertNode(NODE** ppRoot, int key)
{
    while(*ppRoot != NULL)
    {
        if(key == (*ppRoot)->key)
            return 0;

        if(key < (*ppRoot)->key)	
            ppRoot = &(*ppRoot)->left;
        else			            
            ppRoot = &(*ppRoot)->right;
    }

    *ppRoot = makeNode(key);
    return 1;
}

int deleteNode(NODE** ppRoot, int key)
{
    NODE* node;
    while(node = *ppRoot)
    {
        if(key == node->key)
            break;

        if(key < node->key)     ppRoot = &node->left;
        else                    ppRoot = &node->right;
    }

    /////////////////////////

    if(node)
    {
        if(node->left && node->right)
        {
            NODE** ppCur = &(*ppRoot)->left;
            while((*ppCur)->right)
                ppCur = &(*ppCur)->right;

            (*ppRoot)->key = (*ppCur)->key;
            
            ppRoot = ppCur;
            node = *ppRoot;
        }

        if(node->left)      *ppRoot = node->left;
        else                *ppRoot = node->right;

        free(node);
        return 1;
    }

    return 0;
}

NODE* findKey(NODE* pRoot, int key)
{
    NODE* cur = pRoot;
    while(cur != NULL)
    {
        if(cur->key == key)
            return cur;

        if(key < cur->key)      cur = cur->left;
        else                    cur = cur->right;
    }
    
    return NULL;
}
