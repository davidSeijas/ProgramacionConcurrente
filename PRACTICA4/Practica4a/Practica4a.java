//David Seijas Pérez
//Jorge del Valle Vázquez


public class Practica4a
{
    public static void main(String[] args) 
    {
        int N = 20;
        int M = 15;

	    Thread threadsI[], threadsD[];
        threadsI = new Thread[M];  
        threadsD = new Thread[M];  
        Entero n = new Entero(0);

		for (int i = 0;  i < M; ++i)
        {
            threadsI[i] = new ThIncrementa(N, i, n); 
            threadsI[i].start();
            threadsD[i] = new ThDecrementa(N, i+M, n);
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