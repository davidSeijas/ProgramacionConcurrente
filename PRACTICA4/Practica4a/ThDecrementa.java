//David Seijas Pérez
//Jorge del Valle Vázquez


public class ThDecrementa extends miThread 
{
    public ThDecrementa (int N, int id, Entero num)
    {
        super(N, id, num);
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            num.decrementa();
            //System.out.println("Hilo " + this.id + " resta. Valor de num: " + num.getN());                  
        }
    }
}