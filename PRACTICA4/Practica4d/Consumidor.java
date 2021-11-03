//David Seijas Pérez
//Jorge del Valle Vázquez

import java.util.ArrayList;
import java.util.Random;

public class Consumidor extends Thread
{
    private int N; //número de iteraciones
    private int id;
    private MiAlmacen buff;
    private int tam; //tamaño del buffer

    public Consumidor (int N, int id, MiAlmacen buff, int tam)
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
            int num = r.nextInt(tam)+1;    //nº de productos que quiere extraer el consumidor (no dejamos que sea 0)

            ArrayList<Producto> productos = buff.extraer(num);
        }
    }
}
