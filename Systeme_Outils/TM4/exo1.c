#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void lire_puis_ecrire(int fd_lecture, int fd_ecriture){
    char c, d;
    while(read(fd_lecture, &c, sizeof(char)) > 0){
        if(c == 'z'){
            d = 'a';
        } else{
            d = c + 1;
        }
        printf("Le processus %d lit %c et écrit %c\n", getpid(), c, d);
        write(fd_ecriture, &d, sizeof(char));
    }
}

int main(){
    int fd_tube1[2], fd_tube2[2];

    if(pipe(fd_tube1) == -1 || pipe(fd_tube2) == -1){
        perror("pipe");
        exit(1);
    }

    pid_t pid = fork();

    if(pid == -1){
        perror("fork");
        exit(1);
    }

    if(pid == 0){
        close(fd_tube1[0]);
        close(fd_tube2[1]);
        lire_puis_ecrire(fd_tube2[0], fd_tube1[1]);
        close(fd_tube1[1]);
        close(fd_tube2[0]);
    } else{
        close(fd_tube1[1]);
        close(fd_tube2[0]);
        write(fd_tube2[1], "a", sizeof(char));
        lire_puis_ecrire(fd_tube1[0], fd_tube2[1]);
        close(fd_tube1[0]);
        close(fd_tube2[1]);
    }
    return 0;
}