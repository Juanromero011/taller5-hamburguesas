package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

    @Test
    public void testAgregarYPrecios() {
        Pedido pedido = new Pedido("Patricio Estrella", "Fondo de Bikini #13");
        ProductoMenu burger = new ProductoMenu("Kangreburger", 12000);
        ProductoMenu papas = new ProductoMenu("Cangrepapas", 5000);

        pedido.agregarProducto(burger);
        pedido.agregarProducto(papas);

        assertEquals(17000, pedido.getPrecioNetoPedido());
        assertEquals((int)(17000 * 0.19), pedido.getPrecioIVAPedido());
        assertEquals(17000 + (int)(17000 * 0.19), pedido.getPrecioTotalPedido());
    }

    @Test
    public void testGenerarTextoFactura() {
        Pedido pedido = new Pedido("Calamardo Tentáculos", "Casa de al lado");
        ProductoMenu soda = new ProductoMenu("Kangrefresco", 3000);
        pedido.agregarProducto(soda);

        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Kangrefresco"));
        assertTrue(factura.contains("3000"));
        assertTrue(factura.contains("IVA"));
        assertTrue(factura.contains("TOTAL"));
    }

    @Test
    public void testGuardarFactura() throws IOException {
        Pedido pedido = new Pedido("Arenita Mejillas", "Domo submarino");
        ProductoMenu producto = new ProductoMenu("Kangrebatido", 7000);
        pedido.agregarProducto(producto);

        File tempFile = File.createTempFile("factura-test", ".txt");
        tempFile.deleteOnExit();

        pedido.guardarFactura(tempFile);

        List<String> lines = Files.readAllLines(tempFile.toPath());
        assertTrue(lines.stream().anyMatch(line -> line.contains("Kangrebatido")));
        assertTrue(lines.stream().anyMatch(line -> line.contains("7000")));
    }
}
