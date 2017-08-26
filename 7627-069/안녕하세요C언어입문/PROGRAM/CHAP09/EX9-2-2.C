#include <stdio.h>
struct stype {
  char *name;
  int no;
 } ss[] = { {"if",0},{"who",0},{"while",0},{"do",0},{"the",0},{"text",0},
            {"tree",0} };
struct atype {
  int cstatus;
  char c;
  char command;
  int arg;
 } automata[] = { {0,'i','g',4},{0,'w','g',5},{0,'d','g',10},{0,'t','g',11},
  {4,'f','+',0},{5,'h','g',6},{6,'o','+',1},{6,'i','g',8},{8,'l','g',9},
  {9,'e','+',2},{10,'o','+',3},{11,'h','g',14},{11,'e','g',15},{11,'r','g',17},
  {14,'e','+',4},{15,'x','g',16},{16,'t','+',5},{17,'e','g',18},{18,'e','+',6}
 };
int status=0;

void main() {
char c;
int i;
int nstatus;
void process(char,int);
int locate(int,char);
while ((c = getchar()) != EOF) {
  if ((nstatus = locate(status,c)) >= 0) {
    status = nstatus;
    process(automata[status].command,automata[status].arg);
   }
  else {
    if (status != 0) {
      ungetc(c,stdin);
      status = 0;
     }
  }
 }
for (i=0;i<7;++i)
  printf("Number of %s: %d\n",ss[i].name,ss[i].no);
}

int locate(int s, char c) {
int i;
for (i=s;automata[i].cstatus == s;++i)
  if (automata[i].c == c)
    return(i);
return(-1);
}

void process(char c,int arg) {
switch(c) {
  case 'g': status = arg;
            break;
  case '+': ++ss[arg].no;
 }
}
