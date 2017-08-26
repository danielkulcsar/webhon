main() {
int i=10, j=2;
i += j++;
printf("i = %d, j = %d\n",i,j);
{ int i=2;
  i += j;
  j -= i;
  printf("i = %d, j = %d\n",i,j);
  { int j=5;
    j *= i+1;
    ++i;
    printf("i = %d, j = %d\n",i,j);
  }
  printf("i = %d, j = %d\n",i,j);
}
printf("i = %d, j = %d\n",i,j);
}
