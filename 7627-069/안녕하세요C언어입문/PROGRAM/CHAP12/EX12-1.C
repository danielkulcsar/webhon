#include <stdio.h>
main() {
FILE *fp;
int i;
int no = 0;
long int sum = 0l;
if ((fp = fopen("data.dat","r")) == NULL) {
  printf("Error: cannot open data.dat.\n");
  return(1);
 }
while (fscanf(fp,"%d",&i) != EOF) {
  sum += i;
  ++no;
 }
if (no == 0) 
  printf("No data.\n");
else {
  printf("Total %d numbers.\n\n",no);
  printf("Sum = %ld\nAverage = %.1f\n",sum,(float)sum/(float)no);
 }
}
