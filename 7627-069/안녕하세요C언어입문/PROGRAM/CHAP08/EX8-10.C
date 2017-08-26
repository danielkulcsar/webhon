void main() {
static char *month[] = {"January","February","March","April","May","June",
    "July","August","September","October","November","December"};
static int mno[] = {31,28,31,30,31,30,31,31,30,31,30,31};
int year, days;
int i;
printf("Year? ");
scanf("%d",&year);
printf("Days? ");
scanf("%d",&days);
mno[1] += ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
for (i=0;days > mno[i];days -= mno[i++]);
printf("The date is: %s, %d\n",month[i],days);
}
