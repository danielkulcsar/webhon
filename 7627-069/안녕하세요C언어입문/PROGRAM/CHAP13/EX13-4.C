#include <stdio.h>
#include <graph.h>
#define MAXP 10
main() {
struct videoconfig info, far *vp;
struct xycoord points[MAXP];
int i, j;
for (i=0;i<MAXP;++i) {
  printf("Input %dth point: ",i+1);
  scanf("%d%d",&points[i].xcoord,&points[i].ycoord);
 }
if (!_setvideomode(_MAXRESMODE)) exit(1);
vp = _getvideoconfig(&info);
for (i=0;i<MAXP;++i)
  if (vp->numxpixels < points[i].xcoord || vp->numypixels < points[i].ycoord) {
    _setvideomode(_DEFAULTMODE);
    printf("Error: points are out of range.\n");
    exit(1);
   }
for (i=1;i < MAXP;++i)
  for (j=0;j <= i-1; ++j) {
    _moveto(points[j].xcoord,points[j].ycoord);
    _lineto(points[i].xcoord,points[i].ycoord);
   }
getch();
_clearscreen(_GCLEARSCREEN);
_setvideomode(_DEFAULTMODE);
}
