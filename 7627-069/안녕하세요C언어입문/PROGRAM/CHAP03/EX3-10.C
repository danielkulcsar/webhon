main() {
float f1, f2;
double d;
long double l;
printf("Input f1, f2, d, l: ");
scanf("%3f%10f%5lf%3Le",&f1,&f2,&d,&l);
printf("f1 = %20.10f\n",f1);
printf("f2 = %20.10f\n",f2);
printf("d  = %20.10lf\n",d);
printf("l  = %20.10Lf\n",l);
}
