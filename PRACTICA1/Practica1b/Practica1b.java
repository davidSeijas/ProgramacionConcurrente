//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;

public class Practica1b 
{
    public static void main(String[] args) 
    {
        int N = 5;
        int M = 5;

        Thread threads[];
        threads = new Thread[2*M];
        Entero n = new Entero(0);

		for (int i = 0;  i < M; ++i)
        {
            threads[i] = new ThIncrementa(N, n);
            threads[i].start();
        }
        for (int i = M;  i < 2*M; ++i)
        {
            threads[i] = new ThDecrementa(N, n);
            threads[i].start();
        }

        try{
            for (int i = 0;  i < 2*M; ++i){
                threads[i].join();
            }
        }catch (InterruptedException e) {
			e.printStackTrace();
        }
		
		System.out.println("Han terminado todos los procesos. Valor final de n: " + n.getN());
	}
}