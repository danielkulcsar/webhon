#include <stdio.h>
#include <fcntl.h>
#ifndef O_BINARY
#define O_BINARY 0
#endif
main(int argc,char *argv[]) {
int fd;
long int size;
int n;
char buf[BUFSIZ];
if (argc == 1) {
  fprintf(stderr,"Usage: %s file1 file2 ... filen.\n",*argv);
  return(0);
 }
while (--argc > 0) {
  if ((fd = open(*++argv,O_RDONLY | O_BINARY)) == -1) {
    fprintf(stderr,"Error: cannot open %s.\n",*argv);
    continue;
   }
  size = 0l;
  while ((n = read(fd,buf,BUFSIZ)) > 0)
    size += n;
  if (n == 0)
     fprintf(stdout,"%s: %ld bytes.\n",*argv,size);
  else
     fprintf(stderr,"Error in reading %s.\n",*argv);
 }
}
