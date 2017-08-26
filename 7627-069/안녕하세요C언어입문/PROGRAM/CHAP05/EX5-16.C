main() {
long int left, right;
long int res;
long int i;
char op;
while (1) {
 printf("Input Expression --> ");
 scanf("%ld",&left);
 scanf("%c",&op);
 if (op == 'Q' || op == 'q') break;
 scanf("%ld",&right);
 switch(op) {
  case '+': printf("%ld\n",left+right); /* x+y */
           break;
  case '-': printf("%ld\n",left-right); /* x-y */
           break;
  case '*': printf("%ld\n",left*right); /* x*y */
           break; 
  case '/': printf("%ld\n",left/right); /* x/y */
           break;
  case '%': printf("%ld\n",left%right); /* x%y */
           break;
  case '^': if (right < 0)              /* x^y */
             printf("Error: negative exponent\n");
           else {
             res = 1l;
             for (i=1l;i<=right;i++)
               res *= left;
             printf("%ld\n",res);
            break;
            }
  default: printf("Error: unknown operator\n");
  } /* switch */
 } /* while */
} /* main */
