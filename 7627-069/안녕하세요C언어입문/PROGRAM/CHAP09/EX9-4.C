#include <stdio.h>
#include <string.h>
struct time {
  int year;
  int month;
  int day;
 };
struct project {
  char name[80];
  struct time stime;
  struct time etime;
  int pno;
  long int cost;
 } p;
  
void main() {
int first=1;
long int t;
double d;
long int maxtime;
char maxtname[80];
long int mintime;
char mintname[80];
int maxpno;
char maxpname[80];
int minpno;
char minpname[80];
long int maxcost;
char maxcname[80];
long int mincost;
char mincname[80];
double maxpcost;
char maxpcname[80];
int read_project(void);
long int get_time(struct time,struct time);
while (read_project() != EOF) {
  if (first) {
    mintime = maxtime = get_time(p.stime,p.etime);
    minpno = maxpno = p.pno;
    maxcost = mincost = p.cost;
    maxpcost = (double) p.cost / (double) p.pno;
    strcpy(maxtname,p.name);
    strcpy(mintname,p.name);
    strcpy(maxpname,p.name);
    strcpy(minpname,p.name);
    strcpy(maxcname,p.name);
    strcpy(mincname,p.name);
    strcpy(maxpcname,p.name);
    first = 0;
   }
  else { 
    t = get_time(p.stime,p.etime);
    if (t > maxtime) {
      maxtime = t;
      strcpy(maxtname,p.name);
     }
    if (t < mintime) {
      mintime = t;
      strcpy(mintname,p.name);
     }
    if (p.pno > maxpno) {
      maxpno = p.pno;
      strcpy(maxpname,p.name);
     }
    if (p.pno < minpno) {
      minpno = p.pno;
      strcpy(minpname,p.name);
     }
    if (p.cost > maxcost) {
      maxcost = p.cost;
      strcpy(maxcname,p.name);
     }
    if (p.cost < mincost) {
      mincost = p.cost;
      strcpy(mincname,p.name);
     }
    d = (double) p.cost / (double) p.pno;
    if (d > maxpcost) {
      maxpcost = d;
      strcpy(maxpcname,p.name);
     }
   }
 }
if (first) 
  printf("No projects.\n");
else {
  printf("The project of the maximum duration: \n   %s (%ld days).\n",
    maxtname,maxtime);
  printf("The project of the minimum duration: \n   %s (%ld days).\n",
    mintname,mintime);
   printf("The  project  of  the  maximum  number  of  persons:  \n    %s   (%d persons).\n", maxpname,maxpno);
   printf("The  project  of  the  minimum  number  of  persons:  \n    %s   (%d persons).\n", minpname,minpno);
   printf("The project of the maximum cost: \n   %s (%ld won).\n", maxcname,maxcost);
   printf("The project of the minimum cost: \n   %s (%ld won).\n", mincname,mincost);
   printf("The project of the maximum cost per person: \n   %s (%.3lf won).\n", maxpcname,maxpcost);
 }
}

int read_project() {
char *cp=p.name;
void skip_any();
while ((*cp = getchar()) != EOF)
  if (*cp == '\n')
    break;
  else
    ++cp;
if (cp == p.name)
  return(EOF);
else
  *cp = '\0';
if (scanf("%d%d%d%d%d%d%d%ld",&p.stime.year,&p.stime.month,&p.stime.day,
          &p.etime.year,&p.etime.month,&p.etime.day,&p.pno,&p.cost) == EOF)
  return(EOF);
skip_any();
return(1);
}

void skip_any() {
char c;
while ((c = getchar()) == '\n' || c == ' ' || c == '\t');
ungetc(c,stdin);
}
 
long int get_time(struct time s,struct time e) {
long int get_days(int,int,int);
return(get_days(e.year,e.month,e.day)-get_days(s.year,s.month,s.day)+1);
}

long int get_days(int y,int m,int d) {
long int days;
static int mdays[] = {0,31,59,90,120,151,181,212,243,273,304,334};
days = (y-1)*(long int)365;
days += (y-1) / 4 - (y-1) / 100 + (y-1) /400;
days += mdays[m-1];
days += d;
if (m > 2 && ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0))
  ++days;
return(days);
}
