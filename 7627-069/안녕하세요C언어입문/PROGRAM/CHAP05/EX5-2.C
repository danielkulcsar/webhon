main() {
unsigned long int day;
unsigned long int hour;
unsigned long int min;
unsigned long int sec;
unsigned long int res;
printf("Input days: ");
scanf("%lu",&day);
printf("Input hours: ");
scanf("%lu",&hour);
printf("Input minutes: ");
scanf("%lu",&min);
printf("Input seconds: ");
scanf("%lu",&sec);
printf("\n%lu days, %lu hours, %lu minutes, %lu seconds\n\n",day,hour,min,sec);
res = day*24*60*60+hour*60*60+min*60+sec;
printf("The result: %lu seconds\n",res);
}
