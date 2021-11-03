//David Seijas Pérez
//Jorge del Valle Vázquez

    
public class miThread extends Thread
{
    protected int N;  //numero de incrementos/decrementos
    protected int id;
    protected Entero num;

    public miThread(int N, int id, Entero num)
    {
        this.N = N;
        this.id = id;
        this.num = num;
    }
}