//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica3b;

public class MiAlmacen implements Almacen
{
    private Producto producto;

    public void almacenar(Producto producto)
    {
        this.producto = producto;
    }

    public Producto extraer()
    {
        return this.producto;
    }
}
