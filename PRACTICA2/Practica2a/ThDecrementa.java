//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2a;

public class ThDecrementa extends Thread 
{
    private int N;
    private Entero num;
    private MiLock lock;

    public ThDecrementa (int N, Entero num, MiLock lock)
    {
        this.N = N;
        this.num = num;
        this.lock = lock;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            lock.lock2();

            num.decrementa();
            System.out.println("Hilo " + this.getId() + " resta. Valor de num: " + num.getN());
                    
            lock.unlock2();
        }
    }
}
