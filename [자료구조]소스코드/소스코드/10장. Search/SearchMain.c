#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include "Search.h"

enum { ARRAY_SIZE = 100, MAX_VALUE = 1000 };

void fillArray(int* array, int size);
void printArray(const int* array, int size);
int compare(const void* p1, const void* p2);

void main(void)
{
    int array[ARRAY_SIZE];
    int value, pos;

    fillArray(array, ARRAY_SIZE);
    printArray(array, ARRAY_SIZE);

    printf("[���� Ž��]\n");
    while(1)
    {
        printf("�Է� : ");
        scanf("%d", &value);

        if(value < 0 || value >= MAX_VALUE)
            break;

        pos = linearSearch(array, ARRAY_SIZE, value);

        printf("��� : %s\n\n", (pos != -1) ? "����" : "����");
    }
    printf("\n\n");

    qsort(array, ARRAY_SIZE, sizeof(array[0]), compare);
    printArray(array, ARRAY_SIZE);

    printf("[���� Ž��]\n");
    while(1)
    {
        printf("�Է� : ");
        scanf("%d", &value);

        if(value < 0 || value >= MAX_VALUE)
            break;

        pos = linearSearchSort(array, ARRAY_SIZE, value);
        printf("���� : %s\n\n", (pos != -1) ? "����" : "����");

        pos = binarySearch(array, ARRAY_SIZE, value);
        printf("���� : %s\n\n", (pos != -1) ? "����" : "����");
    }
}

void fillArray(int* array, int size)
{
    int i;
    for(i = 0; i < size; i++)
        array[i] = rand()%MAX_VALUE;
}

void printArray(const int* array, int size)
{
    int i;
    for(i = 0; i < size; i++)
    {
        printf("%3d ", array[i]);

        if(i%10 == 9)
            printf("\n");
    }
    printf("\n\n");
}

int compare(const void* p1, const void* p2)
{
    return *(int*) p1 - *(int*) p2;
}

/*
[��� ���]
 41 467 334 500 169 724 478 358 962 464
705 145 281 827 961 491 995 942 827 436
391 604 902 153 292 382 421 716 718 895
447 726 771 538 869 912 667 299  35 894
703 811 322 333 673 664 141 711 253 868
547 644 662 757  37 859 723 741 529 778
316  35 190 842 288 106  40 942 264 648
446 805 890 729 370 350   6 101 393 548
629 623  84 954 756 840 966 376 931 308
944 439 626 323 537 538 118  82 929 541


[���� Ž��]
�Է� : 264
Ƚ�� : ���� [68��]
��� : ����

�Է� : 555
Ƚ�� : ���� [100��]
��� : ����

�Է� : 999
Ƚ�� : ���� [100��]
��� : ����

�Է� : -1


  6  35  35  37  40  41  82  84 101 106
118 141 145 153 169 190 253 264 281 288
292 299 308 316 322 323 333 334 350 358
370 376 382 391 393 421 436 439 446 447
464 467 478 491 500 529 537 538 538 541
547 548 604 623 626 629 644 648 662 664
667 673 703 705 711 716 718 723 724 726
729 741 756 757 771 778 805 811 827 827
840 842 859 868 869 890 894 895 902 912
929 931 942 942 944 954 961 962 966 995


[���� Ž��]
�Է� : 264
Ƚ�� : ���� [17��]
���� : ����

Ƚ�� : ���� [3��]
���� : ����

�Է� : 555
Ƚ�� : ���� [52��]
���� : ����

Ƚ�� : ���� [7��]
���� : ����

�Է� : 999
Ƚ�� : ���� [100��]
���� : ����

Ƚ�� : ���� [7��]
���� : ����

�Է� : -1
*/