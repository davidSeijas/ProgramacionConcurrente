//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;

public class ThDecrementa extends Thread 
{
    private int N;
    private Entero num;

    public ThDecrementa (int N, Entero num)
    {
        this.N = N;
        this.num = num;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            num.decrementa();
        }
        System.out.println("Hilo " + this.getId() + " resta. Valor de num: " + num.getN());
    }
}
