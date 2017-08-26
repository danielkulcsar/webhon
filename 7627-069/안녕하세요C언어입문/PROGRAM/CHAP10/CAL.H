#define VAR 1
#define NOINPUT 2
#define INTEGER 3
#define EQUAL 4
#define UNKNOWN 5
#define OP 6
#define TRUE 1 
#define FALSE 0
union tval {
  long int ival;
  char op;
  int var;
};
struct token {
  int token_type;
  union tval token_val;
 };
