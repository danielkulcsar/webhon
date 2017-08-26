main() {
int i;
printf("Input the number of books: ");
scanf("%d",&i);
printf("%d book%c\n",i,(i == 1) ? '\0' : 's');
}
