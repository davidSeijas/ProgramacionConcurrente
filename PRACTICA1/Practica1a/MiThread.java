//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;


public class MiThread extends Thread 
{
    private int T;

    public MiThread(int T)
    {
        this.T = T;
    }
	
	public void run() 
    {
		System.out.println("Hilo " + this.getId() + " ejecutándose");
        
		try {
            System.out.println("Hilo " + this.getId() + " se duerme");
			Thread.sleep(T);
		} catch (InterruptedException e) {
			e.printStackTrace();
        }

        System.out.println("Hilo " + this.getId() + " se despierta");
    }
}


