#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>

void revcat(const char *filein, const char *fileout) {
    int fdin = open(filein, O_RDONLY);
    if (fdin == -1) {
        perror("Erreur lors de l'ouverture du fichier en lecture");
        exit(EXIT_FAILURE);
    }

    int fdout = open(fileout, O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
    if (fdout == -1) {
        perror("Erreur lors de l'ouverture du fichier en écriture");
        close(fdin);
        exit(EXIT_FAILURE);
    }

    char buffer[1024];
    ssize_t bytes_read;
    ssize_t bytes_written;
    off_t file_size = lseek(fdin, 0, SEEK_END);

    for (off_t offset = file_size - 1; offset >= 0; offset -= bytes_read) {
        lseek(fdin, offset, SEEK_SET);
        bytes_read = read(fdin, buffer, sizeof(buffer));

        if (bytes_read == -1) {
            perror("Erreur lors de la lecture du fichier");
            close(fdin);
            close(fdout);
            exit(EXIT_FAILURE);
        }

        for (ssize_t i = 0; i < bytes_read / 2; i++) {
            char temp = buffer[i];
            buffer[i] = buffer[bytes_read - i - 1];
            buffer[bytes_read - i - 1] = temp;
        }

        bytes_written = write(fdout, buffer, bytes_read);
        if (bytes_written == -1) {
            perror("Erreur lors de l'écriture dans le fichier de sortie");
            close(fdin);
            close(fdout);
            exit(EXIT_FAILURE);
        }
    }

    close(fdin);
    close(fdout);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Utilisation : %s <filein> <fileout>\n", argv[0]);
        return 1;
    }

    const char *filein_name = argv[1];
    const char *fileout_name = argv[2];

    revcat(filein_name, fileout_name);

    printf("Le fichier a été inversé avec succès.\n");

    return 0;
}
