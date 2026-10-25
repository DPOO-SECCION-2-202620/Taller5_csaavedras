
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.io.TempDir;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;


public class RestauranteTest {

    @Test
    public void testRestauranteVacio() {
        Restaurante restaurante = new Restaurante();

        assertTrue(restaurante.getPedidos().isEmpty());
        assertTrue(restaurante.getMenuBase().isEmpty());
        assertTrue(restaurante.getMenuCombos().isEmpty());
        assertTrue(restaurante.getIngredientes().isEmpty());
        assertNull(restaurante.getPedidoEnCurso());
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        Restaurante restaurante = new Restaurante();

        restaurante.iniciarPedido("Ana", "Bogota");

        Pedido pedido = restaurante.getPedidoEnCurso();

        assertNotNull(pedido);
        assertEquals("Ana", pedido.getNombreCliente());
    }

    @Test
    public void testDosPedidosSimultaneos()
            throws YaHayUnPedidoEnCursoException {

        Restaurante restaurante = new Restaurante();

        restaurante.iniciarPedido("Ana", "Bogota");

        assertThrows(YaHayUnPedidoEnCursoException.class,() -> restaurante.iniciarPedido("Juan", "Medellin")
        );
    }

    @Test
    public void testCerrarSinPedido() {
        Restaurante restaurante = new Restaurante();

        assertThrows(NoHayPedidoEnCursoException.class,() -> restaurante.cerrarYGuardarPedido()
        );
    }

    @Test
    public void testGetPedidos() {
        Restaurante restaurante = new Restaurante();

        assertEquals(0, restaurante.getPedidos().size());
    }
    

@Test
public void testCerrarPedido() throws Exception {
    Restaurante restaurante = new Restaurante();

    restaurante.iniciarPedido("Ana", "Bogota");

    // Crear la carpeta de facturas si no existe
    new File("./facturas").mkdirs();

    restaurante.cerrarYGuardarPedido();

    assertNull(restaurante.getPedidoEnCurso());
}

	@Test
	public void testHistorialPedidos() throws Exception {
	    Restaurante restaurante = new Restaurante();
	
	    restaurante.iniciarPedido("Ana", "Bogota");
	
	    new File("./facturas").mkdirs();
	
	    restaurante.cerrarYGuardarPedido();
	
	    assertEquals(1, restaurante.getPedidos().size());
	}
	

	@Test
	public void testCargarInformacionRestaurante(@TempDir Path carpeta)
	        throws Exception {
	
	    Path ingredientes = carpeta.resolve("ingredientes.txt");
	    Path menu = carpeta.resolve("menu.txt");
	    Path combos = carpeta.resolve("combos.txt");
	
	    Files.writeString(ingredientes,
	            "Queso;2000\nTomate;1000\n");
	
	    Files.writeString(menu,
	            "Hamburguesa;10000\nPapas;5000\n");
	
	    Files.writeString(combos,
	            "Combo Especial;10%;Hamburguesa;Papas\n");
	
	    Restaurante restaurante = new Restaurante();
	
	    restaurante.cargarInformacionRestaurante(ingredientes.toFile(),menu.toFile(),combos.toFile());
	
	    assertEquals(2, restaurante.getIngredientes().size());
	    assertEquals(2, restaurante.getMenuBase().size());
	    assertEquals(1, restaurante.getMenuCombos().size());
	
	    assertEquals("Queso",restaurante.getIngredientes().get(0).getNombre());
	
	    assertEquals("Hamburguesa",restaurante.getMenuBase().get(0).getNombre());
	
	    assertEquals("Combo Especial",restaurante.getMenuCombos().get(0).getNombre());
	}
	

	@Test
	public void testIngredienteRepetido(@TempDir Path carpeta)
	        throws Exception {
	
	    Path ingredientes = carpeta.resolve("ingredientes.txt");
	    Path menu = carpeta.resolve("menu.txt");
	    Path combos = carpeta.resolve("combos.txt");
	
	    Files.writeString(ingredientes,
	            "Queso;2000\nQueso;3000\n");
	
	    Files.writeString(menu,
	            "Hamburguesa;10000\n");
	
	    Files.writeString(combos,
	            "Combo Especial;10%;Hamburguesa\n");
	
	    Restaurante restaurante = new Restaurante();
	
	    assertThrows(
	        IngredienteRepetidoException.class,() -> restaurante.cargarInformacionRestaurante(ingredientes.toFile(),menu.toFile(),combos.toFile()));
	}
	
	
	@Test
	public void testProductoRepetido(@TempDir Path carpeta)
	        throws Exception {
	
	    Path ingredientes = carpeta.resolve("ingredientes.txt");
	    Path menu = carpeta.resolve("menu.txt");
	    Path combos = carpeta.resolve("combos.txt");
	
	    Files.writeString(ingredientes, "Queso;2000\n");
	
	    Files.writeString(menu,
	            "Hamburguesa;10000\nHamburguesa;15000\n");
	
	    Files.writeString(combos,
	            "Combo Especial;10%;Hamburguesa\n");
	
	    Restaurante restaurante = new Restaurante();
	
	    assertThrows(
	        ProductoRepetidoException.class,() -> restaurante.cargarInformacionRestaurante(ingredientes.toFile(),menu.toFile(),combos.toFile()));
	}

	@Test
	public void testProductoFaltante(@TempDir Path carpeta)
	        throws Exception {
	
	    Path ingredientes = carpeta.resolve("ingredientes.txt");
	    Path menu = carpeta.resolve("menu.txt");
	    Path combos = carpeta.resolve("combos.txt");
	
	    Files.writeString(ingredientes, "Queso;2000\n");
	
	    Files.writeString(menu, "Hamburguesa;10000\n");
	
	    Files.writeString(combos,
	            "Combo Especial;10%;Hamburguesa;Papas\n");
	
	    Restaurante restaurante = new Restaurante();
	
	    assertThrows(ProductoFaltanteException.class,() -> restaurante.cargarInformacionRestaurante(ingredientes.toFile(),menu.toFile(),combos.toFile()));
	}

	


}
