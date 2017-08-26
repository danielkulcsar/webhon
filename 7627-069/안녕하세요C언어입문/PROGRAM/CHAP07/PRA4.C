main() {
static unsigned long int l1 = 0x61626364ul;
static unsigned long int l2 = 0x65666768ul;
static unsigned long int l3 = 0x696a6b6cul;
char *cp = (char *) &l2;
int i;
for (i=0;i<4;++i)
 printf("%c ",*cp++);
printf("\n");
}
