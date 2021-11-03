//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;
    
public class MiThread extends Thread
{
    protected int N;  //numero de incrementos/decrementos
    protected int id;
    protected Entero num;
    protected Lock miLock;

    public MiThread(int N, int id, Entero num, Lock miLock)
    {
        this.N = N;
        this.id = id;
        this.num = num;
        this.miLock = miLock;
    }
}