public class MensajeEmitirFichero extends Mensaje{
    private String namePelicula;

    public MensajeEmitirFichero(String origen, String destino, String namePelicula)
    {
        super(7, origen, destino);
        this.namePelicula = namePelicula;
    }

    public String getPelicula(){
        return namePelicula;
    }
}
