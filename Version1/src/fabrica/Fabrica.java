package fabrica;

import elementos.*;
import trabajadoresPostCinta.*;
import trabajadoresPreCinta.*;
import java.util.Scanner;

public class Fabrica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario los valores para las variables
        System.out.print("Ingrese la capacidad del depósito de producción: ");
        int capDepProd = scanner.nextInt();

        System.out.print("Ingrese la capacidad del depósito de distribución: ");
        int capDepDist = scanner.nextInt();

        System.out.print("Ingrese el número de productos a producir por cada productor: ");
        int numProductos = scanner.nextInt();

        // Crear los depósitos y la cinta transportadora
        DepositoProduccion depositoProduccion = new DepositoProduccion(capDepProd);
        CintaTransportadora cinta = new CintaTransportadora();
        DepositoDistribucion depositoDistribucion = new DepositoDistribucion(capDepDist);

        // Creación de productores
        Productor productorA1 = new Productor(depositoProduccion, "A", "Productor A1", numProductos);
        Productor productorA2 = new Productor(depositoProduccion, "A", "Productor A2", numProductos);
        Productor productorB1 = new Productor(depositoProduccion, "B", "Productor B1", numProductos);
        Productor productorB2 = new Productor(depositoProduccion, "B", "Productor B2", numProductos);

        // Creación de operarios
        OperarioPre operarioPre = new OperarioPre(depositoProduccion, cinta);
        OperarioPost operarioPost = new OperarioPost(cinta, depositoDistribucion);

        // Creación de distribuidores
        Distribuidor distribuidorA1 = new Distribuidor(depositoDistribucion, "A", "Distribuidor A1");
        Distribuidor distribuidorA2 = new Distribuidor(depositoDistribucion, "A", "Distribuidor A2");
        Distribuidor distribuidorB1 = new Distribuidor(depositoDistribucion, "B", "Distribuidor B1");
        Distribuidor distribuidorB2 = new Distribuidor(depositoDistribucion, "B", "Distribuidor B2");

        // Iniciar todos los hilos
        productorA1.start();
        productorA2.start();
        productorB1.start();
        productorB2.start();
        operarioPre.start();
        operarioPost.start();
        distribuidorA1.start();
        distribuidorA2.start();
        distribuidorB1.start();
        distribuidorB2.start();

        // Cerrar el scanner al final
        scanner.close();
    }
}
