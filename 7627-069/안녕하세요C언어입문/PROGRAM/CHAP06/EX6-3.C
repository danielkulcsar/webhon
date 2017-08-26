double abs(double a) {
return(a >= 0 ? a : -a);
}

double sqrt(int n) {
double e=1.0e-10;
double root=n;
if (n < 0) 
  return(-1.0);
if (n == 0)
  return(0.0);
while (abs(n-root*root) > e)
  root = 0.5*(root+n/root);
return(root);
}

main() {
int i;
for (i=1;i<=10;i++)
  printf("The root of %d is %13.10lf\n",i,sqrt(i));
}
