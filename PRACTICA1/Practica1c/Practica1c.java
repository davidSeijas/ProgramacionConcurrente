//David Seijas Pérez
//Jorge del Valle Vázquez

package Practica1a;

public class Practica1c{
    public static void main(String[] args) 
    {
        // Dos matrices para multiplicar 
        double[][] m1 = new double[][] {{1,2,3},{4,5,4},{3,2,1}};
        double[][] m2 = new double[][] {{1,2,3},{4,5,4},{3,2,1}};

        double[][] solucion = new Practica1c().multiplica(m1, m2);

        for (int i = 0; i < solucion.length; i++)
        {
            for (int j = 0; j < solucion[0].length; j++)
                System.out.print(solucion[i][j] + " ");
            System.out.println(" ");
        }
    }

    public double[][] multiplica (double[][] m1, double[][] m2)
    {
        assert m1 != null;
        assert m2 != null;
        assert m1.length > 0;
        assert m1[0].length > 0;
        assert m2.length > 0;
        assert m2[0].length > 0;
        assert m1.length == m2[0].length;

        int filas = m1.length;
        int columnas = m2[0].length;
        double[][] solucion = new double[filas][columnas];

        Thread hilos[] = new Thread[m1.length];

        for (int f = 0; f < filas; f++)
        {
            hilos[f] = new ThFila(m1, m2, solucion, f);
            hilos[f].start();
        }

        for (Thread hilo: hilos)
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return solucion;
    }
}