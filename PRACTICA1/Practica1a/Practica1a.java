//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;
import java.util.Random;


public class Practica1a 
{
    public static void main(String[] args) 
    {
        int N = 10;
        Random rand = new Random();

        Thread threads[];
        threads = new Thread[N];

        //ArrayList<Thread> threads = new ArrayList<>()

		for (int i = 0;  i < N; ++i)
        {
            int T = rand.nextInt(2000) + 1;

            threads[i] = new MiThread(T);
            threads[i].start();

            //th = new myThread(T);
            //threads.add(th)
            //threads.get(i).start();
        }

        try{
            for (int i = 0;  i < N; ++i)
            {
                threads[i].join();
                //threads.get(i).join();
            }
        }catch (InterruptedException e) {
			e.printStackTrace();
        }
		
		System.out.println("Han terminado todos los procesos");
	}
}