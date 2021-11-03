import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args) throws UnknownHostException, IOException{
        System.out.println("Ingresa nombre de cliente: ");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        
        System.out.println("Ingresa nº de películas que tienes: ");
        int numPelis = Integer.parseInt(scanner.nextLine());
        for(int i = 1; i <= numPelis; ++i){
            System.out.println(i + ".");
            System.out.print("Nombre: ");
            String namePelicula = scanner.nextLine();
            System.out.print("Duración (mins): ");
            int dur = Integer.parseInt(scanner.nextLine());
            System.out.print("Valoracion (0-10): ");
            int val = Integer.parseInt(scanner.nextLine());
            peliculas.add(new Pelicula(namePelicula, dur, val));
        }

        InformacionCliente cliente = new InformacionCliente(nombre, 600, "localhost", peliculas);
        RWController monitor = new RWController();
        Socket sc = new Socket("localhost", 500); //crea canal con servidor y el servidor acepta en accept
        ObjectOutputStream fout = new ObjectOutputStream(sc.getOutputStream());
        ObjectInputStream fin = new ObjectInputStream(sc.getInputStream());

        //este lock servirá para gestionar los mensajes por pantalla
        LockTicket lock = new LockTicket(2); //pasamos solo dos procesos porque el acceso a consolo es de cliente(0) y oyenteServidor(1)
        
        (new OyenteServidor(cliente, monitor, fout, fin, lock, sc)).start();
        Mensaje m = new MensajeConexion(nombre, "S", peliculas);
        fout.writeObject(m);
        
        int opcion = 1;
        while(opcion != 0){
            lock.takeLock(0);
            System.out.println("MENU:");
            System.out.println("0. Cerrar conexión y salir");
            System.out.println("1. Pedir Lista de Usuarios");
            System.out.println("2. Descargar pelicula");
            System.out.println("3. Añadir pelicula");
            System.out.println("4. Borrar pelicula");
            lock.releaseLock(0); 

            String pelicula;
            opcion = Integer.parseInt(scanner.nextLine());
            switch(opcion){
                case 0:  
                    monitor.requestRead();
                    m = new MensajeCierreConexion(cliente.getId(), "S");
                    monitor.releaseRead();

                    fout.writeObject(m);
                    System.out.println("Cerrando conexion...");

                    break;

                case 1:
                    monitor.requestRead();
                    m = new MensajeListaUsuarios(cliente.getId(), "S");
                    monitor.releaseRead();
                    fout.writeObject(m);

                    break;

                case 2:
                    System.out.println("¿Qué película deseas?");
                    pelicula = scanner.nextLine();

                    monitor.requestRead();
                    m = new MensajePedirFichero(cliente.getId(), "S", pelicula);
                    monitor.releaseRead();
                    fout.writeObject(m);


                    break;
                
                case 3:
                    System.out.println("¿Qué película has adquirido?");
                    System.out.print("Nombre: ");
                    String namePelicula = scanner.nextLine();
                    System.out.print("Duración (mins): ");
                    int dur = Integer.parseInt(scanner.nextLine());
                    System.out.print("Valoracion (0-10): ");
                    int val = Integer.parseInt(scanner.nextLine());

                    monitor.requestRead();
                    m = new MensajeAñadirPelicula(cliente.getId(), "S", new Pelicula(namePelicula, dur, val));
                    monitor.releaseRead();
                    fout.writeObject(m);

                    break;
                    

                case 4:
                    
                System.out.println("¿Qué película deseas borrar?");
                pelicula = scanner.nextLine();

                monitor.requestRead();
                m = new MensajeBorrarPelicula(cliente.getId(), "S", pelicula);
                monitor.releaseRead();
                fout.writeObject(m);

                break;

            }
        }
        scanner.close();
    }
}
