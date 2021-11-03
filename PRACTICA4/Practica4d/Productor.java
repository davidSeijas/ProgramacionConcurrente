//David Seijas Pérez
//Jorge del Valle Vázquez

import java.util.ArrayList;
import java.util.Random;

public class Productor extends Thread
{
    private int N; //número de iteraciones
    private int id;
    private MiAlmacen buff;
    private int tam; //tamaño del buffer

    public Productor (int N, int id, MiAlmacen buff, int tam)
    {
        this.N = N;
        this.id = id;
        this.buff = buff;
        this.tam = tam;
    }
	
	public void run() 
    {
		for(int i = 0; i < N; ++i)
        {
            Random r = new Random();
            int num = r.nextInt(tam)+1;    //nº de productos que quiere almacenar el productor (no dejamos que sea 0)

            ArrayList<Producto> productos = new ArrayList<Producto>();
            for(int j = 0; j < num; ++j){
                String idProducto = "P-" + this.id + " It-" + i+1 + " Nº-" + j;
                productos.add(new Producto(idProducto));
            }

            buff.almacenar(productos);
        }
    }
}
