package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {

    @Test
    public void testPrecioSinIngredientes() {
        ProductoMenu base = new ProductoMenu("Kangreburger deluxe", 15000);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        assertEquals(15000, ajustado.getPrecio());
    }

    @Test
    public void testAgregarIngredientes() {
        ProductoMenu base = new ProductoMenu("Kangreburger deluxe", 15000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        Ingrediente quesoBurbuja = new Ingrediente("Queso burbuja", 2000);
        Ingrediente mermeladaPicante = new Ingrediente("Mermelada picante", 3500);

        ajustado.agregarIngrediente(quesoBurbuja);
        ajustado.agregarIngrediente(mermeladaPicante);

        assertEquals(20500, ajustado.getPrecio());
    }

    @Test
    public void testNombreConIngredientes() {
        ProductoMenu base = new ProductoMenu("Kangreburger deluxe", 15000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        ajustado.agregarIngrediente(new Ingrediente("Queso burbuja", 2000));
        ajustado.eliminarIngrediente(new Ingrediente("Cebolla mutante", 0));

        String nombre = ajustado.getNombre();
        assertTrue(nombre.contains("Kangreburger deluxe"));
        assertTrue(nombre.contains("+ Queso burbuja"));
        assertTrue(nombre.contains("- Cebolla mutante"));
    }

    @Test
    public void testTextoFactura() {
        ProductoMenu base = new ProductoMenu("Kangresándwich", 10000);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        ajustado.agregarIngrediente(new Ingrediente("Mayonesa alienígena", 2500));

        String esperado = "Kangresándwich + Mayonesa alienígena\t12500";
        assertEquals(esperado, ajustado.generarTextoFactura());
    }
}
