#pragma warning(disable:4996)
#include <stdio.h>
#include <string.h>
#include "Hash.h"

enum { BUCKET_COUNT = 7, BUCKET_SIZE = 512 };

typedef struct _BUCKET
{
    int  count;
    char bucket[BUCKET_SIZE][WORD_SIZE];
}
BUCKET;

static BUCKET g_hash[BUCKET_COUNT];

void init(void)
{
    int i;
    for(i = 0; i < BUCKET_COUNT; i++)
        g_hash[i].count = 0;
}

unsigned getHashCode(const char* s)
{
    int sum = 0;
    while(*s)
        sum += *s++;

    return sum%BUCKET_COUNT;
}

void insertKey(const char* s)
{
    BUCKET* pb = g_hash+getHashCode(s);

    strcpy(pb->bucket[pb->count], s);
    pb->count++;
}

int deleteKey(const char* s)
{
    BUCKET* pb = g_hash+getHashCode(s);
    int i;

    for(i = 0; i < pb->count; i++)
    {
        if(strcmp(pb->bucket[i], s) == 0)
        {
            strcpy(pb->bucket[i], pb->bucket[--pb->count]);
            return 1;
        }
    }

    return 0;
}

const char* findKey(const char* s)
{
    int i, code = getHashCode(s);

    for(i = 0; i < g_hash[code].count; i++)
    {
        if(strcmp(g_hash[code].bucket[i], s) == 0)
            return g_hash[code].bucket[i];
    }

    return NULL;
}

void printBucket(unsigned index)
{
    BUCKET* pb = g_hash+index;
    int i;

    printf("%d[%2d] : ", index, pb->count);
    for(i = 0; i < pb->count; i++)
        printf("%s ", pb->bucket[i]);

    printf("\n");
}

void printHash(void)
{
    int i;
    for(i = 0; i < BUCKET_COUNT; i++)
        printBucket(i);

    printf("\n");
}
