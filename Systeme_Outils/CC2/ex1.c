#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

void revcat(const char *filein) {
    int fd = open(filein, O_RDWR);
    if (fd == -1) {
        printf("ERROR");
        exit(EXIT_FAILURE);
    }

    char buffer[4096];
    char temp;
    int tete_ecriture = 0;

    ssize_t Read;
    while((Read = read(fd, buffer, sizeof(buffer))) > 0){
        for(int i = 0; i < Read / 2; i++){
            temp = buffer[i];
            buffer[i] = buffer[Read - i - 1];
            buffer[Read - i - 1] = temp;
        }
        printf("%s\n", buffer);
        tete_ecriture = tete_ecriture + Read;
    }
    close(fd);
}

int main(int argc, char *argv[]){
    revcat(argv[1]);
    return 0;
}