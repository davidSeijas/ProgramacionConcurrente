//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica2b;


public class LockRompeEmpate extends Lock
{
    //private int N;  //numero de procesos
    private volatile int[] in;  //volatile es que se guarda en memoria principal
    private volatile int[] last;

    public LockRompeEmpate (int N)
    {
        //this.N = N;
        super(N);
        this.in = new int[this.N];
        this.last = new int[this.N];
        for(int i = 0; i < this.N; ++i){
            in[i] = -1;
            last[i] = -1;
        }
        in = in;
        last = last; //puede ser fuera porque no se han creado los hilos todavia y no va a haber problemas
    }

    public void takeLock(int i)
    {
        for(int j = 0; j < this.N; ++j){
            in[i] = j;
            in = in; //al ser volatile un array lo que es volatile es el puntero y para llevar el valor actualizado en memoria principal del puntero hay que hacer una autoasiganción de todo el array tras cada modificación
            last[j] = i;
            last = last;

            for(int k = 0; k < this.N; ++k){
                if(k != i){
                    while(in[k] >= in[i] && last[j] == i);
                }
            }
        }
    }

    public void releaseLock(int i)
    {
        in[i] = -1;
        in = in;
    }
}