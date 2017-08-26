main() {
char c;
printf("Input a character: ");
scanf("%c",&c);
printf("0");
printf("%1d",(c & 0x40) >> 6);
printf("%1d",(c & 0x20) >> 5);
printf("%1d",(c & 0x10) >> 4);
printf("%1d",(c & 0x08) >> 3);
printf("%1d",(c & 0x04) >> 2);
printf("%1d",(c & 0x02) >> 1);
printf("%1d\n",(c & 0x01));
}
