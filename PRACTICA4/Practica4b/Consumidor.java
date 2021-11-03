//David Seijas Pérez
//Jorge del Valle Vázquez


public class Consumidor extends Thread
{
    private int N; //número de iteraciones
    private int id;
    private MiAlmacen buff;

    public Consumidor (int N, int id, MiAlmacen buff)
    {
        this.N = N;
        this.id = id;
        this.buff = buff;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            Producto producto = buff.extraer();
            //System.out.println("Hilo (consumidor) " + this.id + ": se extrae el producto " + producto.getId());
        }
    }
}
