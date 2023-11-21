#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
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
    int fd_tube1, fd_tube2;

    fd_tube1 = open("tube1", O_WRONLY);
    fd_tube2 = open("tube2", O_RDONLY);
    write(fd_tube2, "a", sizeof(char));
    lire_puis_ecrire(fd_tube2, fd_tube1); 
    close(fd_tube1);
    close(fd_tube2);

    return 0;
}