#include <stdio.h>
#include <math.h>
long int read_number() {
int base=10;
long int res=0l;
int sign=1;
char c;
again: while ((c = getchar()) == ' ' || c == '\t');
switch(c) {
  case 'd': break;
  case 'b': base = 2;
            break;
  case 'o': base = 8;
            break;
  case 'x': base = 16;
            break;
  case '+': goto again;
  case '-': sign *= -1;
            goto again;
  default: ungetc(c,stdin);
 }
while ((c = getchar()) && (((base != 16) && (c >= '0') && (c-'0' <= base-1)) ||
      ((base == 16) && (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') ||
       (c >= 'A' && c <= 'F'))))
  switch(c) {
    case 'a':
    case 'A': res = res*base+10l;
              break;
    case 'b':
    case 'B': res = res*base+11l;
              break;
    case 'c':
    case 'C': res = res*base+12l;
              break;
    case 'd':
    case 'D': res = res*base+13l;
              break;
    case 'e':
    case 'E': res = res*base+14l;
              break;
    case 'f':
    case 'F': res = res*base+15l;
              break;
    default: res = res*base+c-'0';
   }
ungetc(c,stdin);
return(res*sign);
}

void skip_last() {
while (getchar() != '\n');
}

main() {
long int x;
long int y;
char op;
while (1) {
  printf("--> ");
  x = read_number();
  while ((op = getchar()) == ' ' || op == '\t');
 if (op == 'q' || op == 'Q')
    break;
  y = read_number();
  switch(op) {
    case '+': printf("%ld\n",x+y); /* x + y */
              break;
    case '-': printf("%ld\n",x-y); /* x - y */
              break;
    case '*': printf("%ld\n",x*y); /* x * y */
              break;
    case '/': printf("%ld\n",x/y); /* x / y */
              break;
    case '%': printf("%ld\n",x%y); /* x % y */
              break;
    case '^': printf("%ld\n",(long int)pow((double)x,(double)y)); /* x ^ y */
              break;
    default: printf("Error: Unknown Operator %c\n",op);
   }
 skip_last();
 }
}
