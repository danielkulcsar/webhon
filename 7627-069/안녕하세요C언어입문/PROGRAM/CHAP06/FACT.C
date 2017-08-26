/* fact.c */
double fact(double d) {
double i;
double res = 1.0;
for (i=1.0;i <= d;++i)
 res *= i;
return(res);
}
