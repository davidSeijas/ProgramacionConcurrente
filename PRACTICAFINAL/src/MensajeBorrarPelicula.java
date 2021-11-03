public class MensajeBorrarPelicula extends Mensaje{
    private String pelicula;

    public MensajeBorrarPelicula(String usuario, String destino, String pelicula)
    {
        super(12, usuario, destino);
        this.pelicula = pelicula;
    }

    public String getPelicula(){
        return pelicula;
    }
}
