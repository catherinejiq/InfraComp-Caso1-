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
            Producto producto = depositoDistribucion.retirar(color);
            while (producto == null) {
                synchronized (depositoDistribucion) {
                    try { depositoDistribucion.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                }
                producto = depositoDistribucion.retirar(color);
            }

            if (producto.getId().toString().startsWith("FIN")) {
                break;
            }

            int tiempoDistribucion = ThreadLocalRandom.current().nextInt(50, 501);
            try { sleep(tiempoDistribucion); } catch (InterruptedException e) { e.printStackTrace(); }

            producto.modificarMensaje(", t2: " + tiempoDistribucion + "ms");

            synchronized (depositoDistribucion) {
                depositoDistribucion.notifyAll();
            }
        }
    }
}

