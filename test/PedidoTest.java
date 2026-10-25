
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    @Test
    public void testGetNombreCliente() {
        Pedido pedido = new Pedido("Catalina", "Bogota");

        assertEquals("Catalina", pedido.getNombreCliente());
    }

    @Test
    public void testGetIdPedido() {
        Pedido pedido1 = new Pedido("Ana", "Bogota");
        Pedido pedido2 = new Pedido("Juan", "Medellin");

        assertEquals(pedido1.getIdPedido() + 1,
                     pedido2.getIdPedido());
    }

    @Test
    public void testPrecioPedidoVacio() {
        Pedido pedido = new Pedido("Ana", "Bogota");

        assertEquals(0, pedido.getPrecioTotalPedido());
    }

    @Test
    public void testAgregarProducto() {
        Pedido pedido = new Pedido("Ana", "Bogota");

        ProductoMenu producto =
            new ProductoMenu("Hamburguesa", 10000);

        pedido.agregarProducto(producto);

        assertEquals(11900, pedido.getPrecioTotalPedido());
    }

    @Test
    public void testPrecioTotalPedido() {
        Pedido pedido = new Pedido("Ana", "Bogota");

        pedido.agregarProducto(
            new ProductoMenu("Hamburguesa", 10000));

        pedido.agregarProducto(
            new ProductoMenu("Papas", 5000));

        assertEquals(17850, pedido.getPrecioTotalPedido());
    }

    @Test
    public void testGenerarTextoFactura() {
        Pedido pedido = new Pedido("Ana", "Bogota");

        pedido.agregarProducto(
            new ProductoMenu("Hamburguesa", 10000));

        String factura = pedido.generarTextoFactura();

        assertAll(
            () -> assertTrue(factura.contains("Ana")),
            () -> assertTrue(factura.contains("Bogota")),
            () -> assertTrue(factura.contains("Hamburguesa")),
            () -> assertTrue(factura.contains("10000")),
            () -> assertTrue(factura.contains("1900")),
            () -> assertTrue(factura.contains("11900"))
        );
    }

    @Test
    public void testGuardarFactura(@TempDir File carpeta)
            throws IOException {

        Pedido pedido = new Pedido("Ana", "Bogota");

        pedido.agregarProducto(
            new ProductoMenu("Hamburguesa", 10000));

        File archivo = new File(carpeta, "factura.txt");

        pedido.guardarFactura(archivo);

        assertTrue(archivo.exists());

        String contenido = Files.readString(archivo.toPath());

        assertEquals(pedido.generarTextoFactura(), contenido);
    }
}
