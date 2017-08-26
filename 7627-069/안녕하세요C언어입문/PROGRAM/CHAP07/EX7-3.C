main() {
int x, y, z;
void swap(int *, int *);
printf("Input three numbers: ");
scanf("%d%d%d",&x,&y,&z);
if (x > y)
  swap(&x,&y);
if (x > z)
  swap(&x,&z);
if (y > z)
  swap(&y,&z);
printf("Sorted result: %d %d %d\n",x,y,z);
}

void swap(int *ip, int *jp) {
int t;
t = *ip;
*ip = *jp;
*jp = t;
}
