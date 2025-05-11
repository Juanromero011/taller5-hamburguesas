package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testCargarInformacion() throws IOException, HamburguesaException {
        crearArchivo("ingredientes.txt", "Queso burbuja;2000");
        crearArchivo("menu.txt", "Kangreburger;12000");
        crearArchivo("combos.txt", "Combo Kangre;0.1;Kangreburger");

        restaurante.cargarInformacionRestaurante(new File("."));
        assertFalse(restaurante.getMenuBase().isEmpty());
        assertFalse(restaurante.getIngredientes().isEmpty());
        assertFalse(restaurante.getCombos().isEmpty());
    }

    @Test
    public void testFlujoCompletoPedido() throws Exception {
        crearArchivo("ingredientes.txt", "Queso burbuja;2000");
        crearArchivo("menu.txt", "Kangreburger;12000");
        crearArchivo("combos.txt", "");

        restaurante.cargarInformacionRestaurante(new File("."));
        restaurante.iniciarPedido("Bob Esponja", "Piña bajo el mar");
        restaurante.agregarProductoAlPedido(restaurante.getMenuBase().get(0));
        restaurante.cerrarYGuardarPedido();

        assertNotNull(restaurante.getPedidos().get(0));
    }

    @Test
    public void testExcepcionesPedido() throws Exception {
        crearArchivo("ingredientes.txt", "");
        crearArchivo("menu.txt", "Kangreburger;12000");
        crearArchivo("combos.txt", "");
        restaurante.cargarInformacionRestaurante(new File("."));

        restaurante.iniciarPedido("Plankton", "Balde de Carnada");

        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
            restaurante.iniciarPedido("Duplicado", "Lugar X");
        });

        restaurante.cerrarYGuardarPedido();

        assertThrows(NoHayPedidoEnCursoException.class, () -> {
            restaurante.agregarProductoAlPedido(restaurante.getMenuBase().get(0));
        });
    }

    private void crearArchivo(String nombre, String contenido) throws IOException {
        File archivo = new File(nombre);
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
        }
        archivo.deleteOnExit();
    }
}

