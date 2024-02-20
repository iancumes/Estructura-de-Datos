import java.io.File;
import java.util.List;
public interface IDataSource {
    File saveData(Object data, String path);
    Object loadData(String path);
}