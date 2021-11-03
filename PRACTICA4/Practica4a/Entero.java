//David Seijas Pérez
//Jorge del Valle Vázquez


public class Entero { //hace de monitor
    private int n;

    public Entero (int n)
    {
        this.n = n;
    } 

    synchronized void incrementa()
    {
        ++n;
    }

    synchronized void decrementa()
    {
        --n;
    }

    synchronized int getN()
    {
        return this.n;
    }
}