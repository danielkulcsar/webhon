
enum { WORD_SIZE = 32 };

void init(void);
void insertKey(const char* s);
int deleteKey(const char* s);
const char* findKey(const char* s);
unsigned getHashCode(const char* s);

void printHash(void);
void printBucket(unsigned index);
