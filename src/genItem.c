#include <stdio.h>
int main() {
    const char str[] =
"{\n"
"  \"parent\":\"minecraft:item/iron_hoe\",\n"
"  \"textures\": {\n"
"    \"layer0\": \"icehard:tools/icehard_hoe_%s\"\n"
"  }\n"
"}\n";
    const char *names[7] = {"blue", "green", "yellow", "orange", "red", "purple", "light"};
    int i;
    char fn[100];
    for (i = 0; i < 7; i++) {
        sprintf(fn, "icehard_hoe_%s.json", names[i]);
        FILE *f = fopen(fn, "w");
        fprintf(f, str, names[i]);
        fclose(f);
    }
    return 0;
}
