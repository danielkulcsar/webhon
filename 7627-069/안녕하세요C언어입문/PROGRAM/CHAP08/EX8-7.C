#include <stdio.h>
#include <string.h>
void main() {
static char word[100][30];
char (*cp1)[30] = word;
char (*cp2)[30];
char t[30];
int n=0;
int get_word(char *);
while (get_word(*cp1++) && ++n < 100);
for (cp1 = word;cp1 < word+n-1; ++cp1)
  for (cp2 = cp1+1;cp2 < word+n; ++cp2)
    if (strcmp(*cp1,*cp2) > 0) {
      strcpy(t,*cp1);
      strcpy(*cp1,*cp2);
      strcpy(*cp2,t);
    }
for (cp1 = word; cp1 < word+n; ++cp1)
  printf("%s\n",*cp1);
}

int get_word(char *s) {
int l = 0;
while ((*s = getchar()) != EOF) {
  if (!l && (*s == ' ' || *s == '\t' || *s == '\n'))
    continue;
  if (l == 29 || *s == ' ' || *s == '\t' || *s == '\n') {
    if (l == 29)
      ungetc(*s,stdin);
    *s = '\0';
    return(1);
   }
  ++s;
  ++l;
 }
if (l) {
  *s = '\0';
  return(1);
 }
else
 return(0);
}
