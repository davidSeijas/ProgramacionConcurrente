//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;

public class Entero {
    volatile int n;

    public Entero (int n)
    {
        this.n = n;
    } 

    public void incrementa()
    {
        ++n;
    }

    public void decrementa()
    {
        --n;
    }

    public int getN()
    {
        return this.n;
    }
}