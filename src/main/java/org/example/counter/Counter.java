package org.example.counter;

public class Counter implements Runnable{
    private int count =0;

    public void increment(){
        count++;
    }
    public void decrement(){
        count--;
    }
    public int  getValue(){
        return count;
    }
    /*
     상태를 유지(stateful)하게 설계하면 안됨
     Thread safety 하지 않음

    */
    @Override
    public void run() {
        synchronized (this){
            this.increment();
            System.out.println("Value for Thread After increment"+Thread.currentThread().getName()+" "+this.getValue()); //1
            this.decrement();
            System.out.println("Value for Thread at last"+Thread.currentThread().getName()+" "+this.getValue()); //2
        }


    }
}
