package trabajadoresPostCinta;

import elementos.DepositoDistribucion;
import elementos.Producto;

public class Distribuidor extends Thread {
    private final DepositoDistribucion deposito;
    private final String tipoProducto;
    private final String nombre;

    public Distribuidor(DepositoDistribucion deposito, String tipoProducto, String nombre) {
        this.deposito = deposito;
        this.tipoProducto = tipoProducto;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Producto producto = deposito.retirarProducto(tipoProducto, nombre);

                // Verifica si el producto es de tipo "FIN_A" o "FIN_B"
                if (producto.getTipo().equals("FIN_" + tipoProducto)) {
                    System.out.println(nombre + " terminó su trabajo.");
                    break; // Sale del ciclo y termina su ejecución
                }

                // Simula el tiempo de distribución
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
