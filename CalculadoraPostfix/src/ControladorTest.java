import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ControladorTest {

    @Test
    public void testAdd() {
        Controlador controlador = new Controlador();
        int result = controlador.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testSubstraction() {
        Controlador controlador = new Controlador();
        int result = controlador.substraction(5, 3);
        assertEquals(2, result);
    }

    @Test
    public void testMultiplication() {
        Controlador controlador = new Controlador();
        int result = controlador.multiplication(2, 3);
        assertEquals(6, result);
    }

    @Test
    public void testDivision() throws Exception {
        Controlador controlador = new Controlador();
        int result = controlador.division(6, 3);
        assertEquals(2, result);
    }

    @Test(expected = Exception.class)
    public void testDivisionByZero() throws Exception {
        Controlador controlador = new Controlador();
        controlador.division(5, 0);
    }

    @Test
    public void testResidue() throws Exception {
        Controlador controlador = new Controlador();
        int result = controlador.residue(7, 3);
        assertEquals(0, result);
    }

    @Test
   public void testRead() throws Exception {
    Controlador controlador = new Controlador();

    try {
        ArrayList<Character> result = controlador.read("12345");

        // Verificar que el resultado no sea null
        

        // Resto del código de verificación
    } catch (Exception e) {
        fail("Excepción inesperada: " + e.getMessage());
    }
}
    @Test
    public void testSolve() throws Exception {
        Controlador controlador = new Controlador();
        ArrayList<Character> elements = new ArrayList<>();
        elements.add('1');
        elements.add('2');
        elements.add('+');
        elements.add('4');
        elements.add('*');
        elements.add('3');
        elements.add('+');

        int result = controlador.solve(elements);
        assertEquals(0, result);
    }

    // Add more tests for other methods or edge cases if needed
}
