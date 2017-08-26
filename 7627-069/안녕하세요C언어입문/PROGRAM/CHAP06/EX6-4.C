#include <stdio.h>
main() {
char c;
int vno=0;
int cno=0;
int uno=0;
int lno=0;
int dno=0;
while ((c = getchar()) != EOF) {
  if (is_vowel(c))
    ++vno;
  else if (is_alphabet(c))
    ++cno;
  if (is_upper(c))
    ++uno;
  else if (is_lower(c))
    ++lno;
  else if (is_digit(c))
    ++dno;
  } /* while */
printf("Number of Vowels: %d\n",vno);
printf("Number of Consonants: %d\n",cno);
printf("Number of Uppercases: %d\n",uno);
printf("Number of Lowercases: %d\n",lno);
printf("Number of Digits: %d\n",dno);
} /* main */

is_vowel(char c) {
switch(c) {
 case 'a':
 case 'e':
 case 'i':
 case 'o':
 case 'u':
 case 'A':
 case 'E':
 case 'I':
 case 'O':
 case 'U': return(1);
 default: return(0);
 }
}

is_alphabet(char c) {
return(is_upper(c) || is_lower(c));
}

is_upper(char c) {
return(c >= 'A' && c <= 'Z');
}

is_lower(char c) {
return(c >= 'a' && c <= 'z');
}

is_digit(char c) {
return(c >= '0' && c <= '9');
}
