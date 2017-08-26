struct student {
  char name[30];
  long int no;
  float avg;
 } s[100];
main() {
int N;
int i, j;
struct student t;
void read_name(char *);
scanf("%d",&N);
if (N > 100) { 
  printf("Error: too many records(exceeding 100).\n");
  return(1);
 }
for (i=0;i<N;++i) {
  int sum=0;
  int grade;
  read_name(s[i].name);
  scanf("%ld",&s[i].no);
  if (s[i].no < 920000 || s[i].no > 929999) {
    printf("Error: invalid student number in the %dth record.\n",i+1);
    return(2);
   }
  for (j=0;j<5;++j) {
    scanf("%d",&grade);
    if (grade < 0 || grade > 100) {
      printf("Error: invalid grade in the %dth record.\n",i+1);
      return(3);
     }
    sum += grade;
   }
  s[i].avg = sum / 5.0f;
 }
for (i=0;i<N-1;++i)
  for (j=i+1;j<N;++j)
    if (s[i].avg < s[j].avg) {
      t = s[i];
      s[i] = s[j];
      s[j] = t;
     }
printf("%5s%28s%17s%10s\n\n","Rank","Name","ID Number","Average");
for (i=0;i<N;++i)
  printf("%5d%30s%15ld%10.1f\n",
     i+1,s[i].name,s[i].no,s[i].avg);
}

void read_name(char *c) {
do 
  *c = getchar();
 while (*c == '\n' || *c == ' ' || *c == '\t');
do 
  *++c = getchar();
 while (*c != '\n');
*c = '\0';
}
