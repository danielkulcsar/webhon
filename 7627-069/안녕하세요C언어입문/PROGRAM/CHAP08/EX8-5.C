#include <stdio.h>
void main() {
char data[5];
char c;
char *cp = "orea";
int n;
int nget_line(char *,int); 
int compstr(char *, char *);
data[4] = '\0';
while ((c = getchar()) != EOF) {
  if (c == 'K') {
    n = nget_line(data,4);
    if (n == 4 && compstr(data,cp))
      printf("Republic of Korea");
    else
      printf("K%s",data);
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

int compstr(char *s1, char *s2) {
while (*s1 == *s2) {
  if (*s1 == '\0')
    return(1);
  ++s1;
  ++s2;
 }
return(0);
}
