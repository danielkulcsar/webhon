#include <stdio.h>
void main() {
char c;
int ifno = 0;
int whono = 0;
int whileno = 0;
int dono = 0;
int theno = 0;
int textno = 0;
int treeno = 0;
int status = 0;
while ((c = getchar()) != EOF) {
 switch(c) {
  case 'i': if (status == 0)
              status = 1;
            else if (status == 4)
                   status = 6;
            else {
              ungetc(c,stdin);
              status = 0;
             }
            break;
   case 'f': if (status == 1)
             ++ifno;
             status = 0;
             break;
   case 'w': if (status == 0)
               status = 3;
             else {
               ungetc(c,stdin);
               status = 0;
              }
             break;
   case 'd': if (status == 0)
               status = 9;
             else {
               ungetc(c,stdin);
               status = 0;
              }
             break;
   case 't': if (status == 0)
               status = 11;
             else if (status == 15) {
               ++textno;
               status = 0;
              }
             else {
               ungetc(c,stdin);
               status = 0;
              }
             break;
   case 'h': if (status == 3)
               status = 4;
             else if (status == 11)
               status = 12;
             else
               status = 0;
             break;
   case 'o': if (status == 9)
               ++dono;
             else if (status == 4)
               ++whono;
             status = 0;
             break;
   case 'l': if (status == 6)
               status = 7;
             else
               status = 0;
             break;
   case 'r': if (status == 11)
               status = 17;
             else
               status = 0;
             break;
   case 'x': if (status == 14)
               status = 15;
             else
               status = 0;
             break;
   case 'e': if (status == 7) {
               ++whileno;
               status = 0;
              }
             else if (status == 12) {
               ++theno;
               status = 0;
              }
             else if (status == 11)
               status = 14;
             else if (status == 14)
               status = 15;
             else if (status == 17)
               status = 18;
             else if (status == 18) {
               ++treeno;
               status = 0;
              }
             else
               status = 0;
             break;
    default: status = 0;
    }
  }
 printf("Number of if: %d\n",ifno);
 printf("Number of who: %d\n",whono);
 printf("Number of while: %d\n",whileno);
 printf("Number of do: %d\n",dono);
 printf("Number of the: %d\n",theno);
 printf("Number of text: %d\n",textno);
 printf("Number of tree: %d\n",treeno);
 }
