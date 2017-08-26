#include <stdio.h>
void main() {
char sname[11];
char name[21];
int res;
int read_fname(char *);
int read_name(char *);
printf("%20s%10s\n","Name","Surname");
while (res = read_fname(sname)) {
  if (res == -1) {
    printf("Error: no surname.\n");
    break;
   }
  if (res == -2) {
    printf("Error: surname did not end with \',\'.\n");
    break;
   }
  if (res == -3) {
    printf("Error: surname is too long.\n");
    break;
   }
  if (res = read_name(name)) {
    if (res == -1) {
      printf("Error: name is too long.\n");
      break;
     }
    printf("%20s%10s\n",name,sname);
    continue;
   }
  printf("Error: only surname without name.\n");
  break;
 }
}

int read_fname(char *name) {
char c;
int i=0;
while ((c = getchar()) != EOF) {
  if (c == ',')
    if (i != 0) {
      *name = '\0';
      return(1);
     }
    else
      return(-1);
  if (c == '\n')
    return(-2);
  if (c != ' ' && c != '\t')
    if (++i <= 10)
      *name++ = c;
    else 
      return(-3);
  }
 if (i == 0)
   return(0);
 else
   return(-2);
}

int read_name(char *name) {
int i=0;
char c;
while ((c = getchar()) != EOF) {
  if (c == '\n') {
    *name = '\0';
    return(i);
   }
  if (++i <= 20)
    *name++ = c;
  else
    return(-1);
 }
return(i);
}
