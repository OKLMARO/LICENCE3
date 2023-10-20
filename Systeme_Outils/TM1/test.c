#include <stdio.h>

int toto (){
    printf("taratablabla");
    return 0;
}

int main (){
    int i = 0;
    while(!toto){
        printf("while % d \n", i ++);
    }
    return 0;
}
