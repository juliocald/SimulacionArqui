import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {

        CyclicBarrier barreraGUI = new CyclicBarrier(2);
        Ventana ventana = new Ventana(barreraGUI);
        ventana.setVisible(true);

        try {
            barreraGUI.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            //...
        }
        //int quantum = 5000;
//        int hilos = 6; //Para qué se usa???

        int quantum = Integer.parseInt(ventana.cantidad_quantum.getText());
        int hilos = Integer.parseInt(ventana.cantidad_hilos.getText());

        System.out.println("\n----- Hilos = " + hilos + " -----\n");
        System.out.println("\n----- Quantum = " + quantum + " -----\n");

//        File[] archivos = new File[1];
//        File arch = new File("G:/Sharon/Cursos/Arquitectura de Computadoras/Proyecto/HILOS 1era Parte/2.txt");
//        archivos[0] = arch;
        File[] archivos = ventana.ventana_buscar_archivos.getSelectedFiles();
        for (File archivo : archivos) {
            System.out.println("You chose to open this file: " + archivo.getAbsolutePath());
        }

        List<File> archivosCPU1 = new ArrayList<File>();
        List<File> archivosCPU2 = new ArrayList<File>();
        List<File> archivosCPU3 = new ArrayList<File>();

        int temporal = 1;
        for (int i = 0; i < archivos.length; i++) {
            if (temporal == 4) {
                temporal = 1;
            }
            switch (temporal) {
                case 1:
                    archivosCPU1.add(archivos[i]);
                    temporal++;
                    break;
                case 2:
                    archivosCPU2.add(archivos[i]);
                    temporal++;
                    break;
                case 3:
                    archivosCPU3.add(archivos[i]);
                    temporal++;
                    break;
            }
        }


        CyclicBarrier barrera = new CyclicBarrier(4);

        CPU cpu1 = new CPU(1, quantum, archivosCPU1, barrera);
        CPU cpu2 = new CPU(2, quantum, archivosCPU2, barrera);
        CPU cpu3 = new CPU(3, quantum, archivosCPU3, barrera);

        Thread thread1 = new Thread(cpu1);
        Thread thread2 = new Thread(cpu2);
        Thread thread3 = new Thread(cpu3);

        thread1.start();
        thread2.start();
        thread3.start();

        int ciclo = 0;
        while (!cpu1.getTerminado() || !cpu2.getTerminado() || !cpu3.getTerminado()) {

        try {
                barrera.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                //...
            }

            ciclo++;
            if (ciclo == 834){
                System.out.println("Ciclo = " + ciclo);
            }
            System.out.println("Ciclo = " + ciclo);
        }
        cpu1.imprimir_registros();
        System.exit(0);
    }
}