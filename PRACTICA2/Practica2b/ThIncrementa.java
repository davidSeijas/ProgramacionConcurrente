//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;

public class ThIncrementa extends MiThread 
{
    /*
    private int N;  //numero de incrementos
    private int id;
    private Entero num;
    private LockRompeEmpate rompeEmpate;
    private LockTicket ticket;
    private LockBakery bakery;
    */

    public ThIncrementa (int N, int id, Entero num, Lock miLock)
    {
        /*
        this.N = N;
        this.id = id;
        this.num = num;
        this.rompeEmpate = rompeEmpate;
        this.ticket = ticket;
        this.bakery = bakery;
        */
        super(N, id, num, miLock);
    }
	
	public void run() 
    {
        for(int i = 0; i < N; ++i)
        {
            miLock.takeLock(this.id);

            num.incrementa();
            //System.out.println("Hilo " + this.id + " suma. Valor de num: " + num.getN());
                    
            miLock.releaseLock(this.id);
        }
    }
}