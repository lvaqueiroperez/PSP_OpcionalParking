package psp_opcional_parking;

import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

//EL ARRAY CON LAS PLAZAS SERÁ EL RECURSO COMPARTIDO
//SOLO SE ADMITE UNA ENTRADA Y UNA SALIDA A LA VEZ
//se supone que hay un método para entrar y otro para salir, de manera que 
//ambos métodos se llaman en la clase de los hilos separados por un sleep()
public class MonitorParking extends CochesHilos {

    private int numPlazas = Parking.numPlazas;
    private int plazasArray = numPlazas;
    private Integer[] arrayPlazas = new Integer[plazasArray];
    //lo usaremos como contador (el contador se muestra correctamente, pero el array en las salidas??)
    private int contP = numPlazas;
    private Boolean acceso = true;
    //para guardar la posición donde se aparcó el coche
    //private int pos = 0;

    public MonitorParking() {

    }

    //Constructor para que funcione la herencia
    public MonitorParking(String nombre, MonitorParking objM) {
        super(nombre, objM);
    }

    //constructor
//    public MonitorParking(int numPlazas){
//        
//        this.numPlazas = numPlazas;
//        
//    }
    //A este método accederán varios hilos a la vez
    //también le hace falta ser synchronized
    public synchronized void mostrarArray() {

        //arrayPlazas[0] = 0;
        for (int i = 0; i < arrayPlazas.length; i++) {

            System.out.print("[" + arrayPlazas[i] + "]");

        }
        System.out.println("\n");

    }

    public void inicializarArray() {

        for (int i = 0; i < arrayPlazas.length; i++) {

            arrayPlazas[i] = 0;

        }

    }

    //
    public synchronized void entrada(String nombreC) {

        while (!acceso) {

            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorParking.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        acceso = false;
        //recorremos el array en busca de sitios libres (0)
        for (int i = 0; i < arrayPlazas.length; i++) {

            if (arrayPlazas[i] == 0) {

                arrayPlazas[i] = 1;
                //guardamos su posición (?)
                super.setPos(i);
                System.out.println("ENTRADA: " + nombreC + " aparca en plaza " + i);
                contP--;
                System.out.println("PLAZAS LIBRES: " + contP);
                System.out.println("PARKING: ");
                mostrarArray();
                //salimos del for
                break;

            }

        }

        acceso = true;
        notifyAll();

        //iniciar en entrada el propio método de salida para poder pasarle el parámetro de pos??
    }

    public synchronized void salida(String nombreC) {

        while (!acceso) {

            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorParking.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        acceso = false;

        System.out.println("SALIDA: " + nombreC + " saliendo");
        //PROBLEMA: COMO ALMACENAR LA POSICIÓN? YA QUE ES UNA VARIABLE TAMBIÉN COMPARTIDA POR TODOS
        //PODEMOS HACER QUE CADA HILO TENGA SU VARIABLE DE POSICIÓN?
        //NECESITO UNA REFERENCIA AL HILO EJECUTANDO ESTE MÉTODO
        int posGet = super.getPos();
        arrayPlazas[posGet] = 0;
        contP++;
        System.out.println("PLAZAS LIBRES: " + contP);
        System.out.println("PARKING:");
        mostrarArray();

        acceso = true;
        notifyAll();

    }

}
