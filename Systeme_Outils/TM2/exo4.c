#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void pere(int n){
    pid_t pid[n];

    for (int i = 0; i < n; ++i) {
        
        pid[i] = fork();

        if(pid[i] != 0){
            printf("PERE PID : %d\n", getpid());
            exit(0);
        }
    }
}

int main() {
    pere(10);
    return 0;
}