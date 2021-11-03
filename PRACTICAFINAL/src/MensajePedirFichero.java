public class MensajePedirFichero extends Mensaje{
    private String namePelicula;

    public MensajePedirFichero(String origen, String destino, String namePelicula)
    {
        super(6, origen, destino);
        this.namePelicula = namePelicula;
    }

    public String getPelicula(){
        return namePelicula;
    }
}
