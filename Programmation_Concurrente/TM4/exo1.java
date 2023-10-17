import java.util.Random;

import static java.lang.Thread.sleep;

public class exo1 {
    public static void main(String[] args) {
        Flag flag = new Flag();

        Producteur p = new Producteur(flag);
        Consommateur c1 = new Consommateur("1", flag);
        Consommateur c2 = new Consommateur("2", flag);
        Consommateur c3 = new Consommateur("3", flag);
        Consommateur c4 = new Consommateur("4", flag);

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
    Flag flag;

    public Producteur(Flag flag) {
        this.flag = flag;
        flag.num = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (flag) {
                if (!flag.flag) {
                    flag.num++;
                    System.out.println("Production num : " + flag.num);
                    flag.flag = true;
                    flag.notify();
                }
            }
        }
    }
}

class Consommateur implements Runnable {
    String name;
    Flag flag;

    public Consommateur(String name, Flag flag) {
        this.name = name;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (flag) {
                try {
                    flag.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (flag.flag) {
                    System.out.println(name + " Consome : " + flag.num);
                    flag.flag = false;
                }

            }

        }

    }
}

class Flag {
    boolean flag = false;
    int num;
}