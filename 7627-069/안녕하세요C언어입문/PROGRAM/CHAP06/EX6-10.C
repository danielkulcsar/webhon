#include <stdio.h>
#include <math.h>

int status;
char echar;
double read_number() {
int base=10;
double res=0.0;
int sign=1;
int pract=0;
int pdigit=0;
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
  case '0':
  case '1':
  case '2':
  case '3':
  case '4':
  case '5':
  case '6':
  case '7':
  case '8':
  case '9':
  case '.': ungetc(c,stdin);
            break;
  case '+': goto again;
  case '-': sign *= -1;
            goto again;
  case 'q':
  case 'Q': status = 0;
            return(-1.0);
  default: status = -1;
           echar = c;
           return(-1.0);
 }
while (c = getchar())
  switch(c) {
    case 'a':
    case 'A': 
    case 'b':
    case 'B':
    case 'c':
    case 'C':
    case 'd':
    case 'D':
    case 'e':
    case 'E':
    case 'f':
    case 'F': if (base == 16) {
                if (c >= 'a')
                   res = res*base+c-'a'+10;
                else
                   res = res*base+c-'A'+10;
                if (pract)
                  ++pdigit;
                break; }
              else {
                status = -1;
                echar = c;
                return(-1.0);
               }
    case '.': if (pract) {
                status = -1;
                echar = c;
                return(-1.0);
              }
              pract = 1;
              break;
    case '0':
    case '1':
    case '2':
    case '3':
    case '4':
    case '5':
    case '6':
    case '7':
    case '8':
    case '9': if (c-'0' >= base) {
                status = -1;
                echar = c;
                return(-1.0);
               }
              res = res*base+c-'0';
              if (pract)
                ++pdigit;
              break;
   default: ungetc(c,stdin);
            if (pract) {
               status = 1;
               return(sign*res/pow((double)base,(double)pdigit));
              }
            else {
               status = 2;
               return(sign*res);
             }
 }
}

void skip_last() {
while (getchar() != '\n');
}

void print_error(int i) {
switch(i) {
  case 1: printf("Error: Illegal character \'%c\' in the first operand.\n",
                 echar);
          break;
  case 2: printf("Error: Illegal character \'%c\' in the second operand.\n",
                 echar);
          break;
  case 3: printf("Error: Unknown operator \'%c\'.\n",echar);
          break;
  case 4: printf("Error: %% opertor should be used with integers.\n");
 }
}

void print_res(int t,double r) {
if (t == 4)
   printf("%ld\n",(long int)r);
else
   printf("%lf\n",r);
}

main() {
double x;
double y;
char op;
int atype;
while (1) {
  printf("--> ");
  x = read_number();
  if (!status)
    break;
  if (status == -1) {
    print_error(1);
    skip_last();
    continue;
   }
  atype = status;
  while ((op = getchar()) == ' ' || op == '\t');
  y = read_number();
  if (status == -1) {
    print_error(2);
    skip_last();
    continue;
   }
  atype += status;
  switch(op) {
    case '+': print_res(atype,x+y); /* x + y */
              break;
    case '-': print_res(atype,x-y); /* x - y */
              break;
    case '*': print_res(atype,x*y); /* x * y */
              break;
    case '/': print_res(atype,x/y); /* x / y */
              break;
    case '%': if (atype != 4) { /* x % y */
                print_error(4);
                skip_last();
                continue;
                }
              print_res(atype,(double)((long int)x % (long int)y));
              break;
    case '^': print_res(atype,pow(x,y)); /* x ^ y */
              break;
    default: echar = op;
             print_error(3);
             skip_last();
             continue;
   }
 skip_last();
 }
}
