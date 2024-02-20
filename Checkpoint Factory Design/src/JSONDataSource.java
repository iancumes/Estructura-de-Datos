import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONDataSource implements IDataSource {

    private final ObjectMapper objectMapper;

    public JSONDataSource() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void saveData(List<String[]> data, String path) {
        try {
            objectMapper.writeValue(new File(path), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String[]> loadData(String path) {
        try {
            return objectMapper.readValue(new File(path), new TypeReference<List<String[]>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
