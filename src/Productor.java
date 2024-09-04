import java.util.concurrent.ThreadLocalRandom;

public class Productor extends Thread {
    private String color;
    private Buffer depositoProduccion;
    private GeneradorProducto generador;
    private int numProductos;

    public Productor(String color, Buffer depositoProduccion, GeneradorProducto generador, int numProductos) {
        this.color = color;
        this.depositoProduccion = depositoProduccion;
        this.generador = generador;
        this.numProductos = numProductos;
    }

    public void run() {
        for (int i = 0; i < numProductos; i++) {
            Producto producto = new Producto(generador.darNumId(), color, "");
            int tiempoProduccion = ThreadLocalRandom.current().nextInt(50, 501);
            try { sleep(tiempoProduccion); } catch (InterruptedException e) { e.printStackTrace(); }

            producto.modificarMensaje("id: " + producto.getId() + ", color: " + producto.getColor() + ", t1: " + tiempoProduccion + "ms");

            while (depositoProduccion.almacenar(producto)) {
                synchronized (depositoProduccion) {
                    try { depositoProduccion.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }

            synchronized (depositoProduccion) {
                depositoProduccion.notifyAll();
            }
        }
//faltaría poner algo para que se identifique que el proceso de producir un producto terminó pero no sé cómo hacerlo
    }
}
