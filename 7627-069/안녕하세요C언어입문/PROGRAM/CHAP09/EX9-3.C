#include <stdio.h>
struct list_t {
 int data;
 struct list_t *next;
} list[100];
struct list_t *head=NULL;

void main() {
int i;
void insert_list(int);
while (scanf("%d",&i) != EOF) 
  insert_list(i);
i=0;
while (head) {
  printf("%8d",head->data);
  if (++i % 10 == 0)
    putchar('\n');
  head = head->next;
 }
}

void insert_list(int d) {
struct list_t *fp = head, *bp = NULL;
struct list_t *alloc_list(void);
if (!head) {
  head = alloc_list();
  head->data = d;
  head->next = NULL;
  return;
 }
while (fp != NULL && fp->data < d) {
  bp = fp;
  fp = fp->next;
 }
fp = alloc_list();
if (fp) {
  fp->data = d;
  if (bp) {
    fp->next = bp->next;
    bp->next = fp;
   }
  else {
    fp->next = head;
    head = fp;
   }
 }
else
  printf("Error: no available list.\n");
}

struct list_t *alloc_list() {
static int nlist=0;
if (nlist < 100)
  return(&list[nlist++]);
else
  return(NULL);
}
