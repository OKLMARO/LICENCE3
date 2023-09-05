public class question1 extends Thread{
    @Override
    public void run(){
        for(int i = 0;i<10;i++){
            System.out.print(Thread.currentThread().getId() + "1" + i);
            System.out.print(Thread.currentThread().getId() + "2" + i);
            System.out.print(Thread.currentThread().getId() + "3" + i);
            System.out.print(Thread.currentThread().getId() + "4" + i);
            System.out.print(Thread.currentThread().getId() + "5" + i);
            System.out.print(Thread.currentThread().getId() + "6" + i);
            System.out.print(Thread.currentThread().getId() + "7" + i);
            System.out.print(Thread.currentThread().getId() + "8" + i);
            System.out.print(Thread.currentThread().getId() + "9" + i);
            System.out.print(Thread.currentThread().getId() + "10" + i);
        }
    }
}