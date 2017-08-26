#include <stdio.h>
#include "cal.h"
#define SIGN(x) ((x) == '+' || (x) == '-')
#define WHITE(x) ((x) == ' ' || (x) == '\t')
#define DIGIT(x) ((x) >= '0' && (x) <= '9')
#define defined_var(x) ((x) >= 'A' && (x) <= 'Z')
#define defined_op(x) ((x) == '+' || (x) == '-' || (x) == '*' || (x) == '/' ||\
                       (x) == '%' || (x) == '^')
void read_token(struct token *t,int opflag) {
char c;
int sign = 1;
long int res=0l;
c = getchar();
while (WHITE(c))
  c = getchar();
if (c == '\n') {
  t->token_type = NOINPUT;
  return;
 }
if (c == '=') {
  t->token_type = EQUAL;
  return;
 }
if (defined_var(c)) {
  t->token_type = VAR;
  t->token_val.var = c - 'A';
  return;
 }
if (opflag == TRUE && defined_op(c)) {
  t->token_type = OP;
  t->token_val.op = c;
  return;
 }
if (SIGN(c) || WHITE(c)) {
  if (c == '-')
    sign *= -1;
  c = getchar();
 }
if (DIGIT(c)) {
  do {
    res = res*10 + c - '0';
    c = getchar();
   } while (DIGIT(c));
  ungetc(c,stdin);
  t->token_type = INTEGER;
  t->token_val.ival = res*sign;
  return;
}
if (c == EOF) {
  t->token_type = EOF;
  return;
 }
ungetc(c,stdin);
t->token_type = UNKNOWN;
}
