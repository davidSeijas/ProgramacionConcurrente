import java.io.*;
import java.net.*;

public class Emisor extends Thread{
    private InformacionCliente cliente;
    private RWController monitor;
    private String destino;
    private Pelicula pelicula;

    public Emisor(InformacionCliente cliente, RWController monitor, String destino, String pelicula)
    {
        this.cliente = cliente;
        this.monitor = monitor;
        this.destino = destino;
        this.pelicula = null;

        monitor.requestRead();
        for(Pelicula p : cliente.getPeliculas()){
            if(p.getName().equals(pelicula)){
                this.pelicula = p;
            }
        }
        monitor.releaseRead();
    }

    public void run()
    {
        ServerSocket ss = null;
        Socket s = null;
        ObjectOutputStream fout = null;

        try {
            monitor.requestRead();
            ss = new ServerSocket(cliente.getPuerto());
            monitor.releaseRead();
            s = ss.accept();
            fout = new ObjectOutputStream(s.getOutputStream());
            
            monitor.requestRead();
            System.out.println("Conexion entre clientes " + cliente.getId() + " y " + destino  + " establecida");
            monitor.releaseRead();

            if(pelicula == null){
                monitor.requestRead();
                System.out.println(cliente.getId() + ": Ha ocurrido un error al obtener info del servidor");
                monitor.releaseRead();
            }
            else{
                fout.writeObject(pelicula);
            }   
            
            s.close();   
            ss.close();   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
