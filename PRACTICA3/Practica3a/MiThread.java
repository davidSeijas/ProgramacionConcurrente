//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3a;

import java.util.concurrent.Semaphore;
    
public class MiThread extends Thread
{
    protected int N;  //numero de incrementos/decrementos
    protected int id;
    protected Entero num;
    protected Semaphore sem;

    public MiThread(int N, int id, Entero num, Semaphore sem)
    {
        this.N = N;
        this.id = id;
        this.num = num;
        this.sem = sem;
    }
}