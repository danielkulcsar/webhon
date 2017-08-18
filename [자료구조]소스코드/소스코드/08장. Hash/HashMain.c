#pragma warning(disable:4996)
#include <stdio.h>
#include <string.h>
#include "Hash.h"

void main(void)
{
    const char* words[] = 
    {
        "naver", "page", "web", "net", "black", "hand", "rain", "meal", "hi", "wow", 
        "news", "movie", "screen", "car", "sky", "snow", "paper", "task", "air", "book", 
        "2008", "red", "fantasy", "sound", "mickey", "wizard", "voice", "face", "mob", "girl", 
        "miss", "hope", "sea", "apple", "hotdog", "man", "camera", "think", "freedom", "smile", 
        "hat", "door", "water", "inline", "soccor", "claim", "venus", "music", "banana", "stop", 
        "beauty", "green", "gas", "terminal", "wind", "house", "right", "no", "sorry", "mine", 
        "tv", "cry", "friend", "boys", "street", "scope", "dress", "coffee", "star", "farm", 
        "animal", "ancient", "future", "disney", "ever", "play", "piano", "listen", "gag", "child", 
    };
    int i, code;
    char key[WORD_SIZE];

    init();

    for(i = 0; i < 80; i++)
    {
        printf("%8s ", words[i]);
        insertKey(words[i]);

        if(i%8 == 7)
            printf("\n");
    }
    printf("\n");
    printHash();

    while(1)
    {
        printf("key   : ");
        scanf("%s", &key);

        printf("code  : %d\n", code = getHashCode(key));

        printBucket(code);

        if(deleteKey(key) == 0)
            break;

        printBucket(code);
        printf("\n");
    }
}

/*
[출력 결과]
   naver     page      web      net    black     hand     rain     meal
      hi      wow     news    movie   screen      car      sky     snow
   paper     task      air     book     2008      red  fantasy    sound
  mickey   wizard    voice     face      mob     girl     miss     hope
     sea    apple   hotdog      man   camera    think  freedom    smile
     hat     door    water   inline   soccor    claim    venus    music
  banana     stop   beauty    green      gas terminal     wind    house
   right       no    sorry     mine       tv      cry   friend     boys
  street    scope    dress   coffee     star     farm   animal  ancient
  future   disney     ever     play    piano   listen      gag    child

0[13] : page sky snow book red sound face claim banana gas wind coffee ever
1[12] : naver task air hope hotdog man camera water venus sorry star disney
2[12] : meal car fantasy voice hat door inline house friend farm future gag
3[12] : web screen mob girl miss think freedom right tv animal ancient piano
4[ 7] : news paper green no boys play listen
5[12] : net black hand movie mickey sea apple soccor mine cry street child
6[12] : rain hi wow 2008 wizard smile music stop beauty terminal scope dress

key   : page
code  : 0
0[13] : page sky snow book red sound face claim banana gas wind coffee ever
0[12] : ever sky snow book red sound face claim banana gas wind coffee

key   : door
code  : 2
2[12] : meal car fantasy voice hat door inline house friend farm future gag
2[11] : meal car fantasy voice hat gag inline house friend farm future

key   : inline
code  : 2
2[11] : meal car fantasy voice hat gag inline house friend farm future
2[10] : meal car fantasy voice hat gag future house friend farm

key   : q
code  : 1
1[12] : naver task air hope hotdog man camera water venus sorry star disney
*/

