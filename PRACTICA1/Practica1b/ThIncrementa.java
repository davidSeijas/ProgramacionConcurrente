//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;

public class ThIncrementa extends Thread 
{
    private int N;
    private Entero num;

    public ThIncrementa (int N, Entero num)
    {
        this.N = N;
        this.num = num;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            num.incrementa();
        }
        System.out.println("Hilo " + this.getId() + " suma. Valor de num: " + num.getN());
    }
}
