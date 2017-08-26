#include <stdio.h>
#include <string.h>
main(int argc, char *argv[]) {
char data[80];
char c;
int n=0;
int sl1;
int sl2;
int nget_line(char *,int);
int compstr(char *, char *);
if (argc != 3) {
  printf("Usage: %s string1 string2\n",*argv);
  return(1);
 }
sl1 = strlen(argv[1]);
if (sl1 > 80) {
  printf("%s is too long(exceeding 80 characters).\n",argv[1]);
  return(2);
 }
sl2 = strlen(argv[2]);
data[sl1-1] = '\0';
while ((c = getchar()) != EOF) {
  if (c == *argv[1]) {
    if (sl1-1)
      n = nget_line(data,sl1-1);
    if (n == sl1-1 && !strcmp(data,argv[1]+1))
      printf("%s",argv[2]);
    else
      printf("%c%s",*argv[1],data);
   }
  else
    putchar(c);
 }
}

int nget_line(char *c,int n) {
int i=0;
while ((*c++ = getchar()) != EOF)
  if (++i == n)
    break;
return(i);
}
