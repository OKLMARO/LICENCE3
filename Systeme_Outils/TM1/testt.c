#include<stdio.h>
#include<unistd.h>

int main(){
  pid_t pid;
  printf("Bonjour de %d\n", getpid());
  
  
  pid = fork();
    printf("Valeur de retour du fork : %d\n", pid);
        printf("Je suis le processus de PID : %d\n", getpid());

    if 
    
}


