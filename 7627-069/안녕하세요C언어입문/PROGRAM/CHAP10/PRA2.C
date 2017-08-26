#define MAX 100
#define PR(x,f) printf(#x"= "#f"\n",x)
#define m(x,y) x * y

main() {
int i=MAX;
int j=10;
i += m(i+j,i-j);
PR(i,%d);
PR(j,%d);
}
