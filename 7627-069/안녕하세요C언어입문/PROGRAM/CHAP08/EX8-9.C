#include <stdio.h>
void main() {
static char l[3000];
static char *cp[400];
char **cpp = cp;
char **cp1, **cp2;
char *t;
int n=0;
int len;
while (len = get_line(*cpp++ = l+n))
 n += len; 
for (cp1 = cp;cp1 < cpp-1;++cp1)
  for (cp2 = cp1+1;cp2 < cpp;++cp2)
    if (strcmp(*cp1,*cp2) > 0) {
      t = *cp2;
      *cp2 = *cp1;
      *cp1 = t;
     }
for (cp1 = cp;cp1 < cpp; ++cp1)
  printf("%s",*cp1);
}

int get_line(char *l) {
char c;
int i=0;
while ((*l = getchar()) != EOF)
  if (++i && *l++ == '\n') {
    *l = '\0';
    return(i+1);
   }
if (i) {
    *l++ = '\n';
    *l = '\0';
    return(i+2);
 }
return(0);
}
