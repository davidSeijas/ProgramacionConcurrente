//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3b;

import java.util.concurrent.Semaphore;

public class Practica3b
{
    public static void main(String[] args) 
    {
        //int N = 50; //número de iteraciones (con esto si hay mas productores que consumidores peta)
        int M = 25; //número de productores
        int K = 20; //número de consumidores
        int iterP = K; //para que no falle si hay menos consumidores
        int iterC = M;

	    Thread threadsProd[], threadsCons[];
        threadsProd = new Thread[M];  
        threadsCons = new Thread[K];  
        Semaphore semEmpty = new Semaphore(1);
        Semaphore semFull = new Semaphore(0);
        MiAlmacen buff = new MiAlmacen();

		for (int i = 0;  i < M; ++i)
        {
            threadsProd[i] = new Productor(iterP, i+1, buff, semEmpty, semFull); 
            threadsProd[i].start();
        }
        for(int i = 0; i < K; ++i)
        {
            threadsCons[i] = new Consumidor(iterC, i+M+1, buff, semEmpty, semFull);
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