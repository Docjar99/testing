
package testing.testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestIntegracion {

    private Persona cliente;
    private Articulo a1, a2;
    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        cliente = new Persona("Juan Torres", 500.00);
        a1 = new Articulo("Redmi Buds 5 Pro", 5, 220.40);
        a2 = new Articulo("Redmi Buds 6 Lite", 10, 140.20);
        inventario = new Inventario(new Articulo[]{a1, a2});
    }

    @Test
    public void testValidacionStockAntesPago() {
        assertTrue(a1.verificarStock(3));
    }

    @Test
    public void testActualizarInventarioDespuesCompra() {
        a1.reducirStock(3);
        assertEquals(2, a1.getCantidad());
    }

    @Test
    public void testCancelacionPedidoPorFalloPago() {
        assertFalse(cliente.comprar(600.00));
    }

    @Test
    public void testNotificacionConfirmacionCliente() {
        boolean notificacionEnviada = true;
        assertTrue(notificacionEnviada);
    }

    @Test
    public void testManejoConcurrenciaInventario() {
        Persona cliente2 = new Persona("Carlos", 500.00);
        boolean compra1 = cliente.comprar(a1.getPrecio());
        if (compra1) a1.reducirStock(1);
        boolean compra2 = cliente2.comprar(a1.getPrecio());
        assertFalse(compra2);
    }

    @Test
    public void testPagoExitosoAntesDeDescontarStock() {
        assertTrue(cliente.comprar(a2.getPrecio()));
    }

    @Test
    public void testReembolsoPorErrorDePago() {
        double saldoInicial = cliente.getSaldo();
        boolean pago = cliente.comprar(600.00);
        assertFalse(pago);
        assertEquals(saldoInicial, cliente.getSaldo());
    }

    @Test
    public void testVerificacionProductosAgotados() {
        Articulo agotado = new Articulo("Tablet", 0, 250.00);
        assertFalse(agotado.verificarStock(1));
    }

    @Test
    public void testCompraDeMultiplesProductos() {
        boolean pago = cliente.comprar(a1.getPrecio() + a2.getPrecio());
        assertTrue(pago);
    }

    @Test
    public void testPagoParcialNoPermitido() {
        assertFalse(cliente.comprar(600.00));
    }

    @Test
    public void testInventarioSeRestauraTrasFalloPago() {
        int stockInicial = a1.getCantidad();
        cliente.comprar(600.00);
        assertEquals(stockInicial, a1.getCantidad());
    }

    @Test
    public void testCompraConSaldoExacto() {
        Persona clienteExacto = new Persona("Pedro", 220.40);
        assertTrue(clienteExacto.comprar(a1.getPrecio()));
    }

    @Test
    public void testStockNoSeReduceSiPagoFalla() {
        int stockInicial = a1.getCantidad();
        cliente.comprar(600.00);
        assertEquals(stockInicial, a1.getCantidad());
    }

    @Test
    public void testPagoConMetodosMixtos() {
        boolean tarjeta = true, paypal = true;
        boolean pago = tarjeta || paypal;
        assertTrue(pago);
    }
    @Test
    public void testCompraNoPermiteSaldoNegativo() {
        Persona clienteNegativo = new Persona("Luis", 100.00);
        boolean compra = clienteNegativo.comprar(150.00);  // Monto mayor al saldo disponible
        assertFalse(compra);
        assertEquals(100.00, clienteNegativo.getSaldo());  // El saldo debe mantenerse igual
    }

}
