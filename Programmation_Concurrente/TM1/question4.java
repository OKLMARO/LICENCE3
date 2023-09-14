class question4a extends Thread{
    Valeur valeur;

    public question4a(Valeur valeur){
        this.valeur = valeur;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            valeur.x = valeur.x + 1;
            System.out.println(Thread.currentThread().getId() + " " + valeur.x);
        }    
    }
}

class question4b extends Thread{
    Valeur valeur;

    public question4b(Valeur valeur){
        this.valeur = valeur;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            valeur.x = valeur.x - 1;
            System.out.println(Thread.currentThread().getId() + " " + valeur.x);
        }    
    }
}

public class question4{
    public static void main(String[] args) {
        Valeur v = new Valeur();
        Thread t1 = new question4a(v);
        Thread t2 = new question4b(v);
        
        t1.start();
        t2.start();
        
        System.out.println(v.x);
    }
}
