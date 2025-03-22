import java.util.ArrayList;

enum Color {
	Blanc,
	Gris,
	Noir
}

class	Sommet
{
	ArrayList<Integer>	voisins;
	int					id;
	Color				couleur;
	int					debut;
	int					fin;
	int					pi;

	Sommet(int id)
	{
		this.voisins = new ArrayList<Integer>();
		this.id = id;
	}
}

public class	Graph
{
	ArrayList<Sommet>	sommets;

	public	Graph()
	{
		this.sommets = new ArrayList<Sommet>();
	}

	public void	ajoutSommet()
	{
		this.sommets.add(new Sommet(sommets.size()));
	}

	public void	ajoutArete(int i, int j)
	{
		if (this.sommets.size() <= i || this.sommets.size() <= j)
			throw new IllegalArgumentException();
		for (int k = 0; k < this.sommets.get(i).voisins.size(); k++) {
			if (this.sommets.get(i).voisins.get(k) == j)
				return ;
		}
		this.sommets.get(i).voisins.add(j);
	}

	@Override
	public String	toString()
	{
		String res = "digraph{\n";

		for (int i = 0; i < this.sommets.size(); i++)
			res = res + this.sommets.get(i).id + ";\n";
		for (int i = 0; i < this.sommets.size(); i++) {
			for (int j = 0; j < this.sommets.get(i).voisins.size(); j++)
				res = res +this.sommets.get(i).id + "->" + this.sommets.get(i).voisins.get(j) + ";\n";
		}
		res = res + "}";
		return res;
	}

	public boolean	existeArete(int i, int j)
	{
		if (this.sommets.size() <= i || this.sommets.size() <= j)
			throw new IllegalArgumentException();
		for (int k = 0; k < this.sommets.get(i).voisins.size(); k++) {
			if (this.sommets.get(i).voisins.get(k) == j)
				return true;
		}
		return false;
	}

	public int	getNbSommets()
	{
		return this.sommets.size();
	}

	public int	getNbAretes()
	{
		int	count;

		count = 0;
		for (Sommet sommet : sommets)
			count = count + sommet.voisins.size();
		return count;
	}

	/*public void explorer(Sommet s, int deb)
	{
		for (int i = 0; i < s.voisins.size(); i++) {
			if (this.sommets.get(s.voisins.get(i)).couleur == Color.Blanc)
			{
				s.couleur = Color.Gris;
				explorer(this.sommets.get(s.voisins.get(i)), deb + 1);
			}
			else if (this.sommets.get(s.voisins.get(i)).couleur == Color.Gris)
			{
				s.couleur = Color.Noir;
				s.fin = deb;
			}
		}
	}*/

	public int	visite(Graph g, Sommet s, int date)
	{
		date = date + 1;
		s.debut = date;
		s.couleur = Color.Gris;
		for (int i = 0; i < s.voisins.size(); i++) {
			Sommet voisins = g.sommets.get(s.voisins.get(i));
			if (voisins.couleur == Color.Blanc) 
			{
				voisins.pi = s.id;
				date = visite(g, voisins, date);
			}
		}
		s.couleur = Color.Noir;
		date = date + 1;
		s.fin = date;
		return (date);
	}

	public void	parcoursProf()
	{
		int	date;
		for (Sommet sommet : sommets)
		{
			sommet.couleur = Color.Blanc;
			sommet.debut = -1;
			sommet.debut = -1;
			sommet.pi = -1;
		}
		date = 0;
		for (Sommet sommet : sommets) {
			if (sommet.couleur == Color.Blanc)
				date = visite(this, sommet, date);
		}
	}

	public String toStringDates()
	{
		String res;

		res = "";
		for (Sommet sommet : sommets) {
			res = res + "sommet " + sommet.id + "[" + sommet.debut + ";" + sommet.fin + "]\n";
		}
		return (res);
	}
}
