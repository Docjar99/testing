
package testing.testing;
import java.util.Scanner;

/**
 *
 * @author jardel
 */
public class Testing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Persona p = new Persona("Juan Torres",250.40);    
        Articulo a1 = new Articulo("Redmi Buds 5 Pro",5,220.40);
        Articulo a2 = new Articulo("Redmi Buds 6 Lite",10,140.20);
        Inventario inventario = new Inventario(new Articulo[]{a1,a2});
        
        System.out.println("Ingrese el artículo que desee comprar: ");
        String articulo = sc.nextLine();
        
        System.out.println("Ingrese la cantidad que desee comprar: ");
        int cantidad = sc.nextInt();
        Articulo art = inventario.buscar(articulo);

        
        if (art == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }

        double total = art.getPrecio() * cantidad;

        if (!art.verificarStock(cantidad)) {
            System.out.println("No hay stock suficiente.");
            return;
        }
        
        System.out.println("Total a Pagar: S/."+total);
        if (!p.comprar(total)) {
            System.out.println("Pago fallido: saldo insuficiente.");
            return;
        }
        
        art.reducirStock(cantidad);
        System.out.println("Compra exitosa. Se ha procesado el pago.");
    }
    
    
    
}
