#include <stdio.h>
#include <malloc.h>
#include "Sort.h"

void swap(int* p1, int* p2)
{
    int tmp;

    tmp = *p1;
    *p1 = *p2;
    *p2 = tmp;
}

void bubbleSort(int* array, int size)
{
    int i;
    for(i = size; i > 1; i--)
        bubbleUp(array, i);
}

void bubbleUp(int* array, int size)
{
    int i;
    for(i = 1; i < size; i++)
    {
        if(array[i-1] > array[i])
            swap(array+i-1, array+i);
    }
}

void selectionSort(int* array, int size)
{
    int i;
    for(i = size; i > 1; i--)
        selection(array, i);
}

void selection(int* array, int size)
{
    int i, max = 0;
    for(i = 1; i < size; i++)
    {
        if(array[i] > array[max])
            max = i;
    }

    swap(array+max, array+size-1);
}

void insertionSort(int* array, int size)
{
    int i;
    for(i = 2; i <= size; i++)
        insertion(array, i);
}

void insertion(int* array, int size)
{
    int i, unsorted = array[size-1];
    for(i = size-2; i >= 0; i--)
    {
        if(unsorted >= array[i])
            break;

        array[i+1] = array[i];
    }

    array[i+1] = unsorted;
}

void heapSort(int* array, int size)
{
    int i;
    for(i = size/2; i >= 0; i--)
        reheapDown(array, size, i);

    for(i = size-1; i > 0; i--)
    {
        swap(array, array+i);
        reheapDown(array, i, 0);
    }
}

void reheapDown(int* array, int size, int parent)
{
    int left, right, max;

    while(1)
    {
        left  = parent*2 + 1;
        right = parent*2 + 2;

        if(left >= size)
            break;

             if(left == size-1)                 max = left;
        else if(array[left] > array[right])     max = left;
        else                                    max = right;

        if(array[parent] >= array[max])
            break;

        swap(array+parent, array+max);
        parent = max;
    }
}

void mergeSort(int* array, int size)
{
    if(size > 1)
    {
        mergeSort(array,        size/2     );
        mergeSort(array+size/2, size-size/2);

        merge(array, size/2, size);
    }
}

void merge(int* array, int middle, int size)
{
    int i, left, right;
    int* temp = malloc(size*sizeof(int));

    left  = 0;
    right = middle;
    for(i = 0; left < middle && right < size; i++)
    {
        if(array[left] <= array[right])     temp[i] = array[left++];
        else                                temp[i] = array[right++];
    }

    while(left < middle)
        temp[i++] = array[left++];

    while(right < size)
        temp[i++] = array[right++];

    for(i = 0; i < size; i++)
        array[i] = temp[i];

    free(temp);
}

void quickSort(int* array, int size)
{
    if(size > 1)
    {
        int pivot = split(array, size);

        quickSort(array,         pivot       );
        quickSort(array+pivot+1, size-pivot-1);
    }
}

int split(int* array, int size)
{
    int pivot = array[0], left = 1, right = size-1;

    while(1)
    {
        while(array[left] < pivot)
            left++;

        while(array[right] > pivot)
            right--;

        if(left >= right)
            break;

        swap(array+left, array+right);
        left++, right--;
    }

    swap(array, array+right);
    return right;
}
