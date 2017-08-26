int i;
char c;
float f;
double d;
static int si;
static char sc;
static float sf;
static double sd;
main() {
int mi;
char mc;
float mf;
double md;
void f1();
printf("Address of i = %u\n",&i);
printf("Address of c = %u\n",&c);
printf("Address of f = %u\n",&f);
printf("Address of d = %u\n",&d);
printf("Address of si = %u\n",&si);
printf("Address of sc = %u\n",&sc);
printf("Address of sf = %u\n",&sf);
printf("Address of sd = %u\n",&sd);
printf("Address of mi = %u\n",&mi);
printf("Address of mc = %u\n",&mc);
printf("Address of mf = %u\n",&mf);
printf("Address of md = %u\n",&md);
{ int bi;
  char bc;
  float bf;
  double bd;
  static int sbi;
  static char sbc;
  static float sbf;
  static double sbd;
  printf("Address of bi = %u\n",&bi);
  printf("Address of bc = %u\n",&bc);
  printf("Address of bf = %u\n",&bf);
  printf("Address of bd = %u\n",&bd);
  printf("Address of sbi = %u\n",&sbi);
  printf("Address of sbc = %u\n",&sbc);
  printf("Address of sbf = %u\n",&sbf);
  printf("Address of sbd = %u\n",&sbd);
  }
 f1();
}

void f1() {
int fi;
char fc;
float ff;
double fd;
static int sfi;
static char sfc;
static float sff;
static double sfd;
printf("Address of fi = %u\n",&fi);
printf("Address of fc = %u\n",&fc);
printf("Address of ff = %u\n",&ff);
printf("Address of fd = %u\n",&fd);
printf("Address of sfi = %u\n",&sfi);
printf("Address of sfc = %u\n",&sfc);
printf("Address of sff = %u\n",&sff);
printf("Address of sfd = %u\n",&sfd);
}
