/*Écrivez un programme qui fait l’opposé de cat : le programme doit recopier ce qui
est écrit sur l’entrée standard et l’écrire dans un fichier passé en argument. Vous l’implémenterez à
l’aide des fonctions open, read et write, close. L’utilisateur signifie la fin de l’entrée avec CTRL+D.
*/

#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc,char *argv[]){
    int fd = open(argv[1], O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (fd == -1) {
        printf("ERROR");
        exit(EXIT_FAILURE);
    }

    char buffer[1024];

    ssize_t Read = read(STDIN_FILENO, buffer, sizeof(buffer));
    write(fd, buffer, Read);
    printf("\n");
    close(fd);
    return 0;
}