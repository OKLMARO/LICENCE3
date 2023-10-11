#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdio.h>

void grand(const char *filename) {
    int fd = open(filename, O_RDWR);
    if (fd == -1) {
        printf("ERROR");
        exit(EXIT_FAILURE);
    }

    char buffer[4096];
    int tete_ecriture = 0;

    ssize_t Read;
    while((Read = read(fd, buffer, sizeof(buffer))) > 0){
        for(int i = 0; i < Read; i++){
            buffer[i] = toupper(buffer[i]);
        }
        lseek(fd, tete_ecriture, SEEK_SET);
        write(fd, buffer, Read);
        tete_ecriture = tete_ecriture + Read;
    }
    printf("finish\n");
    close(fd);
}

int main(int argc, char *argv[]){
    for(int i = 1; i < argc; i++){
        grand(argv[i]);
    }
    return 0;
}