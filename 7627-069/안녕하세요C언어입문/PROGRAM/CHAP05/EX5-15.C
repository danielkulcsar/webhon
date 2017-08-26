main() {
long int no;
long int sum = 0l;
int sum2 = 0l;
int n;
int g;
int i, j;
scanf("%d",&n);
for (i=1;i<=n;i++) {
 scanf("%ld",&no);
 if (no > 929999l || no < 920000l) goto error1;
 for (j=1;j<=10;j++) {
    scanf("%d",&g);
    if (g > 100 || g < 0) goto error2;
    sum2 += g;
   } /* for */
 printf("%6ld %5.1f\n",no,sum2/10.0f);
 sum += sum2;
 sum2 = 0l;
 }
printf("Total Average = %5.1f\n",sum/(n*10.0));
goto end;
error1: printf("Error in number!\n");
goto end;
error2: printf("Error in grade!\n");
end: ;
}
