main() {
int i=10, j=5;
float f;
i += j-2;
f = (i > 10 ? ++i : --j);
f = i*f - (int)(3.14*j);
printf("i = %d, j = %d, f = %f\n",i,j,f);
}
