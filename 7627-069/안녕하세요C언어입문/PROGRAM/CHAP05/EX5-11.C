main() {
int n;
int i;
float sum;
float max;
float min;
float data;
printf("Input number of data: ");
scanf("%d",&n);
if (n == 0)
   printf("No data\n");
else if (n < 0)
   printf("Error: Negative Data Number\n");
else {
   scanf("%f",&data);
   sum = max = min = data;
   for (i=2;i <= n; i++) {
      scanf("%f",&data);
      if (data > max)
         max = data;
      if (data < min)
         min = data;
      sum += data;
   } /* for */
   printf("The maximum is %f\n",max);
   printf("The minimum is %f\n",min);
   printf("The sum is %f\n",sum);
   printf("The average is %f\n",sum/n);
 } /* else */
} /* main */
