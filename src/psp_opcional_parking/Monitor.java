package psp_opcional_parking;

import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;
//el parking tiene una única entrada y salida
//se supone que hay un método para entrar y otro para salir, de manera que 
//ambos métodos se llaman en la clase de los hilos separados por un sleep()

public class Monitor {

    private int[] parking;

    

    private Boolean acceso = false;

    private int aparcados = 0;

    public synchronized void controlParking(String nombreCoche) {

        while ((aparcados == 5) || (acceso == false)) {

            try {
                
                System.out.println("APARCAMIENTO LLENO, ESPERE");
                wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        acceso = false;

        for (int i = 0; i < parking.length; i++) {

            if (parking[i] == 0) {

                parking[i] = i;

                System.out.println("ENTRADA: " + nombreCoche + "EN " + i);
                aparcados++;
                //salimos del for
                break;

            } 
            
            
            
            

        }

    }

}
