#define N 10000000l
#include <stdio.h>
#include <dos.h>
main() {
 struct dostime_t t1, t2;
 long int i; 
 char c;
 printf("** switch **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 c = 'A';
 for (i=1l;i<=N;i++)
 switch(c) {
 int x=10, y=20;
 case 'A':
 case 'B': x += y;
/*           printf("%d\n",x); */
      c = 'C';
           break;
 case 'C': y -= x;
 case 'D':/* printf("%d\n",y); */
           c = 'E';
           break;
 case 'E': 
 case 'F':
 case 'G': c = 'H';
           break;
 default:  x += y;
           y += x;
      c = 'A';
/*           printf("%d\n",x+y); */
 }
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
 printf("** if **\n");
 _dos_gettime(&t1);
 printf("  start --> %d %d.%d\n",t1.minute,t1.second,t1.hsecond);
 c = 'A';
 for (i=1l;i<=N;i++)
 { int x=10, y=20;
   if (c == 'A' || c == 'B') 
    { x += y;
  /*    printf("%d\n",x); */ c='C';}
   else if (c == 'C' || c == 'D')
    { if (c == 'C')
        y -= x;
 /*     printf("%d\n",y); */ c='E';}
   else if (c == 'E' || c == 'F' || c == 'G') c='H';
   else 
    { x += y;
      y += x;
/*      printf("%d\n",x+y); */ c='A';}
  }
 _dos_gettime(&t2);
 printf("  end   --> %d %d.%d\n",t2.minute,t2.second,t2.hsecond);
 i = t2.minute*60*100 + t2.second * 100 + t2.hsecond - 
     t1.minute*60*100 - t1.second*100 - t1.hsecond;
 printf("  time = %ld milisecs\n",i); 
}
