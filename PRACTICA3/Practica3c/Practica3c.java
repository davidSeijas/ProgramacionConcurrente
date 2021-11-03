//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3c;

import java.util.concurrent.Semaphore;

public class Practica3c
{
    public static void main(String[] args) 
    {
        //int N = 50; //número de iteraciones (con esto si hay mas productores que consumidores peta)
        int M = 25; //número de productores
        int K = 20; //número de consumidores
        int n = 50; //capacidad buffer
        int iterP = K; //para que no falle si hay menos consumidores
        int iterC = M;

	    Thread threadsProd[], threadsCons[];
        threadsProd = new Thread[M];  
        threadsCons = new Thread[K];  
        Semaphore semEmpty = new Semaphore(n);
        Semaphore semFull = new Semaphore(0);
        Semaphore mutexP = new Semaphore(1);
        Semaphore mutexC = new Semaphore(1);
        MiAlmacen buff = new MiAlmacen(n);

		for (int i = 0;  i < M; ++i)
        {
            threadsProd[i] = new Productor(iterP, i+1, buff, semEmpty, semFull, mutexP); 
            threadsProd[i].start();
        }
        for(int i = 0; i < K; ++i){
            
            threadsCons[i] = new Consumidor(iterC, i+M+1, buff, semEmpty, semFull, mutexC);
            threadsCons[i].start();
        }

        try{
            for (int i = 0;  i < M; ++i){
                threadsProd[i].join();
            }
            for (int i = 0;  i < K; ++i){
                threadsCons[i].join();
            }
        }catch (InterruptedException e) {
			e.printStackTrace();
        }
	}
}