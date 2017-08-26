#include <stdio.h>
#include <graph.h>
main() {
struct videoconfig info, far *vp;
vp = _getvideoconfig(&info);
printf("Your PC has: ");
switch(vp->adapter) {
 case _MDPA: printf("Monochrome Display Adapter");
             break;
 case _CGA: printf("Color Graphics Adapter");
            break;
 case _EGA: printf("Enhanced Graphics Adapter");
            break;
 case _MCGA: printf("Multicolor Graphics Adapter");
             break;
 case _VGA: printf("Video Graphics Adapter");
            break;
 }
printf(" and ");
switch(info.monitor) {
 case _MONO: printf("Monochrome Montor\n");
             break;
 case _COLOR: printf("Color Monitor\n");
              break;
 case _ENHCOLOR: printf("Enhanced Color Monitor\n");
                break;
 case _ANALOG: printf("Analog Monitor\n");
               break;
 }
}
