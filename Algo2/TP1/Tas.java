import java.util.ArrayList;

public class Tas {

    public ArrayList<Integer> t;
    public int n;

    // Question n°3
    public Tas(ArrayList<Integer> t){
        this.t = t;
        this.n = t.size();
    }
    
    // Question n°1
    public Tas(){
        this.t = new ArrayList<Integer>();
        this.n = 0;
    }

    // Question n°2
    public static int getFilsGauche(int i){
        return 2 * i + 1;
    }

    public static int getFilsDroit(int i){
        return 2 * i + 2;
    }

    public static int getParent(int i){
        return (i - 1) / 2;
    }

    // Question n°3
    public boolean estVide(){
        if(this.t.size() == 0){
            return true;
        }
        return false;
    }

    // Question n°4
    @Override
    public String toString(){
        return toString(0);
    }

    public String toString(int i){
        if(this.n - 1 < i){
            return "";
        }
        else{
            return "[" + t.get(i) + toString(getFilsGauche(i)) + toString(getFilsDroit(i)) + "]";
        }
    }

    // Question n°5 (Cette fonction est linéaire)
    public boolean testTas(){
        for (int index = 0; index < this.n / 2; index++) {
            if (t.get(index) < t.get(getFilsGauche(index))) {
                return false;
            }

            if (getFilsDroit(index) < n) {
                if (t.get(index) < t.get(getFilsDroit(index))) {
                    return false;
                }
            }
        }
        return true;
    }

    // Question n°6
    public void entasser(int index){
        int max;
        int temp;
        if (getFilsGauche(index) <= n - 1 && t.get(getFilsGauche(index)) > t.get(index)) {
            max = getFilsGauche(index);
        }
        else{
            max = getFilsDroit(index);
        }

        if (getFilsDroit(index) <= n - 1 && t.get(getFilsDroit(index)) > t.get(max)) {
            max = getFilsDroit(index);
        }

        if (max != getFilsDroit(index)) {
            temp = t.get(index);
            t.set(index, t.get(max));
            t.set(max, temp);
            entasser(max);
        }
    }
}
