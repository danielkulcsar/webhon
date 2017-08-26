#include <stdio.h>
read_number(long int *res) {
int base=10;
int sign=1;
char c;
void skip_last(void);
*res = 0l;
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
  case EOF: return(EOF);
  default: ungetc(c,stdin);
 }
while ((c = getchar()) != EOF && (((base != 16) && (c  >=  '0')  &&  
  (c-'0'  <= base-1)) || ((base == 16) && ((c >= '0' && c <= '9') || 
  (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')))))
  switch(c) {
    case 'a':
    case 'A': *res = *res * base+10l;
              break;
    case 'b':
    case 'B': *res = *res * base+11l;
              break;
    case 'c':
    case 'C': *res = *res * base+12l;
              break;
    case 'd':
    case 'D': *res = *res * base+13l;
              break;
    case 'e':
    case 'E': *res = *res * base+14l;
              break;
    case 'f':
    case 'F': *res = *res * base+15l;
              break;
    default: *res = *res * base+c-'0';
   } /* switch */
if (c == EOF)
   return(EOF);
if (c == ' ' || c == '\t')
   skip_last();
if (c == '\n' || c == ' ' || c == '\t') {
   *res *= sign;
   return(1);
  }
return(0);
}

void skip_last() {
while (getchar() != '\n');
}

main() {
long int i;
long int sum = 0;
int status;
while ((status = read_number(&i)) != EOF) {
  if (status == 0) {
    printf("Input error!!\n");
    return(1);
   }
  sum += i;
 }
printf("The sum is %ld.\n",sum);
}
