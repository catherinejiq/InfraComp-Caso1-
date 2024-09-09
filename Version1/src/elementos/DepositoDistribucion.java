package elementos;

import java.util.ArrayList;
import java.util.List;


public class DepositoDistribucion {
	private final List<Producto> productos = new ArrayList<>();
    private final int capacidad;

    public DepositoDistribucion(int capacidad) {
        this.capacidad = capacidad;
    }

    // Método sincronizado para almacenar productos
    public synchronized void almacenarProducto(Producto producto) throws InterruptedException {
        while (productos.size() == capacidad) {
            System.out.println("OperarioPost espera, depósito de distribución lleno.");
            wait(); // Espera hasta que haya espacio
        }

        productos.add(producto);
        System.out.println("OperarioPost almacenó " + producto.getTipo() + " en el depósito de distribución.");
        notifyAll(); // Notifica a los distribuidores que ya hay productos disponibles
    }


    // Método sincronizado para retirar productos
 // Método sincronizado para retirar productos
    public synchronized Producto retirarProducto(String tipoProducto, String nombreDistribuidor) throws InterruptedException {
        while (true) {
            // Espera si no hay productos en el depósito
            while (productos.isEmpty()) {
                System.out.println(nombreDistribuidor + " espera, depósito de distribución vacío.");
                wait(); // Espera hasta que se almacenen nuevos productos
            }

            // Busca un producto del tipo solicitado
            for (int i = 0; i < productos.size(); i++) {
                Producto producto = productos.get(i);
                // Si el tipo de producto coincide con el del distribuidor (incluyendo los que terminan con FIN_)
                if (producto.getTipo().equals(tipoProducto) || producto.getTipo().equals("FIN_" + tipoProducto)) {
                    productos.remove(i); // Retira el producto del depósito
                    System.out.println(nombreDistribuidor + " retiró " + producto.getTipo() + " del depósito.");
                    notifyAll(); // Notifica a los operarios que hay espacio disponible
                    return producto; // Devuelve el producto retirado
                }
            }

            // Si no se encontró un producto del tipo requerido, espera
            System.out.println(nombreDistribuidor + " espera, no hay productos del tipo " + tipoProducto + " en el depósito.");
            wait(); // Espera hasta que se agreguen productos del tipo adecuado
        }
    }

    
}
