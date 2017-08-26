main() {
int x,y,z;
printf("Input three numbers: ");
scanf("%d%d%d",&x,&y,&z);
printf("The largest number is %d.\n",
 (((x > y) ? x : y) > z) ? ((x > y) ? x : y) : z);
}
