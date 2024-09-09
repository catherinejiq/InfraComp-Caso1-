package elementos;

import java.util.LinkedList;
import java.util.Queue;


public class DepositoProduccion {
    private final Queue<Producto> productos = new LinkedList<>();
    private final int capacidad;

    public DepositoProduccion(int capacidad) {
        this.capacidad = capacidad;
    }

    // Método sincronizado para almacenar productos
    public synchronized void almacenarProducto(Producto producto) throws InterruptedException {
        // Espera pasiva si el depósito está lleno
        while (productos.size() == capacidad) {
            System.out.println(producto.getProductor() + " espera, depósito lleno.");
            wait(); // Espera hasta que haya espacio
        }
        
        // Agregar el producto al depósito
        productos.add(producto);
        System.out.println(producto.getProductor() + " almacenó " + producto.getTipo());

        // Notificar a otros hilos que están esperando
        notifyAll();
    }

    // Método sincronizado para retirar productos
    public synchronized Producto retirarProducto() throws InterruptedException {
        // Espera pasiva si el depósito está vacío
        while (productos.isEmpty()) {
            System.out.println("Depósito vacío. Operario espera.");
            wait(); // Espera hasta que haya productos
        }

        // Retira el producto del depósito
        Producto producto = productos.poll();
        System.out.println("OperarioPre retiró " + producto.getTipo());

        // Notificar a otros hilos que pueden estar esperando para almacenar
        notifyAll();

        return producto;
    }
}