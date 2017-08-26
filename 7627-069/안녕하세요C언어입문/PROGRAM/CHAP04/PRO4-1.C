#define N 10000000l
#include <stdio.h>
#include <dos.h>
main() {
 struct dostime_t t1, t2;
 long int i; 
 int x=0;
 printf("** ++x **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=N;i++)
  ++x;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 x=0;
 printf("** x++ **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=N;i++)
  x++;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 x=0;
 printf("** x+=1 **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=N;i++)
  x += 1;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 x=0;
 printf("** x = x+1 **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 for (i=1l;i<=N;i++)
  x = x+1;
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
}
