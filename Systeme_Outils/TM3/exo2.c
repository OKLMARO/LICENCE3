/*void lecture (int block_size )
qui
— ouvre un fichier donné de grosse taille (par exemple, /bin/gcc) en lecture seule,
— réserve un buffer de block_size octets sur le tas, en utilisant malloc(3),
— lit le fichier par blocs de block_size caractères.*/

void lecture (int block_size ){
    FILE *f = fopen("/bin/gcc", "r");
    if(f == NULL){
        perror("Erreur fopen");
        exit(1);
    }

    char *buffer = malloc(block_size);
    if(buffer == NULL){
        perror("Erreur malloc");
        exit(1);
    }

    while(fread(buffer, block_size, 1, f) == 1){}

    fclose(f);
    free(buffer);
}

/*Faites en sorte que votre programme attende deux arguments n1 et n2 en ligne
de commande, puis
— crée un premier processus fils, qui exécute lecture(n1)
— crée un second processus fils, qui exécute lecture(n2)*/

void lecture (int block_size ){
    FILE *f = fopen("/bin/gcc", "r");
    if(f == NULL){
        perror("Erreur fopen");
        exit(1);
    }

    char *buffer = malloc(block_size);
    if(buffer == NULL){
        perror("Erreur malloc");
        exit(1);
    }

    while(fread(buffer, block_size, 1, f) == 1){}

    fclose(f);
    free(buffer);
}

