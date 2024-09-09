import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Capacidad de los depósitos
        int capDepProd = 10;  // Puedes cambiar esto según tus necesidades
        int capDepDist = 10;  // Puedes cambiar esto según tus necesidades

        // Pedir al usuario el número de productos que va a producir cada productor
        System.out.print("Ingrese el número de productos que producirá cada productor de productos 'azul': ");
        int numProductosAzul = scanner.nextInt();

        System.out.print("Ingrese el número de productos que producirá cada productor de productos 'naranja': ");
        int numProductosNaranja = scanner.nextInt();

        // Crear buffers para la producción y distribución
        Buffer depositoProduccion = new Buffer(capDepProd);
        Buffer depositoDistribucion = new Buffer(capDepDist);
        CintaTransportadora cinta = new CintaTransportadora();
        GeneradorProducto generador = new GeneradorProducto();

        // Crear productores de productos "azul"
        Productor productorAzul1 = new Productor("azul", depositoProduccion, generador, numProductosAzul);
        Productor productorAzul2 = new Productor("azul", depositoProduccion, generador, numProductosAzul);

        // Crear productores de productos "naranja"
        Productor productorNaranja1 = new Productor("naranja", depositoProduccion, generador, numProductosNaranja);
        Productor productorNaranja2 = new Productor("naranja", depositoProduccion, generador, numProductosNaranja);

        // Iniciar hilos productores
        productorAzul1.start();
        productorAzul2.start();
        productorNaranja1.start();
        productorNaranja2.start();

        // Crear distribuidores de productos "azul" y "naranja"
        Distribuidor distribuidorAzul1 = new Distribuidor("azul", depositoDistribucion);
        Distribuidor distribuidorAzul2 = new Distribuidor("azul", depositoDistribucion);
        Distribuidor distribuidorNaranja1 = new Distribuidor("naranja", depositoDistribucion);
        Distribuidor distribuidorNaranja2 = new Distribuidor("naranja", depositoDistribucion);

        // Iniciar hilos distribuidores
        distribuidorAzul1.start();
        distribuidorAzul2.start();
        distribuidorNaranja1.start();
        distribuidorNaranja2.start();

        // Operarios internos
        OperarioInterno operario1 = new OperarioInterno(depositoProduccion, cinta, false); // Mueve a cinta
        OperarioInterno operario2 = new OperarioInterno(depositoDistribucion, cinta, true); // Mueve a distribución
        operario1.start();
        operario2.start();

        // Cerrar el scanner
        scanner.close();
    }
}