import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Calculadora {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        StackWithVector vectores = new StackWithVector();

        String filePath = "C:\\Users\\luisy\\OneDrive\\Escritorio\\a.txt"; // Reemplaza con la ruta de tu archivo .txt

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Vector<String> datos = new Vector<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    vectores.push(token);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }
