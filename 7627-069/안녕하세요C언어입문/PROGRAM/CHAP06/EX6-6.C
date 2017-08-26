day_is(int y, int m, int d) {
int e=0;
if (y < 1992 || m < 1 || m > 12 || d < 1 || d > 31)
   return(0);
if ((y % 400 == 0) || ((y % 100 != 0) && (y % 4 == 0)))
   e = 1;
switch(m) {
 case 1: 
 case 3:
 case 5:
 case 7:
 case 8:
 case 10:
 case 12: return(1);
 case 2: if (d <= 28+e)
           return(1);
         else
           return(0);
 case 4:
 case 6:
 case 9:
 case 11: if (d <= 30)
            return(1);
          else
            return(0);
 }
}
     
mdays(int y, int m) {
int e=0;
if ((y % 400 == 0) || ((y % 100 != 0) && (y % 4 == 0)))
   e = 1;
switch (m) {
 case 1: return(0);
 case 2: return(31);
 case 3: return(59+e);
 case 4: return(90+e);
 case 5: return(120+e);
 case 6: return(151+e);
 case 7: return(181+e);
 case 8: return(212+e);
 case 9: return(243+e);
 case 10: return(273+e);
 case 11: return(304+e);
 case 12: return(334+e);
 }
}
 
long int compute_days(int y, int m, int d) {
long int ds;
int i;
ds = (y-1992)*365l + mdays(y, m) + d;
for (i=1992;i<y;i += 4)
  if ((i % 400 == 0) || ((i % 100 != 0) && (i % 4 == 0)))
     ++ds;
return(ds);
}

void print_day(int d) {
switch (d) {
 case 1: printf("The day is Wednesday.\n");
         break;
 case 2: printf("The day is Thursday.\n");
         break;
 case 3: printf("The day is Friday.\n");
         break;
 case 4: printf("The day is Saturday.\n");
         break;
 case 5: printf("The day is Sunday.\n");
         break;
 case 6: printf("The day is Monday.\n");
         break;
 case 0: printf("The day is Tuesday.\n");
         break;
 }
}
 
void main() {
int year, mon, day;
long int days;
printf("Input year: ");
scanf("%d",&year);
printf("Input month: ");
scanf("%d",&mon);
printf("Input day: ");
scanf("%d",&day);
if (!day_is(year,mon,day))
  printf("Incorrect date!!\n");
else {
  days = compute_days(year,mon,day);
  print_day((int)(days % 7));
 }
}
