#include <stdlib.h>
#include <time.h>
int main(void)
{
  system("bash ./gradlew runClient");
  time_t i = time(NULL);
  while (time(NULL) == i) ;
  return 0;
}
