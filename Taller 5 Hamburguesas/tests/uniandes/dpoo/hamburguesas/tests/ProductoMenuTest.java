package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

    @Test
    public void testConstructorYGetters() {
        ProductoMenu producto = new ProductoMenu("Kangreburger", 12000);
        assertEquals("Kangreburger", producto.getNombre());
        assertEquals(12000, producto.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        ProductoMenu producto = new ProductoMenu("Cangrepapas", 5000);
        String esperado = "Cangrepapas\t5000";
        assertEquals(esperado, producto.generarTextoFactura());
    }
}
