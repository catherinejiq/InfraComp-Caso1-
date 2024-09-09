public class OperarioInterno extends Thread {
    private Buffer deposito;
    private CintaTransportadora cinta;
    private boolean moverADistribucion;

    public OperarioInterno(Buffer deposito, CintaTransportadora cinta, boolean moverADistribucion) {
        this.deposito = deposito;
        this.cinta = cinta;
        this.moverADistribucion = moverADistribucion;
    }

    public void run() {
        while (true) {
            Producto producto = null;
            if (moverADistribucion) {
                synchronized (cinta) {
                    while ((producto = cinta.retirar()) == null || producto.getMensaje().startsWith("FIN_")) {
                        try {
                            cinta.wait(); // Esperar si no hay productos disponibles o si el mensaje indica fin
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // Manejar interrupción
                        }
                    }
                    cinta.notifyAll(); // Notificar a otros hilos que pueden estar esperando en cinta
                }

                synchronized (deposito) {
                    deposito.almacenar(producto);
                    deposito.notifyAll(); // Notificar a otros hilos que pueden estar esperando en deposito
                }

            } else {
                synchronized (deposito) {
                    while ((producto = deposito.retirar()) == null) {
                        try {
                            deposito.wait(); // Esperar si no hay productos disponibles
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // Manejar interrupción
                        }
                    }
                    deposito.notifyAll(); // Notificar a otros hilos que pueden estar esperando en deposito
                }

                synchronized (cinta) {
                    cinta.almacenar(producto);
                    cinta.notifyAll(); // Notificar a otros hilos que pueden estar esperando en cinta
                }
            }
        }
    }
}
