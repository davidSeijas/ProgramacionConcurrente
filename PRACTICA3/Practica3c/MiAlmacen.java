//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3c;

public class MiAlmacen implements Almacen
{
    private int tamMax;
    private Producto producto[];
    private volatile int ini;
    private volatile int fin;

    public MiAlmacen(int n)
    {   
        this.tamMax = n;
        this.producto = new Producto[tamMax];
        this.ini = 0;
        this.fin = 0;
    }

    public void almacenar(Producto producto)
    {
        this.producto[fin] = producto;
        fin = (fin + 1) % tamMax;
    }

    public Producto extraer()
    {   
        Producto producto = this.producto[ini];
        ini = (ini + 1) % tamMax;
        return producto;
    }
}
