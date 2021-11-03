import java.util.*;

public class InformacionCliente { //a esta informacion accederan cliente, oyente servidor, recepetor, emisor para obtener info
    private String id;
    private String name;
    private int puerto;
    private String dirIP;
    private List<Pelicula> peliculas;

    public InformacionCliente(String name, int puerto, String dirIP, List<Pelicula> peliculas)
    {
        this.name = name;
        this.puerto = puerto;
        this.dirIP = dirIP;
        this.peliculas = peliculas;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
        int port = Integer.parseInt(id.substring(1)); //le quitamos la C al idCliente y se queda un entero y asi cada cliente manejara un puerto cuando crea emisor
        this.puerto += port;
    }

    public String getName(){
        return name;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getDirIP(){
        return dirIP;
    }

    public List<Pelicula> getPeliculas(){
        return peliculas;
    }

    /*public void añadirPeliculas(List<Pelicula> pelisNuevas){
        this.peliculas.addAll(pelisNuevas);
    }*/

    public void añadirPelicula(Pelicula peli){
        this.peliculas.add(peli);
    }

    public void borrarPelicula(String peli){
        int index = 0;
        for(int i = 0; i < this.peliculas.size(); ++i){
            if(this.peliculas.get(i).getName().equals(peli)){
                index = i;
            }
        }
        this.peliculas.remove(index);
    }
}
