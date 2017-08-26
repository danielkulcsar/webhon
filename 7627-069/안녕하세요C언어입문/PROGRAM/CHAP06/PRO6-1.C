#include <stdio.h>
#include <dos.h>
#define L 30000
#define L2 1000
main() {
 struct dostime_t t1, t2;
 register int r;
 int a;
 int k;
 long int i; 
 printf("** register **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (k=1;k<=L2;k++)
 for (r=1;r<=L;r++);
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** auto **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (k=1;k<=L2;k++)
 for (a=1;a<=L;a++);
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
}
