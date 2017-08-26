int i=1;
int j=2;
void main() {
int i=3;
int k=4;
i += j+i;
k *= i-j;
printf("i = %d, j = %d\n",i,j);
{ int i=5, k=6;
  i = ++j;
  k = ++i;
  printf("i = %d, j = %d\n",i,j);
 }
i = f1(j);
j = f1(k);
printf("i = %d, j = %d\n",i,j);
}

f1(int x) {
return(x + ++i - j--);
}
