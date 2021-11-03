//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;

public class LockBakery extends Lock
{
    //private int N;  //numero de procesos
    private volatile int[] turn;
    
    public LockBakery (int N)
    {
        //this.N = N;
        super(N);
        this.turn = new int[this.N];
        for(int i = 0; i < this.N; ++i){
            turn[i] = -1;
        }
        turn = turn;
    }

    public void takeLock(int i)
    {
        turn[i] = 1;
	    turn[i] = max(turn) + 1;
        turn = turn;
        for(int j = 0; j < this.N; ++j){
            if(j != i){
                while(turn[j] != -1 && Op(turn[i], i, turn[j], j));
            }
        }
    }

    public void releaseLock(int i)
    {
        turn[i] = -1;
        turn = turn;
    }

    private int max(int[] v)
    {
        int max = 0;
        for(int i = 1; i < this.N; ++i){
            if(v[i] > max){
                max = v[i];
            }
        }
        return max;
    }

    private boolean Op(int x, int i, int y, int j)  //como se hace un operador??
    {  
        return ((x > y) || (x == y && i > j));
    }
}