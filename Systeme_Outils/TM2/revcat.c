#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>

void revcat(const char *filein, const char *fileout) {
    int fd = open(filein, O_RDWR);
    int fd2 = open(fileout, O_RDWR);
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
        lseek(fd2, tete_ecriture, SEEK_SET);
        write(fd2, buffer, Read);
        tete_ecriture = tete_ecriture + Read;
    }
    printf("finish\n");
    close(fd);
    close(fd2);
}

int main(int argc, char *argv[]){
    revcat(argv[1], argv[2]);
    return 0;
}