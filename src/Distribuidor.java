import java.util.concurrent.ThreadLocalRandom;

public class Distribuidor extends Thread {
    private String color;
    private Buffer depositoDistribucion;

    public Distribuidor(String color, Buffer depositoDistribucion) {
        this.color = color;
        this.depositoDistribucion = depositoDistribucion;
    }

    public void run() {
        while (true) {
            Producto producto;
            synchronized (depositoDistribucion) {
                producto = depositoDistribucion.retirar();

                // Espera si no hay productos disponibles
                while (producto == null) {
                    try {
                        depositoDistribucion.wait(); // Esperar hasta que haya un producto disponible
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    producto = depositoDistribucion.retirar(); // Intentar de nuevo
                }

                // Verifica si es un producto de fin
                if (producto.getMensaje().startsWith("FIN_")) {
                    System.out.println("Distribuidor de color " + color + " terminó. (FIN_" + color.toUpperCase() + " recibido)");
                    break; // Fin de la distribución, termina el hilo
                }

                depositoDistribucion.notifyAll();  // Notificar a otros hilos que pueden continuar
            }

            // Simular tiempo de distribución
            int tiempoDistribucion = ThreadLocalRandom.current().nextInt(50, 501);
            try {
                sleep(tiempoDistribucion);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Distribuidor de color " + color + " distribuyó producto: " + producto.getMensaje());
        }
    }
}
