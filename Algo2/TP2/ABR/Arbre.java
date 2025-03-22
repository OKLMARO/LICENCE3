package ABR;

public class Arbre {
    Noeud racine;

    public Arbre(){ racine=null;}
    public Arbre(Arbre ag, int v, Arbre ad) {
      racine = new Noeud (ag.racine, v, ad.racine);
    }
    
    public String toString() {
      if (racine == null) { return ""; }
      else { return racine.toString(); }
    }

    public boolean testABR(){
      if (this.racine.v >= this.racine.fd.v && this.racine.v <= this.racine.fg.v) {
        return false;
      }

      return testABR(this.racine.fg.v, this.racine.v) && testABR(this.racine.v, this.racine.fd.v);
    }

    boolean testABR(int min, int max){

    }
}
