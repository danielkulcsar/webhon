main() {
int i=10, j=2;
short int s=12;
long int l=10000L;
unsigned u=32000u;
printf("i = %5d\nj = %5d\ns = %5hd\nl = %5ld\nu = %5u\n",i,j,s,l,u);
printf("i + 1 = %d, j-3 = %d, s*20 = %hd, l-5000*2 = %ld, u-27000 = %u\n",
       i+1,j-3,s*20,l-5000L*2L,u-27000u);
}
