import java.util.concurrent.locks.*;

public class RWController { //este es el monitor (readers-writers) que restringe la lectura y escritura de informacionCliente
    private final Lock lock;
    private final Condition condRd;
    private final Condition condWr;
    private int nr, nw;

    public RWController()
    {
        this.lock = new ReentrantLock(true);
        this.condRd = lock.newCondition();
        this.condWr = lock.newCondition();
        this.nr = 0;
        this.nw = 0;
    }

    public void requestRead(){
        lock.lock();
        while(nw > 0){
            try {
                condRd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++nr;
        lock.unlock();
    }

    public void requestWrite(){
        lock.lock();
        while(nr > 0 || nw > 0){
            try {
                condWr.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++nw;
        lock.unlock();
    }

    public void releaseRead(){
        lock.lock();
        --nr;
        if(nr == 0){
            condWr.signal();
        }
        lock.unlock();
    }

    public void releaseWrite(){
        lock.lock();
        --nw;
        condWr.signal();
        condRd.signalAll();
        lock.unlock();
    }
}
