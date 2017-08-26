void main() {
static int data[100];
int i, j;
int t;
int N;
printf("Input the number of data: ");
scanf("%d",&N);
for (i=0;i<=N-1;++i)
  scanf("%d",&data[i]);
for (i=0;i<=N-2;++i)
  for (j=i+1;j<=N-1;++j)
    if (data[i] > data[j]) {
      t = data[i];
      data[i] = data[j];
      data[j] = t; }
printf("Sorted data:\n");
for (i=0;i<=N-1;++i) {
  printf("%10d",data[i]);
  if ((i+1) % 7 == 0)
    printf("\n");
  }
}
