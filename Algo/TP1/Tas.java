import java.util.ArrayList;

public class Tas {
    ArrayList<Integer> tas;
    
    public Tas() {
        tas = new ArrayList<Integer>();
    }

    public Tas(ArrayList<Integer> tas) {
        this.tas = tas;
    }

    public static int getFilsGauche(int i) {
        return 2*i+1;
    }

    public static int getFilsDroit(int i) {
        return 2*i+2;
    }

    public static int getParent(int i) {
        return (i-1)/2;
    }

    public boolean estVide() {
        return tas.isEmpty();
    }

    public String toString(){
        return toString(0);
    }

    public String toString(int i) {
        if (i >= tas.size()){
            return "";
        }
        return "[" + tas.get(i) + toString(getFilsGauche(i)) + toString(getFilsDroit(i)) + "]";
    }

    public boolean testTas(){
        for (int i = 0; i < tas.size() / 2 - 1; i++) {
            if(tas.get(i) < tas.get(getFilsGauche(i)) || tas.get(i) < tas.get(getFilsDroit(i))){
                return false;
            }
        }
        return true;
    }

    public void inser(int value) {
        tas.add(value);
        int index = tas.size() - 1;
        while (index > 0 && tas.get(index) > tas.get(getParent(index))) {
            int temp = tas.get(index);
            tas.set(index, tas.get(getParent(index)));
            tas.set(getParent(index), temp);
            index = getParent(index);
        }
    }
}