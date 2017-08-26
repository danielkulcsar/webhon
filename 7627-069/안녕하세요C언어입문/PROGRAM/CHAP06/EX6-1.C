long int fact(int n) {
int i;
long int res=1l;
if (n < 0)
  return(-1l);
else if (n == 0) /* 0! = 1*/
  return(1);
else {
  for (i=1;i<=n;i++) /* N! = 1*2*...*N */
    res *= i;
  return(res); } /* else */
} /* fact */
