package practise;

public class OddEvenApp {

    //boolean evenFlag=true;
    private void printEven() throws InterruptedException {

        synchronized (this) {
            for(int i =0 ;i< 100 ;  i=i+2){
                System.out.println("Even " +  i);
               // System.out.println("waiting " +  i);
                notify();
             //   evenFlag=false;
                wait();
              /*  while(!evenFlag) {
                   wait();
                }*/
               // System.out.println("even thread resumed");


            }
            notify(); // to notify last odd iteration of thread to finished
        }
        System.out.println("even thread completed");
    }
    private void printOdd() throws InterruptedException {

        synchronized (this) {
            for(int i =1 ;i< 100 ; i=i+2){

                System.out.println("Odd " +  i);
               // evenFlag=true;
                notify();
                wait();

              /*  while(evenFlag) {
                    wait();
                }*/
               // System.out.println("even thread resumed");

            }

        }
        System.out.println("odd thread completed");
    }


    public static void main(String[] args) throws InterruptedException {
        OddEvenApp app= new OddEvenApp();

        Thread even = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.printEven();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread odd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.printOdd();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        even.start();
        odd.start();

        even.join();
        odd.join();

        System.out.println(" completed");


    }



}
