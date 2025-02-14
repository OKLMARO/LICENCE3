package AVL;

class Noeud {
    int v;
    Noeud fg;
    Noeud fd;
  
    Noeud(Noeud fg,int v,Noeud fd){
      this.fg=fg;
      this.v=v;
      this.fd=fd;
    }
  
    public String toString(){
      String sb = "[";
      if (fg!=null)sb += fg.toString();
      sb += v;
      if (fd!=null)sb += fd.toString();
      return sb + "]";
    }
  }