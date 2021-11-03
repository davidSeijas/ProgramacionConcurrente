//David Seijas Pérez
//Jorge del Valle Vázquez


public class ThIncrementa extends miThread 
{
    public ThIncrementa (int N, int id, Entero num)
    {
        super(N, id, num);
    }
	
	public void run() 
    {
        for(int i = 0; i < N; ++i)
        {
            num.incrementa();
            //System.out.println("Hilo " + this.id + " suma. Valor de num: " + num.getN());            
        }
    }
}