//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2a;


public class Practica2a 
{
    public static void main(String[] args) 
    {
        int N = 5;

        Thread threadI = new Thread();
        Thread threadD = new Thread();
        Entero n = new Entero(0);
        MiLock lock = new MiLock(false, false, 1);

	threadI = new ThIncrementa(N, n, lock);
        threadI.start();
        threadD = new ThDecrementa(N, n, lock);
        threadD.start();
        

        try{
            threadI.join();
            threadD.join();
        }catch (InterruptedException e) {
			e.printStackTrace();
        }
		
		System.out.println("Han terminado todos los procesos. Valor final de n: " + n.getN());
	}
}