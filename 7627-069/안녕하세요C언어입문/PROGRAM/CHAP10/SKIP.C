#include <stdio.h>
void skip_last() {
char c;
while ((c = getchar()) != EOF)
  if (c == '\n')
    return;
}
