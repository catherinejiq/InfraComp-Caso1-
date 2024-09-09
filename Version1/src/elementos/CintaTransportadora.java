package elementos;

public class CintaTransportadora {
    private Producto producto; // Solo puede almacenar un producto a la vez
    private boolean disponible = false; // Indica si hay un producto en la cinta

    // Método para colocar un producto en la cinta (sincronizado)
    public synchronized void colocarEnCinta(Producto producto) throws InterruptedException {
        while (disponible) {
            wait(); // Espera hasta que la cinta esté vacía
        }
        this.producto = producto;
        System.out.println("OperarioPre colocó " + producto.getTipo() + " en la cinta.");
        disponible = true;
        notifyAll(); // Notifica que hay un producto disponible
    }

    // Método para retirar un producto de la cinta (sincronizado)
    public synchronized Producto retirarDeCinta() throws InterruptedException {
        while (!disponible) {
            wait(); // Espera hasta que haya un producto en la cinta
        }
        disponible = false;
        System.out.println("OperarioPost retiró " + producto.getTipo() + " de la cinta.");
        notifyAll(); // Notifica que la cinta está libre
        return producto;
    }
}
