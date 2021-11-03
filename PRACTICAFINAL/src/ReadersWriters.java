import java.util.concurrent.Semaphore;

public class ReadersWriters {
    volatile int nReaders, nWriters, dReaders, dWriters;
    volatile Semaphore excMutua, semReaders, semWriters;

    public ReadersWriters(Semaphore excMutua, Semaphore semReaders, Semaphore semWriters){
        this.nReaders = nWriters = dReaders = dWriters = 0;
        this.excMutua = excMutua;
        this.semReaders = semReaders;
        this.semWriters = semWriters;
    }
    
    public void readerEntrada(){
        try {
            excMutua.acquire();
            if(nWriters > 0){
                ++dReaders;
                excMutua.release();
                semReaders.acquire(); //coge testigo (ya tengo excMutua)
            }
            ++nReaders;

            if(dReaders > 0){
                --dReaders;
                semReaders.release(); //paso testigo (despierta a otro reader si hay alguno en espera)
            }
            else{
                excMutua.release(); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void writerEntrada(){
        try {
            excMutua.acquire();
            if(nReaders > 0 || nWriters > 0){
                ++dWriters;
                excMutua.release();
                semWriters.acquire(); //coge testigo (ya tengo excMutua: me despierta un reader o writer al salir)
            }
            ++nWriters;
            excMutua.release(); 
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void readerSalida(){
        try {
            excMutua.acquire();
            --nReaders;

            if(nReaders == 0 && dWriters > 0){
                --dWriters;
                semWriters.release(); //paso testigo (despierto writer)
            }
            else{
                excMutua.release(); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void writerSalida(){
        try {
            excMutua.acquire();
            --nWriters;

            if(dWriters > 0){
                --dWriters;
                semWriters.release(); //paso testigo (despierto writer)
            }
            else if(dReaders > 0){
                --dReaders;
                semReaders.release(); //paso testigo (despierto a un reader si no hay writers y alguno espera)
            }
            else{
                excMutua.release(); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
