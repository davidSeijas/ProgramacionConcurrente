//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;


public class Practica2b
{
    public static void main(String[] args) 
    {
        int N = 20;
        int M = 15;

	    Thread threadsI[], threadsD[];
        threadsI = new Thread[M];  
        threadsD = new Thread[M];  
        Entero n = new Entero(0);
        LockRompeEmpate rompeEmpate = new LockRompeEmpate(2*M);
        LockTicket ticket = new LockTicket(2*M);
        LockBakery bakery = new LockBakery(2*M);

		for (int i = 0;  i < M; ++i)
        {
            threadsI[i] = new ThIncrementa(N, i, n, bakery); //pasamos el lock que queramos ejecutar en ese momento
            threadsI[i].start();
            threadsD[i] = new ThDecrementa(N, i+M, n, bakery);
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