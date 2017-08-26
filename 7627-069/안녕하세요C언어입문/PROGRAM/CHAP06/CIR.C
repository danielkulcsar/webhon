/* cir.c */
extern unsigned long int data;
circulate() {
unsigned char u=0;
u = (data & 0x80000000lu) >> 31;
data <<= 1;
data |= u;
}
