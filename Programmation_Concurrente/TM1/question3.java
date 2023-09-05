class Valeur{
    int x;
}


public class question3 extends Thread{
    
    Valeur valeur;

    public question3(Valeur valeur){
        this.valeur = valeur;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            valeur.x = valeur.x + 1;
            System.out.println(Thread.currentThread().getId() + " " + valeur.x);
        }    
    }

    public static void main(String[] args) {
        Valeur v = new Valeur();
        Thread t1 = new question3(v);
        Thread t2 = new question3(v);
        
        t1.start();
        t2.start();
        
        System.out.println(v.x);
    }
}
