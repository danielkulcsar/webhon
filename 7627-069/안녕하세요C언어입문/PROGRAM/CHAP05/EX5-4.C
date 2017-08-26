main() {
int i=2, j=4;
if (i*j > i+j)
  i += j -= 3;
else
  i *= j /= 3;
if (i == j);
  ++i;
if (++i >= ++j*3)
  j += 2;
else ;
  --i;
if (i > 0)
  if (i == 2*j-1)
     i += j;
else
   i -= j;
printf("i = %d, j = %d\n",i,j);
}
