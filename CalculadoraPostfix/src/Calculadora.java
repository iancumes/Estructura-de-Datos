import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Calculadora {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        StackWithVector vectores = new StackWithVector();

        String filePath = "a.txt"; // Reemplaza con la ruta de tu archivo .txt

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");

                for (String token : tokens) {
                    if (token.equals("+") || token.equals("-")) {
                        // Verificar si hay suficientes operandos en la pila
                        if (vectores.count() >= 2) {
                            int n1 = Integer.parseInt(vectores.pop());
                            int n2 = Integer.parseInt(vectores.pop());

                            if (token.equals("+")) {
                                vectores.push(String.valueOf(controlador.add(n2, n1)));
                            } else if (token.equals("-")) {
                                vectores.push(String.valueOf(controlador.substraction(n2, n1)));
                            }
                        } else {
                            // Manejar el caso de no tener suficientes operandos en la pila
                            throw new RuntimeException("No hay suficientes operandos para realizar la operación.");
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

        // Imprimir resultado final que queda en la última posición de la pila
        System.out.println(vectores.peek());
    }
}



