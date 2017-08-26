#include <math.h>
long int calc(long int l,char op,long int r) {
switch (op) {
  case '+': return(l+r);
  case '-': return(l-r);
  case '*': return(l*r);
  case '/': return(l/r);
  case '%': return(l % r);
  case '^': return((long int) pow((double)l,(double)r));
 }
}
