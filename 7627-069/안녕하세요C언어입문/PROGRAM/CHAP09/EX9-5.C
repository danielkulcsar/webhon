struct hangeul {
  unsigned int s:1;
  unsigned int f:5;
  unsigned int m:5;
  unsigned int l:5;
 };
main() {
unsigned char c;
unsigned char c2;
struct hangeul h;
while ((c = getchar()) != 255) {
  h.s = ((c & 0x80) >> 7);
  if (h.s == 0) {
    printf("%c -> %d\n",c,c);
    continue;
   }
  h.f = ((c & 0x7c) >> 2);
  h.m = ((c & 0x3) << 3);
  c2 = getchar();
  h.m |= ((c2 & 0xe0) >> 5);
  h.l = c2 & 0x01f;
  printf("%c%c -> %u, %u, %u\n",c,c2,h.f,h.m,h.l);
 }
}
