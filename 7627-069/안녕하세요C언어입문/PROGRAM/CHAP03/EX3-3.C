main() {
float f=1.789f;
double g=32.789345678;
long double a=12.345e-100l;
printf("f = %f, g = %lf, a = %Lf\n",f,g,a);
printf("f = %e, g = %le, a = %Le\n",f,g,a);
printf("f+12.3 = %f, g/3.42 = %lf, a*e100 = %Lf\n",
        f+12.3f,g/3.42,a*1.0e100l);
}
