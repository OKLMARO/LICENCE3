import java.util.ArrayList;

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
		if (this.sommets.size() <= i || this.sommets.size() <= j) {
			throw new IllegalArgumentException();
		}
		for (int k = 0; k < this.sommets.get(i).voisins.size(); k++) {
			if (this.sommets.get(i).voisins.get(k) == j) {
				return ;
			}
		}
		this.sommets.get(i).voisins.add(j);
	}

	@Override
	public String	toString()
	{
		String res = "digraph{\n";

		for (int i = 0; i < this.sommets.size(); i++) {
			res = res + this.sommets.get(i).id + ";\n";
		}
		for (int i = 0; i < this.sommets.size(); i++) {
			for (int j = 0; j < this.sommets.get(i).voisins.size(); j++) {
				res = res +this.sommets.get(i).id + "->" + this.sommets.get(i).voisins.get(j) + ";\n";
			}
		}
		res = res + "}";
		return res;
	}

	public boolean	existeArete(int i, int j)
	{
		if (this.sommets.size() <= i || this.sommets.size() <= j) {
			throw new IllegalArgumentException();
		}
		for (int k = 0; k < this.sommets.get(i).voisins.size(); k++) {
			if (this.sommets.get(i).voisins.get(k) == j) {
				return true;
			}
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
		for (Sommet sommet : sommets) {
			count = count + sommet.voisins.size();
		}
		return count;
	}

	public void	parcoursProf()
	{
		
	}
}
