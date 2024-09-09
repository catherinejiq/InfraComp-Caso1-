package trabajadoresPreCinta;

import elementos.*;


public class OperarioPre extends Thread {
	
    private final DepositoProduccion deposito;
    private final CintaTransportadora cinta;
    
    private int finAContador = 0; // Iniciar el contador de productos FIN_A en 0
    private int finBContador = 0; // Iniciar el contador de productos FIN_B en 0

    public OperarioPre(DepositoProduccion deposito, CintaTransportadora cinta) {
        this.deposito = deposito;
        this.cinta = cinta;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Producto producto = deposito.retirarProducto(); // Retira productos del depósito
                cinta.colocarEnCinta(producto);
                
                // Verifica si es un producto de fin y cuenta cuántos ha visto
                if (producto.getTipo().equals("FIN_A")) {
                    finAContador++;
                } else if (producto.getTipo().equals("FIN_B")) {
                    finBContador++;
                }

                // El operario termina cuando ha retirado 2 FIN_A y 2 FIN_B
                if (finAContador == 2 && finBContador == 2) {
                    System.out.println("OperarioPre terminó su trabajo.");
                    break; // Sale del ciclo y termina su ejecución
                }

                Thread.sleep(1000); // Simula tiempo de transporte
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
