import static java.lang.Thread.sleep;

public class exo1 {
    public static void main(String[] args) {
        Data d = new Data();
        Producteur p = new Producteur(d);

        Consommateur c1 = new Consommateur(1, d);
        Consommateur c2 = new Consommateur(2, d);
        Consommateur c3 = new Consommateur(3, d);
        Consommateur c4 = new Consommateur(4, d);

        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c1);
        Thread t3 = new Thread(c2);
        Thread t4 = new Thread(c3);
        Thread t5 = new Thread(c4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}

class Producteur implements Runnable {
    final Data data;

    public Producteur(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            if (data.available) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                synchronized (data) {
                    data.available = true;
                    System.out.println("Producteur produit");
                    notify();
                }
            }
        }

    }
}

class Consommateur implements Runnable {
    int id;
    final Data data;

    public Consommateur(int id, Data data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (data) {
                try {
                    wait();
                    data.available = false;
                    System.out.println("Consommateur " + id + " consomme");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}

class Data {
    boolean available = false;
}