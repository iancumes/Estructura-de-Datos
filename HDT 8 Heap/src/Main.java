import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Crear el comparador de prioridades
        Comparator<Integer> comparator = new ComparadorNumeros<>();

        // Crear el heap utilizando el árbol binario iterativo
        HeapUsingIterativeBinaryTree<Integer, Proceso> heap = new HeapUsingIterativeBinaryTree<>(comparator);

        // Leer los procesos del archivo y agregarlos al heap
        try {
            BufferedReader reader = new BufferedReader(new FileReader("procesos.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String nombre = parts[0];
                String usuario = parts[1];
                int nice = Integer.parseInt(parts[2]);
                int priority = 20 + nice;
                Proceso proceso = new Proceso(nombre, usuario, nice, priority);
                heap.Insert(priority, proceso);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Procesar los procesos del heap
        while (!heap.isEmpty()) {
            Proceso proceso = heap.remove();
            System.out.println(proceso);
        }
    }
}
