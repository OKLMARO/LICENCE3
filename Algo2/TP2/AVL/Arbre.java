package AVL;

class Arbre {
    Noeud racine;
  
    public Arbre(){ racine=null;}
    public Arbre(Arbre ag, int v, Arbre ad) {
      racine = new Noeud (ag.racine, v, ad.racine);
    }
    public String to_string() {
      if (racine == null) { return ""; }
      else { return racine.toString(); }
    }
}