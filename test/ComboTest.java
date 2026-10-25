
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

    @Test
    public void testGetNombre() {
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu("Hamburguesa", 10000));
        productos.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo("Combo Especial", 0.10, productos);

        assertEquals("Combo Especial", combo.getNombre());
    }

    @Test
    public void testGetPrecio() {
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu("Hamburguesa", 10000));
        productos.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo("Combo Especial", 0.10, productos);

        assertEquals(13500, combo.getPrecio());
    }

    @Test
    public void testSinDescuento() {
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu("Hamburguesa", 10000));
        productos.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo("Combo Normal", 0.0, productos);

        assertEquals(15000, combo.getPrecio());
    }

    @Test
    public void testDescuentoTotal() {
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu("Hamburguesa", 10000));

        Combo combo = new Combo("Combo Gratis", 1.0, productos);

        assertEquals(0, combo.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add(new ProductoMenu("Hamburguesa", 10000));
        productos.add(new ProductoMenu("Papas", 5000));

        Combo combo = new Combo("Combo Especial", 0.10, productos);

        String factura = combo.generarTextoFactura();

        assertTrue(factura.contains("Combo Especial"));
        assertTrue(factura.contains("0.1"));
        assertTrue(factura.contains("13500"));
    }
}

