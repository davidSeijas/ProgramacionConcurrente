//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2a;

public class MiLock {
    volatile boolean in1;
    volatile boolean in2;
    volatile int last;

    public MiLock (boolean in1, boolean in2, int last){
        this.in1 = in1;
        this.in2 = in2; 
        this.last = last;
    }

    public void lock1(){
        in1 = true;
        last = 1;
        while(in2 && last == 1);
    }

    public void lock2(){
        in2 = true;
        last = 2;
        while(in1 && last == 2);
    }

    public void unlock1(){
        in1 = false;
    }

    public void unlock2(){
        in2 = false;
    }
}
