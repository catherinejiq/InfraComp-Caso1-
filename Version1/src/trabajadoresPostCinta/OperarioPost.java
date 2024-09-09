package trabajadoresPostCinta;

import elementos.CintaTransportadora;
import elementos.DepositoDistribucion;
import elementos.Producto;

public class OperarioPost extends Thread {
    private final CintaTransportadora cinta;
    private final DepositoDistribucion depositoDistribucion;
    private int finAContador = 0;
    private int finBContador = 0;

    public OperarioPost(CintaTransportadora cinta, DepositoDistribucion depositoDistribucion) {
        this.cinta = cinta;
        this.depositoDistribucion = depositoDistribucion;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Producto producto = cinta.retirarDeCinta();

                // Colocar producto en el depósito de distribución
                depositoDistribucion.almacenarProducto(producto);

                // Verifica si es un producto de fin y cuenta cuántos ha procesado
                if (producto.getTipo().equals("FIN_A")) {
                    finAContador++;
                } else if (producto.getTipo().equals("FIN_B")) {
                    finBContador++;
                }

                if (finAContador == 2 && finBContador == 2) {
                    System.out.println("OperarioPost terminó su trabajo.");
                    break;
                }

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
