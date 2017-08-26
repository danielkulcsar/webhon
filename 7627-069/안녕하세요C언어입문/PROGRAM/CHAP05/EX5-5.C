main() {
int sc;
printf("Input score: ");
scanf("%d",&sc);
if (sc > 100 || sc < 0)
   printf("Data Error\n");
else if (sc >= 90) 
   printf("A\n");
else if (sc >= 80)
   printf("B\n");
else if (sc >= 70)
   printf("C\n");
else if (sc >= 60)
   printf("D\n");
else 
   printf("F\n");
}
