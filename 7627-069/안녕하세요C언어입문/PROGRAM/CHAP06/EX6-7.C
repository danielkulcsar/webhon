#include <math.h>
main() {
double a, b, c;
double d;
double x;
printf("Input a, b, c: ");
scanf("%lf%lf%lf",&a,&b,&c);
x = -b/(2.0*a);
d = b*b-4.0*a*c;
if (d == 0.0)
  printf("One root: %10.3lf\n",x);
else if (d > 0) {
  d = sqrt(d)/(2.0*a);
  printf("Two real roots: %10.3lf, %10.3lf\n",x+d,x-d);
 }
else {
  d = sqrt(-d)/(2.0*a);
  printf("Two imaginary roots: %10.3lf + %10.3lfi, %10.3lf + %10.3lfi\n",
    x,d,x,-d);
 }
}
