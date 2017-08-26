int i=10;
int *ip = &i;
main() {
int i=2, j=3;
j = ++*ip + ++i - j;
printf("i = %d, j = %d, *ip = %d\n",i,j,*ip);
j = f1() + i++;
printf("i = %d, j = %d, *ip = %d\n",i,j,*ip);
}

f1() {
int j = 5;
int *ip = &j;
j = *ip - i++;
*ip = ++*ip * i;
return(*ip+j+i);
}
