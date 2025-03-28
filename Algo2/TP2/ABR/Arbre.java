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

	public boolean testABR(int min, int max)
	{
		if (this.v > max || this.v < min)
			return false;
		if (this.fd != null) {
			if (this.fd.v < this.v)
				return false;
		}
		if (this.fg != null) {
			if (this.fg.v > this.v)
				return false;
		}
		return ((fg == null || this.fg.testABR(min, this.v)) && (fd == null || this.fd.testABR(this.v, max)));
	}
}

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
	public boolean testABR()
	{
		if (this.racine == null || (this.racine.fd == null && this.racine.fg == null))
			return true;
		return this.racine.testABR(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public void	inser(int value)
	{
		boolean fin;
		Noeud	temp;

		fin = false;
		temp = racine;
		while (fin) 
		{
			if (temp.v > value)
				temp = temp.fg;
			else if (temp.v < value)
				temp = temp.fd;
			if (temp) {
				
			}
		}
	}
}
