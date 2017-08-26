main() {
char c;
while ((c = getchar()) != 'Q') {
  if (c == ' ' || c == '\n')
    continue;
  printf("%c --> %d\n",c,c);
 } /* while */
} /* main */
