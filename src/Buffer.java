import java.util.ArrayList;

public class Buffer {
    private Integer tamanio;
    private ArrayList<Producto> arregloProductos;
    private Integer cantAzules = 0;
    private Integer cantNaranjas = 0;

    public Buffer(Integer tamanio) {
        this.tamanio = tamanio;
        this.arregloProductos = new ArrayList<>();
    }

    public synchronized Boolean almacenar(Producto producto) {
        // Si el depósito está lleno, retornamos true para esperar
        if (this.arregloProductos.size() == this.tamanio) {
            return true; 
        } else {
            this.arregloProductos.add(producto);
            if (producto.getColor().equals("azul")) {
                this.cantAzules++;
            } else {
                this.cantNaranjas++;
            }
            // Notificamos a otros hilos que hay un nuevo producto
            notifyAll();
            return false;
        }
    }

    public synchronized Producto retirar() {
        Producto producto = null;
        while (this.arregloProductos.isEmpty()) {
            try {
                wait(); // Esperar si no hay productos disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejar interrupción
            }
        }
        producto = this.arregloProductos.remove(0); // Retira el primer producto
        // Actualiza el conteo basado en el color del producto retirado
        if (producto.getColor().equals("azul")) {
            this.cantAzules--;
        } else if (producto.getColor().equals("naranja")) {
            this.cantNaranjas--;
        }
        return producto;
    }
}
