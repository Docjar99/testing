
package testing.testing;

/**
 *
 * @author jardel
 */
public class Inventario {
    private Articulo [] articulos;
    public Inventario(Articulo[] art){
        this.articulos = art;
    }
    public Articulo buscar(String nombre){
        for(Articulo art : articulos){
            if(art.getDescripcion().equalsIgnoreCase(nombre)){
                return art;
            }
        }
        return null;
    }
} 
