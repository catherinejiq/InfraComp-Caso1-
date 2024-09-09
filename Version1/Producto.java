public class Producto {
    private Integer id;
    private String color;
    private String mensaje;

    public Producto(Integer id, String color, String mensaje) {
        this.id = id;
        this.color = color;
        this.mensaje = mensaje;
    }

    public Integer getId() { return id; }
    public String getColor() { return color; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void modificarMensaje(String nuevoMensaje) { this.mensaje += nuevoMensaje; }
}