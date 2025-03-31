
package testing.testing;

/**
 *
 * @author jardel
 */
public class Persona {
    private String nombre;
    private double saldo;
    public Persona(String n, double s){
        this.nombre = n;
        this.saldo = s;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public boolean comprar(double monto){
        if(saldo<monto){
            return false;
        }
        this.saldo-=monto;
        System.out.println("Saldo restante: S/."+this.saldo);
        return true;
    }
}
