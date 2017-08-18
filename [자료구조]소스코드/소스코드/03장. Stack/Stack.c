#include "Stack.h"

enum { STACK_SIZE = 512 };

static char g_array[STACK_SIZE];
static int g_top = 0;

void push(char data)
{
    g_array[g_top++] = data;
}

char pop(void)
{
    return g_array[--g_top];
}

int isEmpty(void)
{
    return g_top == 0;
}
