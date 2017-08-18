
#define  MAX_VERTEX  512

void init(void);
void insertVertex(char name);
int  insertEdge(char v, char u);
int  deleteVertex(char name);
int  deleteEdge(char v, char u);
int  isEmptyGraph(void);
char getVertex(int index);

int  checkEdge(char v, char u);
int  findVertex(char name);

void printMatrix(void);
void printVertex(void);
void printEdge(void);
void printAdjacent(char name);

void DepthFirstSearch(char v);
void BreadthFirstSearch(char v);
