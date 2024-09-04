public class GeneradorProducto {
    private int numId = 1;

    public synchronized int darNumId() {
        return numId++;
    }
}

