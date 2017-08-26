void main() {
int gcd=1;
int n, m;
int min;
register int i;
printf("Input n and m: ");
scanf("%d%d",&n,&m);
min = n > m ? m : n;
for (i=2;i<=min;++i)
 if (n % i == 0 && m % i == 0)
   gcd = i;
printf("GCD: %d\n",gcd);
printf("LCD: %ld\n",(long int)m*n/gcd);
}
