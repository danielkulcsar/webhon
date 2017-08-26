main() {
char c;
while ((c = getchar()) != '\n')
  if (c >= 'a' && c <= 'z')
     putchar(c-32);
  else
     putchar(c);
putchar('\n');
}
