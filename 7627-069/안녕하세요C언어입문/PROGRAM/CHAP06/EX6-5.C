void print_chars (char c, int n) {
int i;
for (i=1;i<=n;i++)
  putchar(c);
}

void main() {
int i;
for (i=1;i<=6;i++) {
  print_chars('*',60);
  putchar('\n');
 }
for (i=1;i<=25;i++) {
  print_chars(' ',15);
  print_chars('*',30);
  putchar('\n');
 }
for (i=1;i<=6;i++) {
  print_chars('*',60);
  putchar('\n');
 }
}
