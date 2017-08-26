main() {
int number;
int divisor;
int N;
int count=0;
printf("Input limit: ");
scanf("%d",&N);
if (N <= 1)
   printf("You should input number >= 2.\n");
else
   for (number=2;number <= N; number++) {
     for (divisor=2;number % divisor != 0; divisor++);
     if (divisor == number) {
        printf("%7d ",number);
        if (++count % 10 == 0)
            printf("\n");
       } /* if */
  } /* for */
} /* main */
