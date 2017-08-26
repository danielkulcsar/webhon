main() {
int i=10, *ip = &i;
int j, k;
char c='D', *cp = &c;
double *jp = (double *)2000;
*ip++ = ++i;
++*--ip;
++*cp++;
*--cp = c+3;
jp += 5;
j = jp - (double *)80;
k = (int)(jp - 80);
printf("i = %d, j = %d, k = %d, c = %c\n",i,j,k,c);
}
