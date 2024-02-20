import java.util.List;

public interface IDataSource {
    void saveData(List<String[]> data, String path);
    List<String[]> loadData(String path);
}