int main(int argc, char *argv[]){
    if(argc != 3){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    pid_t pid1 = fork();

    if(pid1 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid1 == 0){
        lecture(atoi(argv[1]));
        exit(0);
    }

    pid_t pid2 = fork();

    if(pid2 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid2 == 0){
        lecture(atoi(argv[2]));
        exit(0);
    }

    wait(NULL);
    wait(NULL);

    return 0;
}
/*Modifiez votre programme de sorte qu’à la terminaison de chacun de ses deux fils,
le processus père affiche “Le premier fils a terminé sa lecture” (resp. “Le second fils a
terminé sa lecture”) sur la sortie standard. On pourra utiliser waitid(2).

Ce benchmark n’est pas tout à fait satisfaisant, dans la mesure où l’un des fils est créé avant
l’autre, et prend donc de l’avance dans sa lecture. Pour que les deux fils démarrent leur lecture en
même temps, on va les synchroniser à l’aide de signaux*/

void lecture (int block_size ){
    FILE *f = fopen("/bin/gcc", "r");
    if(f == NULL){
        perror("Erreur fopen");
        exit(1);
    }

    char *buffer = malloc(block_size);
    if(buffer == NULL){
        perror("Erreur malloc");
        exit(1);
    }

    while(fread(buffer, block_size, 1, f) == 1){}

    fclose(f);
    free(buffer);
}

void handler(int sig){
    printf("Le premier fils a terminé sa lecture\n");
}

void handler2(int sig){
    printf("Le second fils a terminé sa lecture\n");
}

int main(int argc, char *argv[]){
    if(argc != 3){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    struct sigaction sa;
    sa.sa_handler = handler;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = 0;

    if(sigaction(SIGUSR1, &sa, NULL) == -1){
        perror("Erreur sigaction");
        exit(1);
    }

    struct sigaction sa2;
    sa2.sa_handler = handler2;
    sigemptyset(&sa2.sa_mask);
    sa2.sa_flags = 0;

    if(sigaction(SIGUSR2, &sa2, NULL) == -1){
        perror("Erreur sigaction");
        exit(1);
    }

    pid_t pid1 = fork();

    if(pid1 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid1 == 0){
        lecture(atoi(argv[1]));
        kill(getppid(), SIGUSR1);
        exit(0);
    }

    pid_t pid2 = fork();

    if(pid2 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid2 == 0){
        lecture(atoi(argv[2]));
        kill(getppid(), SIGUSR2);
        exit(0);
    }

    sigset_t mask;
    sigemptyset(&mask);
    sigaddset(&mask, SIGUSR1);
    sigaddset(&mask, SIGUSR2);

    sigprocmask(SIG_BLOCK, &mask, NULL);

    sigsuspend(&mask);
    sigsuspend(&mask);

    return 0;
}

/*Modifiez lecture de sorte que chacun des deux fils stoppe son exécution juste
avant de commencer à lire le fichier, en s’envoyant un SIGSTOP.
On se souviendra que lorsqu’un fils est stoppé, son processus père reçoit un signal SIGCHLD.
Ainsi, le père sait que ses deux fils sont sur les starting blocks quand il a reçu deux tels signaux.*/

void lecture (int block_size ){
    FILE *f = fopen("/bin/gcc", "r");
    if(f == NULL){
        perror("Erreur fopen");
        exit(1);
    }

    char *buffer = malloc(block_size);
    if(buffer == NULL){
        perror("Erreur malloc");
        exit(1);
    }

    while(fread(buffer, block_size, 1, f) == 1){}

    fclose(f);
    free(buffer);
}

void handler(int sig){
    printf("Le premier fils a terminé sa lecture\n");
}

void handler2(int sig){
    printf("Le second fils a terminé sa lecture\n");
}

int main(int argc, char *argv[]){
    if(argc != 3){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    struct sigaction sa;
    sa.sa_handler = handler;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = 0;

    if(sigaction(SIGUSR1, &sa, NULL) == -1){
        perror("Erreur sigaction");
        exit(1);
    }

    struct sigaction sa2;
    sa2.sa_handler = handler2;
    sigemptyset(&sa2.sa_mask);
    sa2.sa_flags = 0;

    if(sigaction(SIGUSR2, &sa2, NULL) == -1){
        perror("Erreur sigaction");
        exit(1);
    }

    pid_t pid1 = fork();

    if(pid1 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid1 == 0){
        kill(getppid(), SIGSTOP);
        lecture(atoi(argv[1]));
        kill(getppid(), SIGUSR1);
        exit(0);
    }

    pid_t pid2 = fork();

    if(pid2 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid2 == 0){
        kill(getppid(), SIGSTOP);
        lecture(atoi(argv[2]));
        kill(getppid(), SIGUSR2);
        exit(0);
    }

    sigset_t mask;
    sigemptyset(&mask);
    sigaddset(&mask, SIGUSR1);
    sigaddset(&mask, SIGUSR2);

    sigprocmask(SIG_BLOCK, &mask, NULL);

    sigsuspend(&mask);
    sigsuspend(&mask);

    return 0;
}

/*Faites en sorte qu’une fois ses deux fils stoppés, le processus père leur envoie
un SIGCONT simultanément, pour qu’ils démarrent en même temps leur lecture. Pour s’assurer
qu’aucun fils n’ait l’avantage, on utilsera leur process group ID, cf. kill(2).*/

void lecture (int block_size ){
    FILE *f = fopen("/bin/gcc", "r");
    if(f == NULL){
        perror("Erreur fopen");
        exit(1);
    }

    char *buffer = malloc(block_size);
    if(buffer == NULL){
        perror("Erreur malloc");
        exit(1);
    }

    while(fread(buffer, block_size, 1, f) == 1){}

    fclose(f);
    free(buffer);
}

void handler(int sig){
    printf("Le premier fils a terminé sa lecture\n");
}

void handler2(int sig){
    printf("Le second fils a terminé sa lecture\n");
}

int main(int argc, char *argv[]){
    if(argc != 3){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    struct sigaction sa;
    sa.sa_handler = handler;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = 0;

    if(sigaction(SIGUSR1, &sa, NULL) == -1){
        perror("Erreur sigaction");
        exit(1);
    }

    struct sigaction sa2;
    sa2.sa_handler = handler2;
    sigemptyset(&sa2.sa_mask);
    sa2.sa_flags = 0;

    if(sigaction(SIGUSR2, &sa2, NULL) == -1){
        perror("Erreur sigaction");
        exit(1);
    }

    pid_t pid1 = fork();

    if(pid1 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid1 == 0){
        kill(getppid(), SIGSTOP);
        lecture(atoi(argv[1]));
        kill(getppid(), SIGUSR1);
        exit(0);
    }

    pid_t pid2 = fork();

    if(pid2 == -1){
        perror("Erreur fork");
        exit(1);
    }

    if(pid2 == 0){
        kill(getppid(), SIGSTOP);
        lecture(atoi(argv[2]));
        kill(getppid(), SIGUSR2);
        exit(0);
    }

    sigset_t mask;
    sigemptyset(&mask);
    sigaddset(&mask, SIGUSR1);
    sigaddset(&mask, SIGUSR2);

    sigprocmask(SIG_BLOCK, &mask, NULL);

    sigsuspend(&mask);
    sigsuspend(&mask);

    kill(-getpgid(pid1), SIGCONT);
    kill(-getpgid(pid2), SIGCONT);

    return 0;
}

