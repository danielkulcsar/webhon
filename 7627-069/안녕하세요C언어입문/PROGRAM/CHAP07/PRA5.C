int i=10;
int *ip = &i;
main() {
int i=5;
i += ++*ip;
printf("i = %d, *ip = %d\n",i,*ip);
i = f1(&i,&ip);
printf("i = %d, *ip = %d\n",i,*ip);
}

f1(int *x, int **xp) {
++*x;
++**xp;
*xp = x;
return(**xp + *x);
}
