public class MensajeError extends Mensaje{
    String error;

    public MensajeError(String origen, String destino, String error)
    {
        super(14, origen, destino);
        this.error = "ERROR: " + error;
    }

    public String getError(){
        return error;
    }
}
