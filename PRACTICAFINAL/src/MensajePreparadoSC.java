public class MensajePreparadoSC extends Mensaje{
    private String dirIP;
    private int puerto;

    public MensajePreparadoSC(String origen, String destino, String dirIP, int puerto)
    {
        super(9, origen, destino);
        this.dirIP = dirIP;
        this.puerto = puerto;
    }

    public String getDirIP(){
        return dirIP;
    }

    public int getPuerto(){
        return puerto;
    }
}
