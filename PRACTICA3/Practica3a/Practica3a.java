//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3a;

import java.util.concurrent.Semaphore;

public class Practica3a
{
    public static void main(String[] args) 
    {
        int N = 25;
        int M = 25;

	    Thread threadsI[], threadsD[];
        threadsI = new Thread[M];  
        threadsD = new Thread[M];  
        Entero n = new Entero(0);
        Semaphore sem = new Semaphore(1);

		for (int i = 0;  i < M; ++i)
        {
            threadsI[i] = new ThIncrementa(N, i, n, sem); 
            threadsI[i].start();
            threadsD[i] = new ThDecrementa(N, i+M, n, sem);
            threadsD[i].start();
        }

        try{
            for (int i = 0;  i < M; ++i){
                threadsI[i].join();
                threadsD[i].join();
            }
        }catch (InterruptedException e) {
			e.printStackTrace();
        }
		
		System.out.println("Han terminado todos los procesos. Valor final de n: " + n.getN());
	}
}