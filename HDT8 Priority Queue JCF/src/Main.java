import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // Crear el comparador de prioridades
        Comparator<Proceso> comparator = Comparator.comparingInt(Proceso::getPriority);

        // Crear el PriorityQueue utilizando el comparador de prioridades
        PriorityQueue<Proceso> priorityQueue = new PriorityQueue<>(comparator);

        // Leer los procesos del archivo y agregarlos al PriorityQueue
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
                priorityQueue.add(proceso);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Procesar los procesos del PriorityQueue
        while (!priorityQueue.isEmpty()) {
            Proceso proceso = priorityQueue.poll();
            System.out.println(proceso);
        }
    }
}
