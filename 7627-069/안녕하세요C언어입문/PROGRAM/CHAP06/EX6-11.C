long int rand1(long int R) {
static long int seed = 17l;
seed = (25173*seed+13849) % 65536;
return(seed % R);
}

void main() {
int i;
for (i=1;i<=10;i++)
  printf("%ld\n",rand1((long int)10));
}
