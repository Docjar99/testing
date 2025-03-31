
package testing.testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestUnitario {

    private Persona persona;
    private Articulo articulo;
    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        persona = new Persona("Ana", 300.00);
        articulo = new Articulo("Laptop", 5, 1000.00);
        inventario = new Inventario(new Articulo[]{articulo});
    }

    // 🔹 Módulo Persona (7 pruebas)
    @Test
    public void testCompraExitosa() {
        assertTrue(persona.comprar(200.00));
    }

    @Test
    public void testCompraFallidaPorSaldoInsuficiente() {
        assertFalse(persona.comprar(500.00));
    }

    @Test
    public void testSaldoReducidoDespuesDeCompra() {
        persona.comprar(100.00);
        assertEquals(200.00, persona.getSaldo());
    }

    @Test
    public void testSaldoNoCambiaSiCompraFalla() {
        double saldoInicial = persona.getSaldo();
        persona.comprar(500.00);
        assertEquals(saldoInicial, persona.getSaldo());
    }

    @Test
    public void testCompraConSaldoExacto() {
        Persona clienteExacto = new Persona("Pedro", 1000.00);
        assertTrue(clienteExacto.comprar(1000.00));
    }

    @Test
    public void testSaldoNoNegativoDespuesDeCompraFallida() {
        Persona clienteSinSaldo = new Persona("Luis", 50.00);
        assertFalse(clienteSinSaldo.comprar(100.00));
        assertEquals(50.00, clienteSinSaldo.getSaldo());
    }

    @Test
    public void testMultiplesComprasReducenSaldoCorrectamente() {
        persona.comprar(50.00);
        persona.comprar(30.00);
        assertEquals(220.00, persona.getSaldo());
    }

    // 🔹 Módulo Articulo (7 pruebas)
    @Test
    public void testVerificarStockDisponible() {
        assertTrue(articulo.verificarStock(3));
    }

    @Test
    public void testVerificarStockInsuficiente() {
        assertFalse(articulo.verificarStock(6));
    }

    @Test
    public void testReducirStock() {
        articulo.reducirStock(2);
        assertEquals(3, articulo.getCantidad());
    }

    @Test
    public void testNoReducirStockSiCompraFalla() {
        int stockInicial = articulo.getCantidad();
        persona.comprar(5000.00);
        assertEquals(stockInicial, articulo.getCantidad());
    }

    @Test
    public void testStockNoNegativo() {
        Articulo sinStock = new Articulo("Mouse", 0, 50.00);
        assertFalse(sinStock.verificarStock(1));
    }

    @Test
    public void testReducirStockA0() {
        Articulo unico = new Articulo("Tablet", 1, 300.00);
        unico.reducirStock(1);
        assertEquals(0, unico.getCantidad());
    }

    @Test
    public void testStockNoCambiaSiCantidadEs0() {
        int stockInicial = articulo.getCantidad();
        articulo.reducirStock(0);
        assertEquals(stockInicial, articulo.getCantidad());
    }

    // 🔹 Módulo Inventario (6 pruebas)
    @Test
    public void testBuscarArticuloExistente() {
        assertNotNull(inventario.buscar("Laptop"));
    }

    @Test
    public void testBuscarArticuloNoExistente() {
        assertNull(inventario.buscar("Monitor"));
    }

    @Test
    public void testBuscarArticuloMayusculasMinusculas() {
        assertNotNull(inventario.buscar("laptop"));
    }

    @Test
    public void testInventarioVacio() {
        Inventario invVacio = new Inventario(new Articulo[]{});
        assertNull(invVacio.buscar("Laptop"));
    }

    @Test
    public void testInventarioConVariosArticulos() {
        Articulo nuevo = new Articulo("Tablet", 10, 300.00);
        Inventario nuevoInventario = new Inventario(new Articulo[]{articulo, nuevo});
        assertNotNull(nuevoInventario.buscar("Tablet"));
    }

    @Test
    public void testInventarioUnicoArticulo() {
        Inventario inv = new Inventario(new Articulo[]{new Articulo("Monitor", 1, 500.00)});
        assertNotNull(inv.buscar("Monitor"));
    }
}
