#pragma warning(disable:4996)
#include <stdio.h>

int linearSearch(int* array, int size, int value)
{
    int i;
    for(i = 0; i < size; i++)
    {
        if(array[i] == value)
        {
            printf("È½¼ö : ¼º°ø [%d¹ø]\n", i);
            return i;
        }
    }

    printf("È½¼ö : ½ÇÆÐ [%d¹ø]\n", i);
    return -1;
}

int linearSearchSort(int* array, int size, int value)
{
    int i;
    for(i = 0; i < size; i++)
    {
        if(array[i] >= value)
        {
            if(array[i] == value) 
            {
                printf("È½¼ö : ¼º°ø [%d¹ø]\n", i);
                return i;
            }
            else
                break;
        }
    }

    printf("È½¼ö : ½ÇÆÐ [%d¹ø]\n", i);
    return -1;
}

int binarySearch(int* array, int size, int value)
{
    int i, left, right, middle;

    left = 0, right = size-1;
    for(i = 0; left <= right; i++)
    {
        middle = (left+right)/2;

        if(array[middle] == value)
        {
            printf("È½¼ö : ¼º°ø [%d¹ø]\n", i);
            return middle;
        }

        if(array[middle] < value)
            left  = middle+1;
        else
            right = middle-1;
    }

    printf("È½¼ö : ½ÇÆÐ [%d¹ø]\n", i);
    return -1;
}
