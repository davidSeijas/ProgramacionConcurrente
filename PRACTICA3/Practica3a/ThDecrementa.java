//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3a;

import java.util.concurrent.Semaphore;

public class ThDecrementa extends MiThread 
{
    /*
    private int N;  //numero de decrementos
    private int id;
    private Entero num;
    private Semaphore sem;
    */

    public ThDecrementa (int N, int id, Entero num, Semaphore sem)
    {
        /*
        this.N = N;
        this.id = id;
        this.num = num;
        this.sem = sem;
        */
        super(N, id, num, sem);
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            try{
                sem.acquire();
            }catch (InterruptedException e) {
                System.out.println("Error al coger el semáforo en hilo: " + this.id);
                e.printStackTrace();
            }

            num.decrementa();
            //System.out.println("Hilo " + this.id + " resta. Valor de num: " + num.getN());
                    
            sem.release();
        }
    }
}