public class MensajeConfBorrarPeli extends Mensaje{
    private String pelicula;
    
    public MensajeConfBorrarPeli(String origen, String destino, String pelicula)
    {
        super(13, origen, destino);
        this.pelicula = pelicula;
    }

    public String getPelicula(){
        return pelicula;
    }
}
