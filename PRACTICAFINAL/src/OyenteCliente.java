import java.io.*;
import java.net.*;

public class OyenteCliente extends Thread{
    private ObjectOutputStream fout;
    private ObjectInputStream fin;
    private ReadersWriters rw;
    private Informacion info;

    public OyenteCliente(Socket si, ReadersWriters rw, Informacion info) throws IOException
    {   
        this.rw = rw;
        fout = new ObjectOutputStream(si.getOutputStream()); 
        fin = new ObjectInputStream(si.getInputStream());
        this.info = info;
    }

    public void run()
    {
        boolean done = false;

        while(!done){
            Mensaje m = null;
            try {
                m = (Mensaje) fin.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            String origen;
            String destino;
            String pelicula;
            Pelicula peli;
            Mensaje mensaje;

            switch(m.getTipo()){
                case 0: // conexion
                    MensajeConexion aux = ((MensajeConexion)m); 
                    origen = m.getDestino();

                    rw.writerEntrada();
                    destino = info.añadirUsuario(aux.getName(), aux.getPeliculas(), fin, fout); //escritura
                    rw.writerSalida();
                    
                    mensaje = new MensajeConfConexion(origen, destino);

                    try {
                        fout.writeObject(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case 2: //cierre conexion
                    origen = m.getDestino();
                    destino = m.getOrigen();
                    mensaje = new MensajeConfCierreConexion(origen, destino);

                    try {
                        fout.writeObject(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    rw.writerEntrada();
                    info.eliminarUsuario(destino); //escritura
                    rw.writerSalida();

                    done = true;

                    break;

                case 4: //lista usuarios
                    origen = "S";
                    destino = m.getOrigen();

                    rw.readerEntrada();
                    mensaje = new MensajeConfListaUsuarios(origen, destino, info.getListaUsuarios()); //lectura
                    rw.readerSalida();

                    try {
                        fout.writeObject(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                    break;

                case 6: //pedir fichero
                    MensajePedirFichero m1 = (MensajePedirFichero) m;
                    origen = m1.getOrigen();
                    pelicula = m1.getPelicula();

                    rw.readerEntrada();
                    if(info.tenerPeli(origen, pelicula)){ //lectura
                        mensaje = new MensajeError("S", origen, "Esta película ya está tu colección");
                        rw.readerSalida();

                        try {
                            fout.writeObject(mensaje);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        destino = info.getPropPelicula(pelicula); //lectura
                        rw.readerSalida();

                        if(destino == null){
                            mensaje = new MensajeError("S", origen, "Esa película no la tiene ningún cliente");
                            try {
                                fout.writeObject(mensaje);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            mensaje = new MensajeEmitirFichero(origen, destino, pelicula);
                            rw.readerEntrada();
                            try {
                                info.getFout(destino).writeObject(mensaje);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } //lectura
                            rw.readerSalida();
                        }
                    }
                    
                    break;

                case 8: //preparado CS
                    MensajePreparadoCS m2 = (MensajePreparadoCS) m;
                    origen = m2.getOrigen();
                    destino = m2.getDestino();

                    pelicula = m2.getPelicula();
                    mensaje = new MensajePreparadoSC(origen, destino, m2.getDirIP(), m2.getPuerto());

                    try {
                        rw.writerEntrada();
                        info.añadirPeli(destino, pelicula); //escritura
                        rw.writerSalida();
                        rw.readerEntrada();
                        info.getFout(m2.getDestino()).writeObject(mensaje); //lectura
                        rw.readerSalida();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case 10: //añadir pelicula
                    MensajeAñadirPelicula m3 = (MensajeAñadirPelicula) m;     
                    origen = m3.getDestino();
                    destino = m3.getOrigen();         
                    peli = m3.getPelicula();  

                    rw.readerEntrada();
                    if(info.tenerPeli(destino, peli.getName())){ //lectura
                        mensaje = new MensajeError("S", origen, "Esta película ya está tu colección");
                        rw.readerSalida();
                    }
                    else{
                        rw.readerSalida();
                        rw.writerEntrada();
                        info.añadirPeli(destino, peli.getName()); //escritura
                        rw.writerSalida();
                        mensaje = new MensajeConfAñadirPeli(origen, destino, peli);
                    }
                        
                    try {
                        fout.writeObject(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

                case 12: //borrar pelicula
                    MensajeBorrarPelicula m4 = (MensajeBorrarPelicula) m;    
                    origen = m4.getDestino();
                    destino = m4.getOrigen();         
                    pelicula = m4.getPelicula();  

                    rw.readerEntrada();
                    if(!info.tenerPeli(destino, pelicula)){ //lectura
                        mensaje = new MensajeError("S", origen, "Esta película NO está tu colección");
                        rw.readerSalida();
                    }    
                    else{
                        rw.readerSalida();
                        rw.writerEntrada();
                        info.borrarPeli(destino, pelicula); //escritura
                        rw.writerSalida();
                        mensaje = new MensajeConfBorrarPeli(origen, destino, pelicula);
                    }
                        
                    try {
                        fout.writeObject(mensaje);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
    
                    break;
            }
        }
    }
}