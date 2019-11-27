package psp_opcional_parking;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CochesHilos extends Thread {

    private MonitorParking objM;
    //CÓMO HACER UN SET/GET DESDE UNA CLASE EXTERNA?
    private int pos;

    //Constructor sin parámetros
    public CochesHilos() {

    }

    //Constructor con parámetros y getName()
    public CochesHilos(String nombre, MonitorParking objM) {

        super(nombre);
        this.objM = objM;

    }
    
    

    //GETTERS Y SETTERS DE LA POS
    public synchronized void setPos(int pos) {
        this.pos = pos;
    }

    public synchronized int getPos() {
        return pos;
    }

    @Override
    public void run() {

        System.out.println("HILO CREADO: " + getName());
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CochesHilos.class.getName()).log(Level.SEVERE, null, ex);
        }

        objM.entrada(getName());
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CochesHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        objM.salida(getName());

    }

}
