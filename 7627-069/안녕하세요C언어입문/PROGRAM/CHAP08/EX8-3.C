void main() {
static int ia[] = {2,5,7,9,11};
static char ca[] = "abcdef";
int *ip, *jp;
int t;
char *cp;
ip = ia;
while (ip <= ia+4)
 ++*ip++;
ip = ia;
jp = ip + 4;
while (ip < jp) {
  t = *ip;
  *ip++ = *jp;
  *jp-- = t;
 }
for (ip = ia; ip <= ia+4; ++ip) 
  printf("%d ",*ip);
putchar('\n');
cp = ca;
while (*cp)
 putchar(*cp++);
putchar('\n');
cp = ca;
while (*cp++)
 putchar(++*cp++);
putchar('\n');
for (cp = ca; *cp;) 
  putchar(*cp++);
putchar('\n');
}
