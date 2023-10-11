public class Val implements Valeur{

    private final int value;

    public Val(int value){
        this.value = value;
    }

    public int readValue(Machine m){
        return this.value;
    }
    
}
