//David Seijas Pérez
//Jorge del Valle Vázquez

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class MiAlmacen //es el monitor
{
    private int tamMax;
    private Producto producto[];
    private int ini;
    private int fin;
    private int count; //número de productos almacenados
    private final Lock lock;
    private final Condition condA;
    private final Condition condE; 

    public MiAlmacen(int n)
    {   
        this.tamMax = n;
        this.producto = new Producto[tamMax];
        this.ini = 0;
        this.fin = 0;
        this.count = 0;
        this.lock = new ReentrantLock(true);
        this.condA = lock.newCondition();
        this.condE = lock.newCondition();
    }

    public void almacenar(ArrayList<Producto> productos)
    {   
        lock.lock();

        while(count + productos.size() > tamMax){
            try{
                condA.await();
            }catch (InterruptedException e) {
                System.out.println("Error al encolarme para esperar hueco en almacén");
                e.printStackTrace();
            }
        } 
        
        for(int i = 0; i < productos.size(); ++i){    
            this.producto[fin] = productos.get(i);
            count++;
            System.out.println("Se almacena producto en la pos: " + fin + ". Nº de productos en alamacén: " + count);
            fin = (fin + 1) % tamMax;
        }
        System.out.println("Total de productos almacenados: " + productos.size());

        condE.signal(); //despertamos a uno de los que esperan para extraer

        lock.unlock();
    }

    public ArrayList<Producto> extraer(int num)
    {   
        lock.lock();

        while(count - num < 0){
            try{
                condE.await();
            }catch (InterruptedException e) {
                System.out.println("Error al encolarme para esperar productos en almacén");
                e.printStackTrace();
            }
        } 

        ArrayList<Producto> productos = new ArrayList<Producto>();
        
        for(int i = 0; i < num; ++i){    
            productos.add(this.producto[ini]);
            count--;
            System.out.println("Se extrae producto de la pos: " + ini + ". Nº de productos en alamacén: " + count);
            ini = (ini + 1) % tamMax;
        }
        System.out.println("Total de productos extraidos: " + productos.size());

        condA.signal(); //despertamos a uno de los que esperan para almacenar

        lock.unlock();

        return productos;
    }
}
