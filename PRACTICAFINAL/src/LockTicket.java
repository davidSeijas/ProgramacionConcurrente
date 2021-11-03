import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket  
{
    private int N; //numero de procesos
    private volatile AtomicInteger number;
    private volatile int next;
    private int[] turn; //no hace falta que sea volatile pues cada proceso accede y modifica unicamente su elemento

    public LockTicket(int N) 
    {
        this.N = N;
        this.number = new AtomicInteger(0);
        this.next = 0;
        this.turn = new int[this.N];
        for (int i = 0; i < this.N; ++i) {
            turn[i] = -1;
        }
    }

    public void takeLock(int i) {
        turn[i] = number.getAndIncrement(); //turn[i] = Fecth_and_add(number, 1);
        while (turn[i] != next);
    }

    public void releaseLock(int i) {
        ++this.next;
    }
}