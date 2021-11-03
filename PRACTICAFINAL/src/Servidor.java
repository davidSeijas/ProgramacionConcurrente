/*ALUMNOS:
David Seijas Pérez
Jorge Del Valle Vázquez
*/

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class Servidor {
    public static void main(String[] args) throws IOException
    {
        int puerto = 500;
        Informacion info = new Informacion();
        Semaphore excMutua = new Semaphore(1);
        Semaphore semReaders = new Semaphore(0);
        Semaphore semWriters = new Semaphore(0);
        ReadersWriters rw = new ReadersWriters(excMutua, semReaders, semWriters);
        ServerSocket ss = new ServerSocket(puerto); //crea el servidor para gestionar todos los canales con clientes
        
        while(true){
            Socket si = ss.accept(); //espera a que un cliente se conecte con él
            System.out.println("Servidor conectado con cliente");
            (new OyenteCliente(si, rw, info)).start();
        }
    }
}
