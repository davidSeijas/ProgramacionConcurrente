//David Seijas Pérez
//Jorge del Valle Vázquez


public class MiAlmacen //es el monitor
{
    private int tamMax;
    private Producto producto[];
    private int ini;        
    private int fin;        
    private int count;      //número de productos almacenados

    public MiAlmacen(int n)
    {   
        this.tamMax = n;
        this.producto = new Producto[tamMax];
        this.ini = 0;           
        this.fin = 0;           
        this.count = 0;
    }

    synchronized void almacenar(Producto producto)
    {   
        while(count == tamMax){
            try{
                wait();
            }catch (InterruptedException e) {
                System.out.println("Error al encolarme para esperar hueco en almacén");
                e.printStackTrace();
            }
        } 
            
        this.producto[fin] = producto;
        count++;
        System.out.println("Se almacena producto en la pos: " + fin + ". Nº de productos en alamacén: " + count);
        fin = (fin + 1) % tamMax;

        notifyAll(); //tienes que despertar a todos pues si haces notify no sabes si se despertará uno que estaba esperando a almacenar o uno esperando a extraer
    }

    synchronized Producto extraer()
    {   
        while(count == 0){
            try{
                wait();
            }catch (InterruptedException e) {
                System.out.println("Error al encolarme para esperar productos en almacén");
                e.printStackTrace();
            }
        } 
        
        Producto producto = this.producto[ini];
        count--;
        System.out.println("Se extrae producto de la pos: " + ini + ". Nº de productos en alamacén: " + count);
        ini = (ini + 1) % tamMax;

        notifyAll(); //igual que antes

        return producto;
    }
}
