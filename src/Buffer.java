import java.util.ArrayList;

public class Buffer {
    private Integer tamanio;
    private ArrayList<Producto> arregloProductos;
    private Integer cantAzules = 0;
    private Integer cantNaranjas = 0;

    public Buffer(Integer tamanio) {
        this.tamanio = tamanio;
        this.arregloProductos = new ArrayList<>();
    }

    public synchronized Boolean almacenar(Producto producto) {
        if (this.arregloProductos.size() == this.tamanio) {
            return true; 
        } else {
            this.arregloProductos.add(producto);
            if (producto.getColor().equals("azul")) {
                this.cantAzules++;
            } else {
                this.cantNaranjas++;
            }
            return false;
        }
    }
/** 
    public synchronized Producto retirar(String color) {
        Producto producto = null;
        if (color.equals("azul") && this.cantAzules > 0) {
            for (int i = 0; i < this.arregloProductos.size(); i++) {
                if (this.arregloProductos.get(i).getColor().equals("azul")) {
                    producto = this.arregloProductos.remove(i);
                    this.cantAzules--;
                    break;
                }
                
            }
        } else if (color.equals("naranja") && this.cantNaranjas > 0) {
            for (int i = 0; i < this.arregloProductos.size(); i++) {
                if (this.arregloProductos.get(i).getColor().equals("naranja")) {
                    producto = this.arregloProductos.remove(i);
                    this.cantNaranjas--;
                    break;
                }
            }
        }
        return producto;
    }
    */

    public synchronized Producto buscarProducto(Integer id) {
        Producto producto = null;
        for (int i = 0; i < arregloProductos.size(); i++) {
            if (arregloProductos.get(i).getId().equals(id)) {
                producto = arregloProductos.remove(i);
                break;
            }
        }
        return producto;
    }
}

