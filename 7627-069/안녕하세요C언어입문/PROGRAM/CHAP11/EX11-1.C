#include <time.h>
#include <stdlib.h>
#define TOTAL 1000
#define PLAYER_FIRST 1
#define COMPUTER_FIRST 2
#define SHOW_HISTORY 3
#define QUIT 4
enum game {ODD, EVEN};
int pwin=0;
int cwin=0;

void main() {
int get_command(void);
void play_game(int,int,int);
void show_history(void);
srand((unsigned int)time(0));
while (1) {
  switch(get_command()) {
    case QUIT: return;
    case PLAYER_FIRST: play_game(TOTAL,TOTAL,PLAYER_FIRST);
      break;
    case COMPUTER_FIRST: play_game(TOTAL,TOTAL,COMPUTER_FIRST);
      break;
    case SHOW_HISTORY: show_history();
   }
 }
}

void play_game(int pmoney, int cmoney, int turn) {
char c;
int bet;
enum game guess, guess2;
while (1) {
printf("You have %d won.\nI have %d won.\n",pmoney,cmoney);
if (turn == PLAYER_FIRST) {
  printf("You first.\n");
  bet = rand() % cmoney;
  if (bet == 0)
    bet = cmoney; 
  guess = ((rand() % 2) == 1 ? ODD : EVEN);
  printf("I bet %d won.\n",bet);
  printf("Input your status(ODD = 1, EVEN = 2) ");
  while (1) {
    c = getchar();
    if (c == '1' || c == '2') {
      while (getchar() != '\n');
      if (c == '1') { 
          if (guess == ODD) {
            printf("I guessed ODD. I won !! You lose %d won.\n",bet);
            cmoney += bet;
            pmoney -= bet;
           }
          else {
            printf("I guessed EVEN. You won !! I lose %d won.\n",bet);
            cmoney -= bet;
            pmoney += bet;
           }
      break;
      }
     else {
          if (guess == ODD) {
            printf("I guessed ODD. You won !! I lose %d won.\n",bet);
            cmoney -= bet;
            pmoney += bet;
           }
          else {
            printf("I guessed EVEN. I won !! You lose %d won.\n",bet);
            cmoney += bet;
            pmoney -= bet;
           }
          break;
       }
     }
    else {
     if (c != '\n')
       while (getchar() != '\n');
     printf("You should select a number 1(ODD) or 2(EVEN).\n");
    }
   }
  }
else {
  printf("I first.\n");
  guess = ((rand() % 2) == 1 ? ODD : EVEN);
  printf("Input your guess(ODD = 1, EVEN = 2) ");
  c = getchar();
  while (c != '1' && c != '2') {
    if (c != '\n')
      while (getchar() != '\n');
    printf("You should type 1(ODD) or 2(EVEN).\n");
    c = getchar();
   }
  guess2 = (c == '1' ? ODD : EVEN);
  while (getchar() != '\n');
  while (1) {
    printf("Input your bet(1-%d) \n",pmoney);
    bet = 0;
    c = getchar();
    while (c >= '0' && c <= '9') {
      bet = bet*10 + c - '0';
      c = getchar();
     }
    if (c != '\n')
      while (getchar() != '\n');
    if (bet <= 0 || c > pmoney)
      printf("You should bet between 1 and %d.\n",pmoney);
    else
      break;
   } 
  printf("You bet %d won for ",bet);
  if (guess2 == ODD)
    printf("ODD\n");
  else
    printf("EVEN\n");
  if (guess2 == ODD) { 
    if (guess == ODD) {
      printf("My staus is ODD. You won !! I lose %d won.\n",bet);
      cmoney -= bet;
      pmoney += bet;
     }
    else {
      printf("My status is EVEN. I won !! You lose %d won.\n",bet);
      cmoney += bet;
      pmoney -= bet;
     }
   }
  else {
    if (guess == ODD) {
      printf("My staus is ODD. I won !! You lose %d won.\n",bet);
      cmoney += bet;
      pmoney -= bet;
     }
    else {
      printf("My status is EVEN. You won !! I lose %d won.\n",bet);
      cmoney -= bet;
      pmoney += bet;
     }
   }
  } 
if (pmoney == 0) {
  printf("You lose all your money. You lose the game.\n");
  ++cwin;
  return;
 }
else if (cmoney == 0) {
  printf("I lose all my money. I lose the game.\n");
  ++pwin;
  return;
 }
if (pmoney > cmoney)
  turn = PLAYER_FIRST;
else
  turn = COMPUTER_FIRST;
}
}

int get_command() {
char c;
printf("\nWelcome to the ODD/EVEN game!!\n\n");
printf("You can select one of these:\n");
printf("\t1. Play a new game(you first).\n");
printf("\t2. Play a new game(computer first).\n");
printf("\t3. Show the game history.\n\n");
printf("\t4. Quit.\n\n");
printf("Your choice? (1-4) \7");
while (1) {
  c = getchar();
  if (c >= '1' && c <= '4') {
    while (getchar() != '\n');
    switch(c) {
      case '1': return(PLAYER_FIRST);
      case '2': return(COMPUTER_FIRST);
      case '3': return(SHOW_HISTORY);
      case '4': return(QUIT);
     }
   }
  else {
   if (c != '\n')
     while (getchar() != '\n');
   printf("You should select a number between 1 and 4.\n");
  }
 }
}

void show_history() {
if (pwin == 0 && cwin == 0)
  printf("\nNo game yet.\n");
else {
  printf("You won %d times, and I won %d times.\n",pwin,cwin);
  if (pwin > cwin)
    printf("You are lucky!!\n");
  else if (cwin > pwin)
    printf("Ha Ha I won more!!\n");
  else
    printf("The perfect match!!\n");
 }
}
