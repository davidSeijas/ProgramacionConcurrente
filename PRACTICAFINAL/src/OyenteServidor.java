import java.io.*;
import java.net.*;
import java.util.*;

public class OyenteServidor extends Thread{
    private InformacionCliente cliente;
    private RWController monitor;
    private ObjectOutputStream fout;
    private ObjectInputStream fin;
    private LockTicket lock;
    private Socket sc;

    public OyenteServidor(InformacionCliente cliente, RWController monitor, ObjectOutputStream fout, ObjectInputStream fin, LockTicket lock, Socket sc)
    {
        this.cliente = cliente;
        this.monitor = monitor;
        this.fout = fout;
        this.fin = fin;
        this.lock = lock;
        this.sc = sc;
    }

    public void run()
    {
        boolean done = false;
        
        while(!done){
            Mensaje m = null;
            lock.takeLock(1);

            try {
                m = (Mensaje) fin.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            switch(m.getTipo()){
                case 1: //confirmar conexion
                    monitor.requestWrite();
                    cliente.setId(m.getDestino());
                    monitor.releaseWrite();

                    monitor.requestRead();
                    System.out.println("Conexión establecida con cliente " + cliente.getId() + ": " + cliente.getName()); 
                    monitor.releaseRead();   

                    break;

                case 3: //confirmar cierre conexion
                    monitor.requestRead();
                    System.out.println("Conexión cerrada con cliente " + cliente.getId() + ": " + cliente.getName());
                    monitor.releaseRead();  
                    done = true;

                    try {
                        sc.close();
                        break;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    break;

                case 5: //confirmar lista usuarios
                    MensajeConfListaUsuarios m1 = (MensajeConfListaUsuarios) m;
                    List<String> usuarios = m1.getListaUsuarios();

                    monitor.requestRead();
                    System.out.println(cliente.getId() + ": Lista de usuarios conectados: ");
                    monitor.releaseRead();  
                    
                    for(int i = 1; i <= usuarios.size(); ++i){
                        System.out.println(i + ". " + usuarios.get(i-1));
                    }

                    break;

                case 7: //emitir fichero
                    MensajeEmitirFichero m2 = (MensajeEmitirFichero) m;
                    String destino = m2.getOrigen();
                    String pelicula = m2.getPelicula();
                    new Emisor(cliente, monitor, destino, pelicula).start();

                    monitor.requestRead();
                    String usuario = cliente.getId();
                    String dirIP = cliente.getDirIP();
                    int puerto = cliente.getPuerto();
                    monitor.releaseRead();

                    MensajePreparadoCS preparado = new MensajePreparadoCS(usuario, destino, dirIP, puerto, pelicula);

                    try {
                        fout.writeObject(preparado);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case 9: //preparado SC
                    MensajePreparadoSC m3 = (MensajePreparadoSC) m;
                    Receptor r = new Receptor(cliente, monitor, m3.getDirIP(), m3.getPuerto());
                    r.start();
                    try {
                        r.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Descarga realizada con éxito");

                    break;
                
                case 11: //confirmar añadirPeli
                    MensajeConfAñadirPeli m4 = (MensajeConfAñadirPeli) m;
                    Pelicula peliA = m4.getPelicula();
                    
                    monitor.requestWrite();
                    cliente.añadirPelicula(peliA);
                    monitor.releaseWrite();

                    System.out.println("Pelicula " + peliA.getName()+ " añadida con éxita"); 

                    break;                
                
                case 13: //confirmar borrarPeli
                    MensajeConfBorrarPeli m5 = (MensajeConfBorrarPeli) m;
                    String peliB = m5.getPelicula();

                    monitor.requestWrite();
                    cliente.borrarPelicula(peliB);
                    monitor.releaseWrite();

                    System.out.println("Pelicula " + peliB + " borrada con éxito");       

                    break;

                case 14: //error
                    MensajeError m6 = (MensajeError) m;
                    System.out.println(m6.getError());                
                    break;
            }

            lock.releaseLock(1);
        }
    }
}
