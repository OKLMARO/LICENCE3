#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define BUFFER_SIZE 4096

void convertToUppercase(const char *filename) {
    int fd = open(filename, O_RDWR);
    if (fd == -1) {
        perror("Erreur lors de l'ouverture du fichier");
        return;
    }

    char buffer[BUFFER_SIZE];
    ssize_t bytes_read, bytes_written;

    while ((bytes_read = read(fd, buffer, sizeof(buffer))) > 0) {
        for (ssize_t i = 0; i < bytes_read; i++) {
            if (islower(buffer[i])) {
                buffer[i] = toupper(buffer[i]);
            }
        }

        // Se déplacer à la position actuelle du curseur
        off_t current_position = lseek(fd, 0, SEEK_CUR);

        // Revenir au début du bloc actuel
        off_t block_start = current_position - bytes_read;

        // Déplacer le curseur au début du bloc
        lseek(fd, block_start, SEEK_SET);

        // Écrire le bloc modifié
        bytes_written = write(fd, buffer, bytes_read);

        // Replacer le curseur à la position actuelle
        lseek(fd, current_position, SEEK_SET);

        if (bytes_written == -1) {
            perror("Erreur lors de l'écriture dans le fichier");
            close(fd);
            return;
        }
    }

    if (bytes_read == -1) {
        perror("Erreur lors de la lecture du fichier");
        close(fd);
        return;
    }

    close(fd);
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Utilisation : %s <fichier1> [fichier2 ...]\n", argv[0]);
        return 1;
    }

    for (int i = 1; i < argc; i++) {
        convertToUppercase(argv[i]);
    }

    printf("Conversion en majuscules terminée.\n");

    return 0;
}
