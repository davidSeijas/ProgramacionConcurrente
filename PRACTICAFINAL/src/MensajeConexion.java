import java.util.*;

public class MensajeConexion extends Mensaje {
    private String name;
    private List<String> namePeliculas;

    public MensajeConexion(String usuarioName, String destino, List<Pelicula> peliculas)
    {
        super(0, usuarioName, destino);
        this.name = usuarioName;
        this.namePeliculas = new ArrayList<String>();
        for(Pelicula p : peliculas){
            this.namePeliculas.add(p.getName());
        }
    }
    
    public String getName(){
        return name;
    }

    public List<String> getPeliculas(){
        return namePeliculas;
    }
}
