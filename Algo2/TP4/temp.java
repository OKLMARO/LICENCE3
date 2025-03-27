import java.util.ArrayList;

enum Color {
        Blanc,
        Gris,
        Noir
}

class Arrete {
    double poids;
    int cible;
    Arrete(double poids, int cible){
        this.poids=poids;
        this.cible=cible;
    }
}

class Sommet{
    ArrayList<Arrete> voisins;
    Color couleur;
    int debut,fin,pi,id;
    double distance;

    Sommet(){
        couleur = Color.Blanc;
        debut = Integer.MAX_VALUE;
        voisins = new ArrayList<>();
    }

}

public class Graph {
    private
        ArrayList<Sommet> sommets;

    public Graph(){
        sommets = new ArrayList<>();
    }
}
