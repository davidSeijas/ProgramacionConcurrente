import java.util.*;

public class MensajeConfListaUsuarios extends Mensaje {
    private List<String> listaU;

    public MensajeConfListaUsuarios(String origen, String destino, List<String> listaU)
    {
        super(5, origen, destino);
        this.listaU = listaU;
    }

    public List<String> getListaUsuarios(){
        return listaU;
    }
}