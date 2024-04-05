import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryProgram {

    public static void main(String[] args) {
        // Crear árboles binarios de búsqueda para cada idioma
        BinarySearchTree<String, Association> englishTree = new BinarySearchTree<>(String.CASE_INSENSITIVE_ORDER);
        BinarySearchTree<String, Association> spanishTree = new BinarySearchTree<>(String.CASE_INSENSITIVE_ORDER);
        BinarySearchTree<String, Association> frenchTree = new BinarySearchTree<>(String.CASE_INSENSITIVE_ORDER);

        // Leer el archivo de diccionario y construir los árboles
        buildDictionaryTrees("diccionario.txt", englishTree, spanishTree, frenchTree);
        // Leer el archivo de texto y traducir las palabras
        translateText("texto.txt", englishTree, spanishTree, frenchTree);

        // Recorrer el árbol de inglés In-order y mostrar las palabras ordenadas
        System.out.println("Palabras ordenadas en inglés:");
        inOrderTraversal(englishTree);

        // Recorrer el árbol de español In-order y mostrar las palabras ordenadas
        System.out.println("\nPalabras ordenadas en español:");
        inOrderTraversalesp(spanishTree);

        // Recorrer el árbol de francés In-order y mostrar las palabras ordenadas
        System.out.println("\nPalabras ordenadas en francés:");
        inOrderTraversalfre(frenchTree);

        // Leer el archivo de texto y traducir las palabras
        System.out.println("Frase traducida");
        translateText("texto.txt", englishTree, spanishTree, frenchTree);
    }

    // Método para construir los árboles del diccionario a partir de un archivo
    private static void buildDictionaryTrees(String fileName,
     BinarySearchTree<String, Association> englishTree,
     BinarySearchTree<String, Association> spanishTree,
      BinarySearchTree<String, Association> frenchTree) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Association association = new Association(parts[0], parts[1], parts[2]);
                    englishTree.insert(parts[0], association);
                    spanishTree.insert(parts[1], association);
                    frenchTree.insert(parts[2], association);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void inOrderTraversalesp(BinarySearchTree<String, Association> tree) {
        ArrayList<String> words = new ArrayList<>();
        tree.InOrderWalk((Association association) -> {
            words.add(association.getSpanish() + " (" + association.getEnglish() + ", " + association.getFrench() + ")");
        });
        for (String word : words) {
            System.out.println(word);
        }
    }
    private static void inOrderTraversal(BinarySearchTree<String, Association> tree) {
        ArrayList<String> words = new ArrayList<>();
        tree.InOrderWalk((Association association) -> {
            words.add(association.getEnglish() + " (" + association.getSpanish() + ", " + association.getFrench() + ")");
        });
        for (String word : words) {
            System.out.println(word);
        }
    }
    private static void inOrderTraversalfre(BinarySearchTree<String, Association> tree) {
        ArrayList<String> words = new ArrayList<>();
        tree.InOrderWalk((Association association) -> {
            words.add(association.getFrench() + " (" + association.getSpanish() + ", " + association.getEnglish() + ")");
        });
        for (String word : words) {
            System.out.println(word);
        }
    }

    // Método para traducir el texto del archivo de entrada
    private static void translateText(String fileName,
     BinarySearchTree<String, Association> englishTree,
     BinarySearchTree<String, Association> spanishTree,
     BinarySearchTree<String, Association> frenchTree) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    translateWord(word, englishTree, spanishTree, frenchTree);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para traducir una palabra y mostrar el resultado
    private static void translateWord(String word,
     BinarySearchTree<String, Association> englishTree,
     BinarySearchTree<String, Association> spanishTree,
     BinarySearchTree<String, Association> frenchTree) {
        Association association = englishTree.find(word);
        if (association != null) {
            System.out.print(association.getSpanish() + " ");
        } else {
            association = spanishTree.find(word);
            if (association != null) {
                System.out.print(association.getEnglish() + " ");
            } else {
                association = frenchTree.find(word);
                if (association != null) {
                    System.out.print(association.getEnglish() + " ");
                } else {
                    System.out.print("*" + word + "* ");
                }
            }
        }
    }
}