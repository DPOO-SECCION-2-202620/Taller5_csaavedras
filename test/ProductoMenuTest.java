import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoMenuTest {

	@Test
	public void testGetNombre() {
		ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        assertEquals("Hamburguesa", producto.getNombre());
	}
	
	@Test
	public void testGetPrecio() {
		ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        assertEquals(10000, producto.getPrecio());
	}
	
	@Test
	public void testGenerarTextoFactura() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
        String esperado = "Hamburguesa\n" + "            15000\n";

        assertEquals(esperado, producto.generarTextoFactura());
	}

}
