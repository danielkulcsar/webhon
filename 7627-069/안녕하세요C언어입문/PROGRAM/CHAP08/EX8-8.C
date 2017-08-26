#include <stdio.h>
void main() {
static int A[10][10];
static int B[10][10];
static int C[10][10];
int N;
int i, j;
void print_matrix(int (*)[10],int);
int matrix_mul(int *, int *, int);
printf("Input N: ");
scanf("%d",&N);
printf("Input matrix A(%-d*%-d): ",N,N);
for (i=0;i<N;++i)
  for (j=0;j<N;++j)
    scanf("%d",&A[i][j]);
printf("** A **\n");
print_matrix(A,N);
printf("Input matrix B(%-d*%-d): ",N,N);
for (i=0;i<N;++i)
  for (j=0;j<N;++j)
    scanf("%d",&B[i][j]);
printf("** B **\n");
print_matrix(B,N);
for (i=0;i<N;++i)
  for (j=0;j<N;++j)
    C[i][j] = matrix_mul(A[i],*B+j,N);
printf("** C = A*B **\n");
print_matrix(C,N); 
}

void print_matrix(int (*m)[10],int N) {
int i, j;
for (i=0;i<N;++i) {
  for (j=0;j<N;++j)
    printf("%7d ",m[i][j]);
  putchar('\n');
 }
}

int matrix_mul(int *A, int *B, int N) {
int res = 0;
while (N-- > 0)
  res += *(A+N) * *(B+N*10);
return(res);
}
