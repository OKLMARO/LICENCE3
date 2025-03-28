class Noeud {
    int v;
    Noeud fg;
    Noeud fd;
    int hauteur;

    Noeud(Noeud fg,int v,Noeud fd){
        this.fg=fg;
        this.v=v;
        this.fd=fd;
        hauteur = 0;
    }

    public boolean testABR(int min, int max) {
        if (v < min || v > max) return false;
        if (fg != null && fg.v >= v) return false; 
        if (fd != null && fd.v <= v) return false;
        return (fg == null || fg.testABR(min, v - 1)) && (fd == null || fd.testABR(v + 1, max));
    }

    public boolean membre(int elm){
        if(elm == v) return true;
        if(fg == null && fd == null) return false;
        if(fg != null && fd != null){
            if(elm < v) return fg.membre(elm);
            if(elm > v) return fd.membre(elm);
        }
        else if(fg != null) {
            if(elm < v) return fg.membre(elm);
            else return false;
        }
        if(elm > v) return fd.membre(elm);
        return false;

    }


    public String toString(){
        String sb = "[";
        if (fg!=null)sb += fg.toString();
        sb += v;
        if (fd!=null)sb += fd.toString();
        return sb + "]";
    }

    public int  maj() {
    	  hauteur=1;
    	  if(fg != null) {
    		  hauteur = 1 + fg.maj();
    	  }
    	  if(fd != null) {
    		  hauteur = Math.max(hauteur, (1+fd.maj()));
    	  }
    	  return hauteur;
      }

    public int  testAVL(){
      int gauche=0;
      int  droit =0;
        if (fg != null ){
          gauche = fg.testAVL();
        }else if (fd !=null){
          droit =fd.testAVL();
        }
        if (Math.abs(gauche-droit)>1){
          return -1;
        }
        return 1 + Math.max(gauche, droit);
        
    }
    public void rotationGauche ( ) {
        int aval = v ;
        Noeud t1 = fg ;
        Noeud t2 = fd.fg ;
        v = fd.v ;
        fg = fd ;
        fd = fd.fd ;
        fg.v = aval ;
        fg.fg = t1 ;
        fg.fd = t2 ;
    }
    public void rotationDroite ( ) {
        int cval = v ;
        Noeud t4 = fd ;
        Noeud t3 = fg.fd ;
        v = fg.v ;
        fd= fg ;
        fg= fg.fg ;
        fd.v= cval ;
        fd.fg = t3 ;
        fd.fd = t4 ;
    }
    public int difference_étages() {
	  int b = 0;
	  
	  if(fg != null) {
		  b = -(fg.hauteur);
	  }
	  if(fd != null) {
		  b = fd.hauteur + b;
	  }
	  return b;
  }
    public boolean equilibre() {
		if(difference_étages() > -2 && difference_étages() < 2) return true;
		return rotations();
	}
	
	public boolean rotations() {
		
		if(difference_étages() > 1) {
			if(fd.difference_étages() < 0) {
				fd.rotationDroite();
				rotationGauche();
			}
			else rotationGauche();
		}else {
			if(fg.difference_étages() > 0) {
				fg.rotationGauche();
				rotationDroite();
			}
			else rotationDroite();
		}
		maj();
		return true;
	}
}

class Arbre {
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
        if(racine==null)return true;
        return racine.testABR(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void inser(int value) {
		if(racine == null) {
			racine = new Noeud(null, value, null);
		}
		else aux(racine, value);
	}

	protected Noeud aux(Noeud n, int value) {
	  if(n == null) {
		  return new Noeud(null, value, null);
	  }else {
		  if(value <= n.v) n.fg = aux(n.fg, value);
		  else n.fd = aux(n.fd, value);
	  }
	  return n;
	}

    public boolean membre(int elm){
        if(racine== null) return false;
        return racine.membre(elm);
    }
    public boolean testAVL(){
        if (racine ==null ) return true;
        racine.maj();
        if (racine.testAVL()==-1) return false;
        else return true && testABR();
      }     
}