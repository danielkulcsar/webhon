/* ex6-13.c */
unsigned long int data;
void main() {
int i;
printf("Input data: ");
scanf("%lu",&data);
for (i=1;i<=32;i++) {
  circulate();
  printf("data = %lu\n",data);
  print_bit(data);
 }
}
