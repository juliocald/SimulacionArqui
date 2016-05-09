import java.io.File;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args){

        Ventana ventana = new Ventana();
        ventana.setVisible(true);

        while(ventana.isVisible()){
            //esperar...
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

        CyclicBarrier barrera = new CyclicBarrier(2);

        CPU cpu1 = new CPU(1, quantum, archivos, barrera);
//        CPU cpu2 = new CPU(2, quantum, barrera);
//        CPU cpu3 = new CPU(2, quantum, barrera);

        Thread thread1 = new Thread(cpu1);
//        Thread thread2 = new Thread(cpu2);
//        Thread thread3 = new Thread(cpu3);

        thread1.start();
//        thread2.start();
//        thread3.start();

        int ciclo = 0;
        while (!cpu1.procesamientoTerminado()/* && !cpu2.procesamientoTerminado() && !cpu3.procesamientoTerminado()*/) {

            try {
                barrera.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                //...
            }

            ciclo++;
            System.out.println("Ciclo = " + ciclo);
        }
        cpu1.imprimir_registros();
        System.exit(0);

//        while(true){
//            if(!cpu1.procesamientoTerminado() && !cpu2.procesamientoTerminado() && !cpu3.procesamientoTerminado()){
//                try {
//                    barrera.await();
//                } catch (InterruptedException | BrokenBarrierException e) {
//                    //...
//                }
//            } else{
//                break;
//            }
//        }

//        int var_com = cpu1.variable_compartida+cpu2.variable_compartida+cpu3.variable_compartida;
//        System.out.println("Var_comp = " + var_com);
    }
}

///**
// * Created by Julio on 5/2/16.
// */
//
//import java.util.Scanner;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//
//public class Main {
//
//    public static void main(String[] args){
//
//        Scanner in = new Scanner(System.in);
//        System.out.println("Ingrese el valor del quantum: ");
//        int quantum = Integer.parseInt(in.nextLine());
//
//        CyclicBarrier barrera = new CyclicBarrier(4);
//
//        CPU cpu1 = new CPU(1, quantum, barrera);
//        CPU cpu2 = new CPU(2, quantum, barrera);
//        CPU cpu3 = new CPU(2, quantum, barrera);
//
//        Thread thread1 = new Thread(cpu1);
//        Thread thread2 = new Thread(cpu2);
//        Thread thread3 = new Thread(cpu3);
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//
//        while(true){
//            if(!cpu1.procesamientoTerminado() && !cpu2.procesamientoTerminado() && !cpu3.procesamientoTerminado()){
//                try {
//                    barrera.await();
//                } catch (InterruptedException | BrokenBarrierException e) {
//                    //...
//                }
//            } else{
//                break;
//            }
//        }
//    }
//}

//package proyectoarqui;