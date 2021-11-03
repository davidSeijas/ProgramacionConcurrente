//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3b;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread
{
    private int N; //número de iteraciones
    private int id;
    private MiAlmacen buff;
    private Semaphore semEmpty;
    private Semaphore semFull;

    public Consumidor (int N, int id, MiAlmacen buff, Semaphore semEmpty, Semaphore semFull)
    {
        this.N = N;
        this.id = id;
        this.buff = buff;
        this.semEmpty = semEmpty;
        this.semFull = semFull;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            try{
                semFull.acquire();
            }catch (InterruptedException e) {
                System.out.println("Error al coger el semáforo en hilo consumidor: " + this.id);
                e.printStackTrace();
            }

            Producto producto = buff.extraer();

            System.out.println("Hilo (consumidor) " + this.id + ": se extrae el producto " + producto.getId());
                    
            semEmpty.release();
        }
    }
}
