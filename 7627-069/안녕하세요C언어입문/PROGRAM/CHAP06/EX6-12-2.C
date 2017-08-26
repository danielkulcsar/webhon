void main() {
int gcd;
int max;
int n, m;
int t;
printf("Input n and m: ");
scanf("%d%d",&n,&m);
gcd = n > m ? m : n;
max = n > m ? n : m;
while (max % gcd != 0) {
 t = max;
 max = gcd;
 gcd = t % gcd;
}
printf("GCD: %d\n",gcd);
printf("LCM: %ld\n",(long int)m*n/gcd);
}
