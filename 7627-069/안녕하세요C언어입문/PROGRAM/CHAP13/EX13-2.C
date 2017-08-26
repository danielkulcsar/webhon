#include <conio.h>
#include <graphics.h>
main() {
int driver = DETECT, mode;
initgraph(&driver,&mode,"");
settextstyle(GOTHIC_FONT,HORIZ_DIR,1);
outtextxy(10,10,"Gothic ");
settextstyle(TRIPLEX_FONT,HORIZ_DIR,4);
outtextxy(20,20,"Triplex ");
settextstyle(SMALL_FONT,HORIZ_DIR,4);
outtextxy(30,30,"Small ");
settextstyle(SANS_SERIF_FONT,HORIZ_DIR,8);
outtextxy(40,40,"Sans Serif");
settextstyle(DEFAULT_FONT,HORIZ_DIR,12);
outtextxy(50,50,"Default");
settextstyle(GOTHIC_FONT,VERT_DIR,16);
outtextxy(100,100,"Gothic");
getch();
closegraph();
}
