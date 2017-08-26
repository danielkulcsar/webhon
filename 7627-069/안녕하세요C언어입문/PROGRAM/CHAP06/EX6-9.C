void hanoi(int N, int from, int temp, int to) {
if (N == 0)
  return;
hanoi(N-1,from,to,temp); 
printf("Move a disk from %1d to %1d\n",from,to);
hanoi(N-1,temp,from,to);
}

void main() {
int n;
printf("Input number of disks: ");
scanf("%d",&n);
if (n <= 0)
  printf("Invalid number! The number must be greater than 0.\n");
else
  hanoi(n,1,2,3);
}
