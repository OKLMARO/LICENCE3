#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>

void cat(const char *filename) {
    int fd = open(filename, O_RDONLY);
    if (fd == -1) {
        printf("ERROR");
        exit(EXIT_FAILURE);
    }

    char buffer[1024];

    ssize_t Read= read(fd, buffer, sizeof(buffer));
    write(1, buffer, Read);
    printf("\n");
    close(fd);
}

int main(int argc, char *argv[]) {
    cat(argv[1]);
    return 0;
}