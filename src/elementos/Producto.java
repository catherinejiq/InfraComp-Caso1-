package elementos;

public class Producto {
    private final String tipo;
    private final String productor;

    public Producto(String tipo, String productor) {
        this.tipo = tipo;
        this.productor = productor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getProductor() {
        return productor;
    }
}
