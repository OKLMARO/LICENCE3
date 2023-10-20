#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char *argv[]) {
    
    for(int i = 0; i < atoi(argv[1]); i = i + 1){
        sleep(1);
        printf("%d\n", i);
        printf("PID : %d\n", getpid());
    }

    return 0;
}