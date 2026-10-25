
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;

public class ProductoAjustadoTest {

    @Test
    public void testGetNombre() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        assertEquals("Hamburguesa", ajustado.getNombre());
    }

    @Test
    public void testGetPrecio() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        assertEquals(10000, ajustado.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        String factura = ajustado.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("10000"));
    }
    

	@Test
	public void testAgregarIngrediente() {
	    ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
	    ProductoAjustado ajustado = new ProductoAjustado(base);
	
	    Ingrediente queso = new Ingrediente("Queso", 2000);
	    ajustado.agregarIngrediente(queso);
	
	    assertEquals(12000, ajustado.getPrecio());
	    assertTrue(ajustado.generarTextoFactura().contains("+Queso"));
	}
	
	@Test
	public void testEliminarIngrediente() {
	    ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
	    ProductoAjustado ajustado = new ProductoAjustado(base);
	
	    Ingrediente tomate = new Ingrediente("Tomate", 1000);
	    ajustado.eliminarIngrediente(tomate);
	
	    assertEquals(10000, ajustado.getPrecio());
	    assertTrue(ajustado.generarTextoFactura().contains("-Tomate"));
	}

}
