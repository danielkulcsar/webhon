#include <stdio.h>
#define LSIZE 255
#define AUNIT 100
main(int argc, char *argv[]) {
char *cp;
char **cpp, **cpa, **cp1, **cp2, *t;
FILE *fp1, *fp2;
int n=1;
if (argc != 3) {
  fprintf(stderr,"Usage: %s file1 file2.\n",*argv);
  return(0);
 }
if ((fp1 = fopen(*++argv,"r")) == NULL) {
  fprintf(stderr,"Error: cannot open %s.\n",*argv);
  return(1);
 }
if ((fp2 = fopen(*++argv,"w")) == NULL) {
  fprintf(stderr,"Error: cannot create %s.\n",*argv);
  return(1);
 }
cp = (char *) malloc(LSIZE);
cpp = (char **) calloc(AUNIT,sizeof(char **));
cpa = cpp;
while (get_line(cp,fp1) != EOF) {
  *cpa = cp;
  if (++cpa == cpp+AUNIT*n) {
    if ((cpp = (char **) realloc(cpp,sizeof(char **)*AUNIT*++n)) == NULL) { 
      fprintf(stderr,"No memory.\n");
      return(-1);
     }
    cpa = cpp+AUNIT*(n-1);
   }
  if ((cp = (char *) malloc(LSIZE)) == NULL) {
      fprintf(stderr,"No memory.\n");
      return(-1);
     }
 }
for (cp1 = cpp;cp1 < cpa-1; ++cp1)
 for (cp2 = cp1+1;cp2 < cpa; ++cp2)
   if (strcmp(*cp1,*cp2) > 0) {
     t = *cp1;
     *cp1 = *cp2;
     *cp2 = t;
    }
for (cp1 = cpp;cp1 < cpa; ++cp1)
  fprintf(fp2,"%s\n",*cp1);
fclose(fp1);
fclose(fp2);
}
  
int get_line(char *s, FILE *fp) {
char *c = s;
while ((*s = getc(fp)) != EOF) 
  if (*s == '\n') {
    *s = '\0';
    return(1);
   }
  else
   ++s;
if (c == s) 
  return(EOF);
*s = '\0';
return(1);
}

