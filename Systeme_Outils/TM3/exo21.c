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