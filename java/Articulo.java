
package testing.testing;

/**
 *
 * @author jardel
 */
public class Articulo {
    private String descripcion;
    private int cantidad;
    private double precio;

    
    public Articulo(String desc, int c, double pr){
        this.descripcion = desc;
        this.cantidad = c;
        this.precio = pr;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public boolean verificarStock(int compra){
        if(cantidad<compra){
            System.out.println("Stock insuficiente");
            return false;
        }
        System.out.println("Stock disponible: " + this.cantidad);
        return true;
    }
    public void reducirStock(int compra){
        if (this.cantidad < compra) {
            System.out.println("No hay stock disponible");
            return;
        }

        this.cantidad -= compra;
        System.out.println("Nuevo stock disponible: " + this.cantidad);
    }


}
