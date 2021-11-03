//David Seijas Pérez
//Jorge del Valle Vázquez


public class Productor extends Thread
{
    private int N; //número de iteraciones
    private int id;
    private MiAlmacen buff;

    public Productor (int N, int id, MiAlmacen buff)
    {
        this.N = N;
        this.id = id;
        this.buff = buff;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            String idProducto = "P-" + this.id + " It-" + i+1;
            Producto producto = new Producto(idProducto);
            //System.out.println("Hilo (productor) " + this.id + ": se almacena el producto " + producto.getId()); //cuando esto sale por pantalla igual no ha alamacenado nada todavia

            buff.almacenar(producto);
        }
    }
}
