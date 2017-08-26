#include <stdio.h>
#include <fcntl.h>
main(int argc, char *argv[]) {
int fd1, fd2;
char buf[BUFSIZ];
int n;
if (argc != 3) {
  fprintf(stderr,"Usage: %s file1 file2.\n",*argv);
  return(1);
 }
if ((fd1 = open(*(argv+1),O_RDONLY)) < 0) {
  fprintf(stderr,"Error: cannot open %s.\n",*(argv+1));
  return(2);
 }
if ((fd2 = open(*(argv+2),O_WRONLY | O_TRUNC | O_CREAT, 0700)) < 0) {
  fprintf(stderr,"Error: cannot create %s.\n",*(argv+2));
  return(3);
 }
while ((n = read(fd1,buf,BUFSIZ)) > 0)
  if (write(fd2,buf,n) != n) {
    fprintf(stderr,"Error in writing %s.\n",*(argv+2));
    return(4);
   }
if (n < 0) {
  fprintf(stderr,"Error in reading %s.\n",*(argv+1));
  return(5);
 }
}
