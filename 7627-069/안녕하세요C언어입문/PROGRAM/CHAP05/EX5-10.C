main() {
long int res=0;
char c;
c = getchar();
while (c == '0' || c == '1') {
 res = res*2l + c-'0';
 c = getchar(); }
printf("%ld, %lo, %lx\n",res,res,res);
}
