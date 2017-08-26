#include <stdio.h>
#define REVERSE -1
#define NORMAL 1
#define MAXLINENO 100
#define MAXCNO 80
#define TRUE EOF*-1
main(int argc, char *argv[]) {
static char text[MAXLINENO][MAXCNO];
char t[MAXCNO];
FILE *fp1, *fp2;
int direction=NORMAL;
int no=0;
int i, j;
 if ((argc != 3 && argc != 4) || (argc == 4 && strcmp(*(argv+1),"-r"))) {
  fprintf(stderr,"Usage: %s [ -r ] file1 file2.\n",*argv);
  return(1);
 }
if (argc == 4) {
  direction = REVERSE;
  ++argv;
 }
if ((fp1 = fopen(*++argv,"r")) == NULL) {
  fprintf(stderr,"Error: cannot open %s.\n",*argv);
  return(2);
 }
if ((fp2 = fopen(*++argv,"w")) == NULL) {
  fprintf(stderr,"Error: cannot create %s.\n",*argv);
  return(3);
 }
while (get_line(fp1,text[no++]) != EOF);
for (i=0;i<no-2;++i)
 for (j=i+1;j<no-1;++j)
  if (strcmp(text[i],text[j]) * direction > 0) {
    strcpy(t,text[i]);
    strcpy(text[i],text[j]);
    strcpy(text[j],t);
   }
for (i=0;i<no-1;++i)
  fprintf(fp2,"%s\n",text[i]);
}
 
int get_line(FILE *fp, char *s) {
char *cp = s;
while ((*s = getc(fp)) != EOF)
  if (*s == '\n') {
    *s = '\0';
    return(TRUE);
   }
  else
    ++s;
if (cp == s) 
  return(EOF);
*s = '\0';
return(TRUE);
}
