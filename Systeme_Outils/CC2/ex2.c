#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void difference(int x, int y){

    int temp;

    pid_t pid = fork();

    if(pid == 0){
        if(x >= y){ 
            temp = x - y;
            printf("%d\n", temp);
        } else {
            temp = y - x;
            printf("%d\n", temp);
        }
        exit(0);
    }

    

    pid = fork();

    if(pid == 0){
        execlp("ps", "ps", "-f", NULL);
        perror("Erreur exec");
        exit(1);
    }
    
    wait(NULL);
    wait(NULL);

    temp = x + y;
    printf("%d\n", temp);
}

int main(int argc, char *argv[]){
    difference(atoi(argv[1]), atoi(argv[2]));
    return 0;
}