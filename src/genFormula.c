#include <stdio.h>
int main() {
    const char str[] =
"{\n"
"  \"type\": \"minecraft:crafting_shaped\",\n"
"  \"group\": \"icehard_hoe\",\n"
"  \"result\": {\n"
"    \"item\": \"icehard_hoe_%s\"\n"
"  },\n"
"  \"pattern\": [\n"
"    \"ii\",\n"
"    \" c\",\n"
"    \" m\"\n"
"  ],\n"
"  \"key\": {\n"
"    \"i\": {\"item\": \"icehard_%s\"},\n"
"    \"c\": {\"type\": \"forge:ore_dict\", \"ore\": \"icehard_copper_lv%d_up\"},\n"
"    \"m\": {\"item\": \"masspile\"}\n"
"  }\n"
"}\n";
    const char *names[7] = {"blue", "green", "yellow", "orange", "red", "purple", "light"};
    int i;
    char fn[100];
    for (i = 1; i < 7; i++) {
        sprintf(fn, "icehard_hoe_%s.json", names[i]);
        FILE *f = fopen(fn, "w");
        fprintf(f, str, names[i], names[i], i);
        fclose(f);
    }
    return 0;
}
