#include <time.h>
#include <stdio.h>
void main() {
static char *cp[] = { "Sunday","Monday","Tuesday","Wednesday","Thursday", 
                      "Friday","Saturday" };
time_t t;
struct tm *tb;
t = time(NULL); 
tb = localtime(&t); 
printf("%d, %d, %d. %d:%d:%d (%s)\n",1900+tb->tm_year,tb->tm_mon+1,tb->tm_mday,
  tb->tm_hour,tb->tm_min,tb->tm_sec,cp[tb->tm_wday]);
}
