import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class HeapUsingIterativeBinaryTreeTest {

    @Test
    public void testInsertAndRemove() {
        // Crear un comparador de prioridades
        Comparator<Integer> comparator = new ComparadorNumeros<>();

        // Crear un heap utilizando el árbol binario iterativo
        HeapUsingIterativeBinaryTree<Integer, String> heap = new HeapUsingIterativeBinaryTree<>(comparator);

        // Prueba Insert y remove con elementos
        heap.Insert(10, "A");
        heap.Insert(20, "B");
        heap.Insert(15, "C");

        assertEquals(3, heap.count());

        assertEquals("A", heap.remove());
        assertEquals(2, heap.count());

        assertEquals("C", heap.remove());
        assertEquals(1, heap.count());

        assertEquals("B", heap.remove());
        assertEquals(0, heap.count());
    }

    @Test
    public void testInsertAndRemoveWithEmptyHeap() {
        // Crear un comparador de prioridades
        Comparator<Integer> comparator = new ComparadorNumeros<>();

        // Crear un heap utilizando el árbol binario iterativo
        HeapUsingIterativeBinaryTree<Integer, String> heap = new HeapUsingIterativeBinaryTree<>(comparator);

        // Prueba remove en un heap vacío
        assertNull(heap.remove());
    }


}
