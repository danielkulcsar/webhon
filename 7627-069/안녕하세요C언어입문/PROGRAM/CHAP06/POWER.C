/* power.c */
double power(double d, double e) {
double i;
double res = 1.0;
for (i=1.0;i <= e; ++i)
 res *= d;
return(res);
}
