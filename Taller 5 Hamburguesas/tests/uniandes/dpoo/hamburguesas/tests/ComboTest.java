package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

    @Test
    public void testNombreCombo() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        Combo combo = new Combo("Kangrecombo festivo", 0.1, items);
        assertEquals("Kangrecombo festivo", combo.getNombre());
    }

    @Test
    public void testPrecioCombo() {
        ProductoMenu burger = new ProductoMenu("Kangreburger", 12000);
        ProductoMenu papas = new ProductoMenu("Cangrepapas", 5000);
        ProductoMenu soda = new ProductoMenu("Kangrefresco", 3000);

        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(burger);
        items.add(papas);
        items.add(soda);

        Combo combo = new Combo("Kangrecombo festivo", 0.1, items);

        int precioEsperado = (int)(20000 * 0.9); // 18000
        assertEquals(precioEsperado, combo.getPrecio());
    }

    @Test
    public void testTextoFacturaCombo() {
        ProductoMenu burger = new ProductoMenu("Kangreburger", 12000);
        ProductoMenu papas = new ProductoMenu("Cangrepapas", 5000);
        ProductoMenu soda = new ProductoMenu("Kangrefresco", 3000);

        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(burger);
        items.add(papas);
        items.add(soda);

        Combo combo = new Combo("Kangrecombo festivo", 0.1, items);
        String esperado = "Kangrecombo festivo\t18000";
        assertEquals(esperado, combo.generarTextoFactura());
    }
}
