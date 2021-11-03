public class MensajePreparadoCS extends Mensaje{
    private String dirIP;
    private int puerto;
    private String pelicula;

    public MensajePreparadoCS(String usuario, String destino, String dirIP, int puerto, String pelicula)
    {
        super(8, usuario, destino);
        this.dirIP = dirIP;
        this.puerto = puerto;
        this.pelicula = pelicula;
    }
    public String getDirIP(){
        return dirIP;
    }

    public int getPuerto(){
        return puerto;
    }

    public String getPelicula(){
        return pelicula;
    }
}