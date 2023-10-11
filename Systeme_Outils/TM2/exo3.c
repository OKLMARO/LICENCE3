#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void fils(int n){
    printf("PERE PID: %d\n", getpid());

    pid_t pid[n];

    for (int i = 0; i < n; ++i) {
        
        pid[i] = fork();

        if(pid[i] == 0){
            printf("FILS PID : %d\n", getpid());
            exit(0);
        }
    }
    for (int i = 0; i < n; ++i) {
        wait(NULL);
    }
}

int main() {
    fils(10);
    return 0;
}