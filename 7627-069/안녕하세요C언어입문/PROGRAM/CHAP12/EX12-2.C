#include <stdio.h>
main(int argc, char *argv[]) {
FILE *fp;
char c;
if (argc == 1) {
  printf("Usage: %s file1 ... filen.\n",*argv);
  return(1);
 }
while (--argc > 0) {
 if ((fp = fopen(*++argv,"r")) == NULL) {
   printf("Error: cannot open %s.\n",*argv);
   continue;
  }
 printf("### %s ###\n",*argv);
 while ((c = getc(fp)) != EOF)
   putchar(c);
 }
}
