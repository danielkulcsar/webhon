main() {
int i;
printf("Input grade: ");
scanf("%d",&i);
printf("\nThe result is: %c\n",65*(i >= 90) + 66*(80 <= i && i <= 89) +
            67*(70 <= i && i <= 79) + 70*(i <= 69));
}
