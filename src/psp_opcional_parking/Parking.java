package psp_opcional_parking;
//EN ESTA CLASE SE CREARÁN LOS HILOS

public class Parking {

    //define el numero de elementos del array (variable global para que funcione bien)
    public static int numPlazas;
    //cuantos coches estarán entrando y saliendo (el número de hilos)
    private int numCoches;

    public Parking(int numPlazas, int numCoches) {
        this.numPlazas = numPlazas;
        this.numCoches = numCoches;
    }

    //Método donde se crearán hilos
    public void crearHilos() {
        //Objeto para que accedan a la misma clase
        MonitorParking objM = new MonitorParking();
        //Inicializamos el array !!!
        objM.inicializarArray();
        //array para ir creando y almacenando los hilos
        CochesHilos[] arrayCoches = new CochesHilos[numCoches];

        for (int i = 0; i < numCoches; i++) {

            //DUDA: Hilo que cree otro hilo ó Array que almacene primero los hilos y 
            //luego los inicie? (hecho de la segunda manera)
            arrayCoches[i] = new CochesHilos("COCHE " + i, objM);

        }
        //iniciamos los hilos
        for (int i = 0; i < arrayCoches.length; i++) {

            arrayCoches[i].start();

        }
          

    }

}
