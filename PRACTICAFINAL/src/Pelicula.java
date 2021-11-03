import java.io.Serializable;

public class Pelicula implements Serializable{ 
    private String name;
    private int duracion;
    private int valoracion;

    public Pelicula(String name, int duracion, int valoracion)
    {
        this.name = name;
        this.duracion = duracion;
        this.valoracion = valoracion;
    }

    public String getName()
    {
        return name;
    }

    public int getDuracion()
    {
        return duracion;
    }

    public int getValoracion()
    {
        return valoracion;
    }
}
