main() {
int i, j;
printf("Input two integers: ");
scanf("%d%d",&i,&j);
printf("\nYou typed: %d and %d\n",i,j);
printf("The result is %d\n",(i > j) - (i < j));
}
