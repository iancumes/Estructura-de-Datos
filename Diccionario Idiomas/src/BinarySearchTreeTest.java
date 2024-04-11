import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Comparator;

public class BinarySearchTreeTest {

    @Test
    public void testInsertAndFind() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        tree.insert(5, "Five");
        tree.insert(3, "Three");
        tree.insert(7, "Seven");
        tree.insert(1, "One");

        assertEquals("Five", tree.find(5));
        assertEquals("Three", tree.find(3));
        assertEquals("Seven", tree.find(7));
        assertEquals("One", tree.find(1));
        assertNull(tree.find(10));
    }

    @Test
    public void testInOrderTraversal() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        tree.insert(5, "Five");
        tree.insert(3, "Three");
        tree.insert(7, "Seven");
        tree.insert(1, "One");

        SaveInArrayListWalk<String> walk = new SaveInArrayListWalk<>();
        tree.InOrderWalk(walk);
        assertEquals("[One, Three, Five, Seven]", walk.getListado().toString());
    }

    @Test
    public void testRemove() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>(Comparator.naturalOrder());
        tree.insert(5, "Five");
        tree.insert(3, "Three");
        tree.insert(7, "Seven");
        tree.insert(1, "One");

        assertEquals("Five", tree.remove(5));
        assertNull(tree.find(5));

        assertEquals("One", tree.remove(1));
        assertNull(tree.find(1));
    }


}
