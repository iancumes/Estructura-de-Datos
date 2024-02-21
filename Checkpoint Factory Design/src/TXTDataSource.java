import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TXTDataSource implements IDataSource {

    @Override
    public void saveData(List<String[]> data, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            for (String[] record : data) {
                for (String field : record) {
                    writer.write(field + ",");
                }
                writer.write("\n");
            }
            System.out.println("Datos guardados correctamente en el archivo: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos en el archivo: " + path);
        }
    }

    @Override
    public List<String[]> loadData(String path) {
        List<String[]> loadedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                loadedData.add(fields);
            }
            System.out.println("Datos cargados correctamente desde el archivo: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos desde el archivo: " + path);
        }
        return loadedData;
    }
}
