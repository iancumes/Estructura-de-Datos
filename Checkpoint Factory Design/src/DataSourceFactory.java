public class DataSourceFactory {
    public static final int CSV_TYPE = 0;
    public static final int JSON_TYPE = 1;

    public static IDataSource getDataSourceInstance(int formatType) {
        switch (formatType) {
            case CSV_TYPE:
                return new CSVDataSource();
            case JSON_TYPE:
                return new JSONDataSource();
            default:
                throw new IllegalArgumentException("Invalid format type");
        }
    }
}