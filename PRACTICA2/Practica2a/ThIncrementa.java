//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2a;


public class ThIncrementa extends Thread 
{
    private int N;
    private Entero num;
    private MiLock lock;

    public ThIncrementa (int N, Entero num, MiLock lock)
    {
        this.N = N;
        this.num = num;
        this.lock = lock;
    }

    public void run() 
    {
        for(int i = 0; i < N; ++i)
        {
            lock.lock1();

            num.incrementa();
            System.out.println("Hilo " + this.getId() + " suma. Valor de num: " + num.getN());
                    
            lock.unlock1();
        }
    }
}
