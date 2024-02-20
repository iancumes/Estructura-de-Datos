import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVDataSource implements IDataSource {

    @Override
    public void saveData(List<String[]> data, String path) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (String[] row : data) {
                StringBuilder rowBuilder = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    rowBuilder.append(row[i]);
                    if (i < row.length - 1) {
                        rowBuilder.append(",");
                    }
                }
                writer.println(rowBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String[]> loadData(String path) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
