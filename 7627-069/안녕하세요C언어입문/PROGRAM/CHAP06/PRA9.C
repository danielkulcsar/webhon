int i=10;
int j=20;
main() {
int i=5;
i += ++j;
printf("i = %d, j = %d\n",i,j);
j -= --i;
{ int j=3;
  i *= i+j;
  printf("i = %d, j = %d\n",i--,j--);
}
printf("i = %d, j = %d\n",i,j);
i = f1(i,j);
printf("i = %d, j = %d\n",i,j);
}

f1(int x, int y)
{
 return(++i * ++j - ++x - ++y);
}
