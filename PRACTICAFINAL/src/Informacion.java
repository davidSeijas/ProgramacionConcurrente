import java.util.*;
import java.io.*;

public class Informacion {
    private HashMap<String, Pair> tablaUsuarios; //tabla de usuarios = (idUsuario, fin, fout) //pair de ObjectOutputStream y ObjectInputStream
    private HashMap<String, List<String>> tablaInfo; //tabla de info = (idUsuario, {f1, f2,...}))
    private HashMap<String, String> tablaNames; //(idUsuario, name)
    private int nClientes;

    public Informacion()
    {
        this.tablaUsuarios = new HashMap<String, Pair>(); 
        this.tablaInfo = new HashMap<String, List<String>>();
        this.tablaNames = new HashMap<String, String>();
        this.nClientes = 0;
    }

    
    //lectura

    public List<String> getListaUsuarios()
    {
        List<String> l = new ArrayList<String>();
        for(String s : tablaInfo.keySet()){
            String result = s + " - " + tablaNames.get(s);
            if(tablaInfo.get(s).size() != 0){
                result += ":\n";
                for(int i = 0; i < tablaInfo.get(s).size() - 1; ++i){
                    result += tablaInfo.get(s).get(i) + " - ";
                }
                result += tablaInfo.get(s).get(tablaInfo.get(s).size() - 1);
            }
            l.add(result);
        }
        return l;
        
    }

    public String getPropPelicula(String namePeli)
    {
        String owner = null;
        for (String key : tablaInfo.keySet()) {
            List<String> value = tablaInfo.get(key);
            for(String film : value){
                if(film.equals(namePeli))
                    owner = key;
            }
        }
        return owner;
    }

    public boolean tenerPeli(String id, String namePeli){
        boolean stop = false;
        List<String> value = tablaInfo.get(id);
        for(int i = 0; i < value.size() && !stop; ++i){
            if(value.get(i).equals(namePeli)){
                stop = true;
            }
        }
        return stop;
    }

    public ObjectInputStream getFin(String idCliente){
        return (ObjectInputStream)tablaUsuarios.get(idCliente).getFirst();
    }

    public ObjectOutputStream getFout(String idCliente){
        return (ObjectOutputStream)tablaUsuarios.get(idCliente).getSecond();
    }


    //escritura

    public String añadirUsuario(String name, List<String> pelis, ObjectInputStream fin, ObjectOutputStream fout){
        String idCliente = "C"+(++nClientes);
        tablaInfo.put(idCliente, pelis);
        tablaUsuarios.put(idCliente, new Pair(fin, fout));
        tablaNames.put(idCliente, name);
        return idCliente;
    }

    public void eliminarUsuario(String idCliente){
        tablaInfo.remove(idCliente);
        tablaUsuarios.remove(idCliente);
    }

    public void añadirPeli(String id, String peli){
        List<String> peliculas = tablaInfo.get(id);
        peliculas.add(peli);
        tablaInfo.put(id, peliculas);
    }

    public void borrarPeli(String id, String peli){
        List<String> peliculas = tablaInfo.get(id);
        int i = 0;
        while(i < peliculas.size()){
            if(peliculas.get(i).equals(peli)) break;
            ++i;
        }
        peliculas.remove(i);
        tablaInfo.put(id, peliculas);
    }
}
