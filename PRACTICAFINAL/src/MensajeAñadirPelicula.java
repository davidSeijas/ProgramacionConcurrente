public class MensajeAñadirPelicula extends Mensaje{
    private Pelicula pelicula;

    public MensajeAñadirPelicula(String usuario, String destino, Pelicula pelicula)
    {
        super(10, usuario, destino);
        this.pelicula = pelicula;
    }

    public Pelicula getPelicula(){
        return pelicula;
    }
}
