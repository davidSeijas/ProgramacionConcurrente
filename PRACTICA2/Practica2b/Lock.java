//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;

public abstract class Lock 
{
    protected int N;  //numero de procesos

    public Lock(int N)
    {
        this.N = N;
    }

    public abstract void takeLock(int i);
    public abstract void releaseLock(int i);
}