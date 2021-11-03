//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;

public class ThFila extends Thread
{
    private double[][] m1;
    private double[][] m2;
    private double[][] solucion;
    private int fila;

    public ThFila (double[][] m1, double[][]  m2, double[][] solucion, int fila)
    {
        this.m1 = m1;
        this.m2 = m2;
        this.solucion = solucion;
        this.fila = fila;
    }

    public void run()
    {
        for(int c = 0; c < m2[0].length; ++c)
        {
            solucion[fila][c] = 0.0;
            for (int i = 0; i < m2.length; i++)
                solucion[fila][c] += m1[fila][i]*m2[i][c];
        }
    }
}
