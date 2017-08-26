#include <stdio.h>
#include "cal.h"
#include "err.h"
long int var[26] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
void main() {
struct token t1, t2, t3, t4, t5, t6;
long int left, right;
void read_token(struct token *,int);
long int calc(long int,char,long int);
void print_error(int);
void skip_last(void);
while (TRUE) {
  printf(">> ");
  read_token(&t1,FALSE);
  switch (t1.token_type) {
    case VAR:
      read_token(&t2,TRUE);
      switch (t2.token_type) {
        case EQUAL: read_token(&t3,FALSE);
          if (t3.token_type == VAR || t3.token_type == INTEGER) {
            read_token(&t4,TRUE);
            if (t4.token_type == NOINPUT) {
              if (t3.token_type == VAR)
                var[t1.token_val.var] = var[t3.token_val.var];
              else
                var[t1.token_val.var] = t3.token_val.ival;
              printf("ok.\n");
              continue;
             }
            else if (t4.token_type == OP) {
              read_token(&t5,FALSE);
              if (t5.token_type == VAR || t5.token_type == INTEGER) {
              read_token(&t6,FALSE);
                if (t6.token_type == NOINPUT) {
                  if (t3.token_type == VAR) 
                    left = var[t3.token_val.var];
                  else
                    left = t3.token_val.ival;
                  if (t5.token_type == VAR)
                    right = var[t5.token_val.var];
                  else
                    right = t5.token_val.ival;
                  var[t1.token_val.var] = calc(left,t4.token_val.op,right);
                  printf("ok.\n");
                  continue;
                 }
                else {
                  print_error(TRAIL);
                  skip_last();
                  continue;
                 }
               }
              else {
                print_error(ROPERROR);
                if (t5.token_type != NOINPUT)
                  skip_last();
                continue;
               }
            }
          else {
            print_error(OPEXPECTED);
            skip_last();
            continue;
           }
          } 
        else {
          print_error(XORXOPYEQ);
          if (t3.token_type != NOINPUT)
            skip_last();
          continue;
         }
       case NOINPUT: printf("%c = %ld\n", 'A'+t1.token_val.var,
                                           var[t1.token_val.var]);
         continue;
       case OP: read_token(&t3,FALSE);
         if (t3.token_type == VAR || t3.token_type == INTEGER) {
           read_token(&t4,FALSE);
           if (t4.token_type == NOINPUT) {
             if (t3.token_type == VAR)
               printf("%ld\n",calc(var[t1.token_val.var],t2.token_val.op,
                                   var[t3.token_val.var]));
             else
               printf("%ld\n",calc(var[t1.token_val.var],t2.token_val.op,
                                   t3.token_val.ival));
             continue;
            }
           else {
             print_error(TRAIL);
             skip_last();
             continue;
            }
          }
         else {
           print_error(ROPERROR);
           if (t3.token_type != NOINPUT)
             skip_last();
           continue;
         }
       default: print_error(MORMEQXORXOPY);
         continue;
      }
    case INTEGER:
      read_token(&t2,TRUE);
      if (t2.token_type == NOINPUT) {
        printf("%ld\n",t1.token_val.ival);
        continue;
       }
      else if (t2.token_type == OP) {
        read_token(&t3,FALSE);
        if (t3.token_type == VAR || t3.token_type == INTEGER) {
          read_token(&t4,FALSE);
          if (t4.token_type == NOINPUT) {
            if (t3.token_type == VAR)
              printf("%ld\n",calc(t1.token_val.ival,t2.token_val.op,
                                  var[t3.token_val.var]));
            else
              printf("%ld\n",calc(t1.token_val.ival,t2.token_val.op,
                                  t3.token_val.ival));
            continue;
           }
          else {
            print_error(TRAIL);
            skip_last();
            continue;
           }
         }
        else {
          print_error(ROPERROR);
          if (t3.token_type != NOINPUT)
            skip_last();
          continue;
         }
      }
     else {
      print_error(OPEXPECTED);
      skip_last();
      continue;
     }
    case NOINPUT:
      continue;
    case EOF:
      return;
    default:
      print_error(HELP);
      skip_last();
   }
 }
}
