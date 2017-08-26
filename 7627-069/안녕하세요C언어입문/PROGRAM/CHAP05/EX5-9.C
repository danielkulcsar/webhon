#include <stdio.h>
main() {
char c;
long int nc=0l;
int nl=0;
while ((c = getchar()) != EOF) {
  ++nc;
  if (c == '\n') ++nl;
 } /* while */
printf("%ld characters, %d lines.\n",nc,nl);
}
