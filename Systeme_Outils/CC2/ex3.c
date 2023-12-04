#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

void reverse(const char *filename) {
    int fd = open(filename, O_RDWR);
    if (fd == -1) {
        perror("Erreur lors de l'ouverture du fichier");
        exit(EXIT_FAILURE);
       
    }
     
    char buffer[1024];
    ssize_t Read;

    Read = read(STDIN_FILENO, buffer, sizeof(buffer));
        
    if (write(fd, buffer, Read) == -1) {
        perror("Erreur lors de l'écriture dans le fichier");
        close(fd);
        exit(EXIT_FAILURE);
    }
    printf("%s", buffer);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <nom du fichier>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    reverse(argv[1]);

    return EXIT_SUCCESS;
}