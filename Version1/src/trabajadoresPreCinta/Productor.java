package trabajadoresPreCinta;
import elementos.*;

public class Productor extends Thread {
    private final DepositoProduccion deposito;
    private final String tipoProducto;
    private final String nombre;
    private final int numProductos;

    public Productor(DepositoProduccion deposito, String tipoProducto, String nombre, int numProductos) {
        this.deposito = deposito;
        this.tipoProducto = tipoProducto;
        this.nombre = nombre;
        this.numProductos = numProductos;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numProductos; i++) {
                Producto producto = new Producto(tipoProducto, nombre);
                deposito.almacenarProducto(producto);
                Thread.sleep(500); // Simula tiempo de producción
            }

            // Producir producto de terminación
            Producto productoFin = new Producto("FIN_" + tipoProducto, nombre);
            deposito.almacenarProducto(productoFin);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


