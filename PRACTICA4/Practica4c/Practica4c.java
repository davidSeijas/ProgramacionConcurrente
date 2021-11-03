//David Seijas Pérez
//Jorge del Valle Vázquez


public class Practica4c
{
    public static void main(String[] args) 
    {
        int N = 10; //número de iteraciones 
        int M = 15; //número de productores
        int K = 15; //número de consumidores
        int n = 25; //capacidad buffer
        //int iterP = K; 
        //int iterC = M;

	    Thread threadsProd[], threadsCons[];
        threadsProd = new Thread[M];  
        threadsCons = new Thread[K];  
        MiAlmacen buff = new MiAlmacen(n);

		for (int i = 0;  i < M; ++i)
        {
            threadsProd[i] = new Productor(N, i+1, buff, n); 
            threadsProd[i].start();
        }
        for(int i = 0; i < K; ++i){
            
            threadsCons[i] = new Consumidor(N, i+M+1, buff, n);
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