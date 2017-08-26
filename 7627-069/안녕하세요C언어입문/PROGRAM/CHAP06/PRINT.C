/* print.c */
print_bit(unsigned long int x) {
int i;
for (i=1;i<=32;i++) {
  printf("%1d",(x & 0x80000000ul) >> 31);
  x <<= 1;
 }
putchar('\n');
}
