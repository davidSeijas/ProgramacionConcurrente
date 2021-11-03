import java.io.Serializable;

public abstract class Mensaje implements Serializable{
    private int tipo; 
    //0: Conexion; 1: ConfConexion; 2: CierreConexion; 3: ConfCierreConexion; 
    //4: ListaUsuarios; 5: ConfListaUsuarios; 6: PedirFichero; 7: EmitirFichero; 8: PreparadoCS; 9: PreparadoSC;
    private String origen;
    private String destino;

    public Mensaje(int tipo, String origen, String destino)
    {
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }

    public int getTipo()
    {
        return tipo;
    }

    public String getOrigen()
    {
        return origen;
    }

    public String getDestino()
    {
        return destino;
    }
}
