public class MensajeConfAñadirPeli extends Mensaje{
    Pelicula pelicula;

    public MensajeConfAñadirPeli(String origen, String destino, Pelicula pelicula)
    {
        super(11, origen, destino);
        this.pelicula = pelicula;
    }

    public Pelicula getPelicula(){
        return pelicula;
    }
}
