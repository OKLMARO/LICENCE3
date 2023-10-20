#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

void execpar(char* fonction, char* fichier){
    execlp(fonction, fonction, fichier, NULL);
}

int main(int argc, char *argv[]){

    pid_t pid[argc - 3];

    for(int i = 3; i < argc; i = i + 1){
        pid[i - 3] = fork();
        for(int j = 0; j < atoi(argv[2]); j = j + 1){
            if(pid[i - 3] == 0){
                execpar(argv[1], argv[i]);
            }
            wait(NULL);
        }
    }
    
    for(int i = 3; i < argc; i = i + 1){
        wait(NULL);
    }

    return 0;
}