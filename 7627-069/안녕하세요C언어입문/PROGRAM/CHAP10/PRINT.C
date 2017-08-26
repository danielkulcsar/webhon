#include "err.h"
void print_error(int x) {
switch(x) {
  case HELP: printf("You can use the following formulas:\n");
    printf("\tX\n");
    printf("\tX = Y\n");
    printf("\tM = X op Y\n");
    printf("where X,Y: a variable or an integer\n");
    printf("      M  : a variable.\n");
    break;
  case XORXOPYEQ: printf("Error: X or X op Y should be used after \'=\'.\n");
    break;
  case TRAIL: printf("Error: Unexpected input in the last part.\n");
    break;
  case ROPERROR: printf("Error: An integer or a variable should be  used  after the operator.\n");
    break;
  case OPEXPECTED: printf("Error: On operator is expected but not used.\n");
    break;
  case MORMEQXORXOPY: printf("Error: M or M = X  or  M  =  X  op  Y  should  be used.\n");
 }
}
