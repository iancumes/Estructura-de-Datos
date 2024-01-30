import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Calculadora {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        StackWithVector vectores = new StackWithVector();

        String filePath = "C:\\Users\\Usuario\\Downloads\\a.txt"; // Reemplaza con la ruta de tu archivo .txt

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    if (token.equals("+")) {
                        if (vectores.count() >= 2) {
                            int n1 = Integer.parseInt(vectores.pop());
                            int n2 = Integer.parseInt(vectores.pop());
                            String n3 = String.valueOf(controlador.add(n1, n2));
                            vectores.push(n3);
                        } else {
                            throw new RuntimeException("No hay mas valores :(");
                        }
                    } else {
                        vectores.push(token);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(vectores.peek());
    }
}


