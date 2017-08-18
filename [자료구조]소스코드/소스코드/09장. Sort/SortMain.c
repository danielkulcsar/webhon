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

    printf("[저속 정렬]\n");
    for(i = 0; i < 4; i++)
    {
        fillArray(array, size);

        printf("개수 : %d\n", size);
        bubbleTest(array, size);
        selectionTest(array, size);
        insertionTest(array, size);
        printf("\n");

        size *= 2;
    }

    printf("\n[고속 정렬]\n");
    for(i = 0; i < 4; i++)
    {
        fillArray(array, size);

        printf("개수 : %d\n", size);
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

    printf("거품 : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
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

    printf("선택 : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
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

    printf("삽입 : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
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

    printf("히프 : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
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

    printf("합병 : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
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

    printf("빠른 : %.3f\n", (end-start) / (double) CLOCKS_PER_SEC);
    free(temp);
}

/*
[출력 결과]
[저속 정렬]
개수 : 4096
거품 : 0.235
선택 : 0.031
삽입 : 0.031

개수 : 8192
거품 : 0.828
선택 : 0.141
삽입 : 0.094

개수 : 16384
거품 : 3.312
선택 : 0.578
삽입 : 0.375

개수 : 32768
거품 : 12.953
선택 : 2.313
삽입 : 1.500


[고속 정렬]
개수 : 65536
히프 : 0.047
합병 : 0.078
빠른 : 0.016

개수 : 131072
히프 : 0.109
합병 : 0.141
빠른 : 0.047

개수 : 262144
히프 : 0.234
합병 : 0.297
빠른 : 0.094

개수 : 524288
히프 : 0.515
합병 : 0.610
빠른 : 0.187
*/