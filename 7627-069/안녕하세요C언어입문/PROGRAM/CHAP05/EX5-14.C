main() {
char c;
do {
  c = getchar();
  if (c >= 'a' && c <= 'z')
     putchar(c-32);
  else if (c >= 'A' && c <= 'Z' && c != 'Q')
     putchar(c+32);
  else
     putchar(c);
 } while (c != 'Q');
} /* main */
