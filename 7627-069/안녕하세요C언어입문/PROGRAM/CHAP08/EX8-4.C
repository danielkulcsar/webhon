#include <stdio.h>
void main() {
char line[255];
char *cp;
int l;
int get_line(char *);
void reverse(char *,int);
while (l = get_line(line)) {
 reverse(line,l);
 for (cp = line;cp < line+l;++cp)
   putchar(*cp);
 }
}

int get_line(char l[]) {
int len=0;
while ((*l = getchar()) != EOF) {
  ++len;
  if (*l++ == '\n')
    return(len);
 }
*l = '\n';
return(len);
}

void reverse(char *line, int len) {
char *cp = line+len-2;
int t;
while (line < cp) {
  t = *line;
  *line++ = *cp;
  *cp-- = t; 
 }
}
