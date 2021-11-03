import java.io.*;
import java.net.*;

public class Receptor extends Thread{
    private InformacionCliente cliente;
    private RWController monitor;
    private String dirIP;
    private int puerto;

    public Receptor(InformacionCliente cliente, RWController monitor, String dirIP, int puerto)
    {
        this.cliente = cliente;
        this.monitor = monitor;
        this.dirIP = dirIP;
        this.puerto = puerto;
    }

    public void run()
    {        
        Socket s = null;
        ObjectInputStream fin = null;

        try {
            monitor.requestRead();
            System.out.println("Conectando " + cliente.getId() + " a " + dirIP + "...");
            monitor.releaseRead();

            s = new Socket(dirIP, puerto);
            fin = new ObjectInputStream(s.getInputStream());
            
            Pelicula p = (Pelicula)fin.readObject();
            monitor.requestRead();
            System.out.println(cliente.getId() + ": Descargando " + p.getName() + " de " + p.getDuracion() + " mins y " + p.getValoracion() + " de valoracion");
            monitor.releaseRead();
            
            monitor.requestWrite();
            cliente.añadirPelicula(p);
            monitor.releaseWrite();

            s.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } 
    }
}
