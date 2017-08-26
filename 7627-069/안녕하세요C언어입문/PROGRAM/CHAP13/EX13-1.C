#include <conio.h>
#include <graphics.h>
main() {
int driver = DETECT, mode;
int maxx, maxy;
initgraph(&driver,&mode,"");
maxx = getmaxx();
maxy = getmaxy();
putpixel(0,0,0);
line(2,2,maxx-2,maxy-2);
rectangle(4,4,maxx-4,maxy/2-4);
bar(4,maxy/2,maxx-4,maxy-4);
circle(maxx/2,maxy/2,maxy/2-5);
getch();
cleardevice();
line(0,0,maxx,maxy);
rectangle(0,0,maxx,maxy);
line(0,maxy,maxx,0);
circle(maxx/2,maxy/2,maxy/2);
getch();
closegraph();
}
