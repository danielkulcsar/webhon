/* main.c */
void main() {
extern double fact(double x);
extern double power(double x, double y);
int N; 
int i;
double sum = 0.0;
printf("Input N: ");
scanf("%d",&N);
for (i=1;i<=N;i++)
  sum += fact((double)i) / power((double)i, (double)i);
printf("sum = %lf\n",sum);
}
