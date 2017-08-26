#include <stdio.h>
#include <dos.h>
main() {
 struct dostime_t t1, t2;
 char c1=1, c2=2, c3; 
 int i1=1, i2=2, i3; 
 long int l1=1l, l2=2l, l3; 
 float f1=1.0f, f2=2.0f, f3; 
 double d1=1.0, d2=2.0, d3; 
 long double ld1=1.0l, ld2=2l, ld3; 
 long int i; 
 printf("** char **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=1000000l;i++)
   c3 = c1 +  c2;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** int **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=1000000l;i++)
   i3 = i1 +  i2;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** long int **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=1000000l;i++)
   l3 = l1 +  l2;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** float **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=1000000l;i++)
   f3 = f1 +  f2;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** double **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=1000000l;i++)
   d3 = d1 +  d2;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** long double **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=1000000l;i++)
   ld3 = ld1 +  ld2;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
}
