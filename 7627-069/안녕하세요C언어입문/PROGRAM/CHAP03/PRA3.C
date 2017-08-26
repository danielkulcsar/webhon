main() {
char c='A';
float f = 0.0f;
double d = 1.2;
long double l = -10.2L;
int i = 10;
unsigned long int u = 200l;
scanf("%2d",&i);
scanf("%4f",&f);
scanf("%3le",&d);
scanf("%Lf",&l);
scanf("%c",&c);
scanf("%lx",&u);
printf("i = %d\n",i);
printf("f = %f\n",f);
printf("d = %lf\n",d);
printf("l = %Lf\n",l);
printf("c = %c\n",c);
printf("u = %lu\n",u);
}
