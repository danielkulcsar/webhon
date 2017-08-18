#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "Sort.h"

void fillArray(int* array, int size);
void printArray(const int* array, int size);

void bubbleTest(const int* array, int size);
void selectionTest(const int* array, int size);
void insertionTest(const int* array, int size);
void heapTest(const int* array, int size);
void mergeTest(const int* array, int size);
void quickTest(const int* array, int size);

void main(void)
{
    int* array = NULL;
    int  i, size;

    srand((unsigned) time(NULL));
    
    size = 4096;
    array = malloc(size*128*sizeof(int));

    printf("[���� ����]\n");
    for(i = 0; i < 4; i++)
    {
        fillArray(array, size);

        printf("���� : %d\n", size);
        bubbleTest(array, size);
        selectionTest(array, size);
        insertionTest(array, size);
        printf("\n");

        size *= 2;
    }

    printf("\n[��� ����]\n");
    for(i = 0; i < 4; i++)
    {
        fillArray(array, size);

        printf("���� : %d\n", size);
        heapTest(array, size);
        mergeTest(array, size);
        quickTest(array, size);
        printf("\n");

        size *= 2;
    }

    free(array);
}

void fillArray(int* array, int size)
{
    int i;
    for(i = 0; i < size; i++)
        array[i] = rand() * rand();
}

void printArray(const int* array, int size)
{
    int i;
    for(i = 0; i < size; i++)
    {
        printf("%10d ", array[i]);

        if(i%7 == 6)
            printf("\n");
    }
    printf("\n\n");
}

void bubbleTest(const int* array, int size)
{
    clock_t start, end;
    int* temp;

    temp = malloc(size*sizeof(int));
    memcpy(temp, array, size*sizeof(int));

    start = clock();    bubbleSort(temp, size);
    end   = clock();

    printf("��ǰ : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

void selectionTest(const int* array, int size)
{
    clock_t start, end;
    int* temp;

    temp = malloc(size*sizeof(int));
    memcpy(temp, array, size*sizeof(int));

    start = clock();    selectionSort(temp, size);
    end   = clock();

    printf("���� : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

void insertionTest(const int* array, int size)
{
    clock_t start, end;
    int* temp;

    temp = malloc(size*sizeof(int));
    memcpy(temp, array, size*sizeof(int));

    start = clock();    insertionSort(temp, size);
    end   = clock();

    printf("���� : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

void heapTest(const int* array, int size)
{
    clock_t start, end;
    int* temp;

    temp = malloc(size*sizeof(int));
    memcpy(temp, array, size*sizeof(int));

    start = clock();    heapSort(temp, size);
    end   = clock();

    printf("���� : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

void mergeTest(const int* array, int size)
{
    clock_t start, end;
    int* temp;

    temp = malloc(size*sizeof(int));
    memcpy(temp, array, size*sizeof(int));

    start = clock();    mergeSort(temp, size);
    end   = clock();

    printf("�պ� : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

void quickTest(const int* array, int size)
{
    clock_t start, end;
    int* temp;

    temp = malloc(size*sizeof(int)+4);      temp[size] = INT_MAX;
    memcpy(temp, array, size*sizeof(int));

    start = clock();    quickSort(temp, size);
    end   = clock();

    printf("���� : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

/*
[��� ���]
[���� ����]
���� : 4096
��ǰ : 0.235
���� : 0.031
���� : 0.031

���� : 8192
��ǰ : 0.828
���� : 0.141
���� : 0.094

���� : 16384
��ǰ : 3.312
���� : 0.578
���� : 0.375

���� : 32768
��ǰ : 12.953
���� : 2.313
���� : 1.500


[��� ����]
���� : 65536
���� : 0.047
�պ� : 0.078
���� : 0.016

���� : 131072
���� : 0.109
�պ� : 0.141
���� : 0.047

���� : 262144
���� : 0.234
�պ� : 0.297
���� : 0.094

���� : 524288
���� : 0.515
�պ� : 0.610
���� : 0.187
*/