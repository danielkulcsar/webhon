#include <stdio.h>
char data[255];
void main() {
char mdata[255];
int get_line(void);
int max = 0;
int l;
int i;
while (l = get_line()) {
 if (l > max)  {
   for (i=0;i<=l-1;++i)
     mdata[i] = data[i];
   max = l;
  }
}
if (max == 0)
  printf("No data.\n");
else {
  printf("The longest line is:\n");
  for (i=0;i<=max-1;++i)
    putchar(mdata[i]);
  printf("\nThe length is: %d\n",max);
 }
}

int get_line() {
int i=0;
while ((data[i] = getchar()) != EOF)
 if (data[i++] == '\n')
    return(i);
return(i);
}
