package psp_opcional_parking;

public class PSP_Opcional_Parking {

    public static void main(String[] args) {
        //OBJETO PARKING QUE RECIBE COMO PARÁMETROS Nº DE PLAZAS Y Nº DE COCHES
        Parking objP = new Parking(6, 10);

        objP.crearHilos();

    }

}
