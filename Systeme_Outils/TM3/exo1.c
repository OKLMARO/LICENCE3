#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
    if(argc < 3){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    for(int i = 2; i < argc; i = i + 1){
        pid_t pid = fork();

        if(pid == -1){
            perror("Erreur fork");
            exit(1);
        }

        if(pid == 0){
            execlp(argv[1], argv[1], argv[i]);
            perror("Erreur exec");
            exit(1);
        }
    }

    for(int i = 2; i < argc; i = i + 1){
        wait(NULL);
    }

    return 0;
}