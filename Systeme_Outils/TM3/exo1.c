#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
    if(argc < 3){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    for(int i = 2; i < argc; i = i + 1){
        pid_t pid = fork();

        if(pid == -1){
            perror("Erreur fork");
            exit(1);
        }

        if(pid == 0){
            execlp(argv[1], argv[1], argv[i]);
            perror("Erreur exec");
            exit(1);
        }
    }

    for(int i = 2; i < argc; i = i + 1){
        wait(NULL);
    }

    return 0;
}

/*Modifiez execpar pour qu’il prenne un argument supplémentaire entre prog et
fichier1 : le nombre maximum d’instances de prog à lancer en parallèle. Quand ce nombre est
atteint, execpar doit attendre qu’un des appels se termine avant d’en lancer un nouveau.
Écrivez un petit programme compte qui effectue un compte à rebours seconde par seconde
à partir d’un nombre passé en argument. Sur chaque ligne, compte affichera le nombre de secondes restantes ainsi que son pid (afin de l’identifier facilement en cas d’exécutions multiples).
Une commande telle que ./execpar ./compte 2 4 5 6 7 devrait alors vous permettre de tester
facilement votre nouveau execpar.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
    if(argc < 4){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    int nb_processus = atoi(argv[2]);

    for(int i = 3; i < argc; i = i + 1){
        pid_t pid = fork();

        if(pid == -1){
            perror("Erreur fork");
            exit(1);
        }

        if(pid == 0){
            execlp(argv[1], argv[1], argv[i]);
            perror("Erreur exec");
            exit(1);
        }

        if(i % nb_processus == 0){
            wait(NULL);
        }
    }

    for(int i = 3; i < argc; i = i + 1){
        wait(NULL);
    }

    return 0;
}

/*Modifiez execpar pour que, si une des instances de prog se termine anormalement
(sur un signal) alors il ne lance plus de nouvelles instances de prog. À la place, il attend que les
instances déjà lancées se terminent et quitte.
Qu’arrive-t-il aux instances déjà lancées de prog si execpar quitte sans attendre qu’elles se
terminent ?*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

int nb_processus = 0;

void handler(int sig){
    if(sig == SIGINT){
        printf("Signal SIGINT reçu\n");
        exit(0);
    }
}

int main(int argc, char *argv[]){
    if(argc < 4){
        perror("Nombre d'arguments incorrect");
        exit(1);
    }

    signal(SIGINT, handler);

    nb_processus = atoi(argv[2]);

    for(int i = 3; i < argc; i = i + 1){
        pid_t pid = fork();

        if(pid == -1){
            perror("Erreur fork");
            exit(1);
        }

        if(pid == 0){
            execlp(argv[1], argv[1], argv[i]);
            perror("Erreur exec");
            exit(1);
        }

        if(i % nb_processus == 0){
            wait(NULL);
        }
    }

    for(int i = 3; i < argc; i = i + 1){
        wait(NULL);
    }

    return 0;
}

