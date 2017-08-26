#include <stdio.h>
main(int argc, char *argv[]) {
FILE *fp1, *fp2;
char c;
if (argc != 3) {
  fprintf(stderr,"Usage: %s file1 file2.\n",*argv);
  return(1);
 }
if ((fp1 = fopen(*++argv,"r")) == NULL) {
  fprintf(stderr,"Error: cannot open %s.\n",*argv);
  return(2);
 }
if ((fp2 = fopen(*++argv,"w")) == NULL) {
  fprintf(stderr,"Error: cannot create %s.\n",*argv);
  return(3);
 }
while ((c = getc(fp1)) != EOF)
   putc(c,fp2);
}
