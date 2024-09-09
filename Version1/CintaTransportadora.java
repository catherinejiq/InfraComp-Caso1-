public class CintaTransportadora {
    private Producto producto;

    public synchronized void almacenar(Producto producto) {
        while (this.producto != null) {
            try {
                wait(); // Esperar si la cinta ya tiene un producto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.producto = producto;
        notifyAll(); // Notificar a otros hilos que el producto ha sido almacenado
    }

    public synchronized Producto retirar() {
        while (this.producto == null) {
            try {
                wait(); // Esperar si no hay productos en la cinta
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto p = this.producto;
        this.producto = null;
        notifyAll(); // Notificar a otros hilos que el producto ha sido retirado
        return p;
    }
}