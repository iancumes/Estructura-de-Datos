public class DataSourceFactory {
    public static final int CSV_TYPE = 0;
    public static final int JSON_TYPE = 1;
    public static final int TXT_TYPE = 2; // Agregamos un nuevo tipo para XML

    public static IDataSource getDataSourceInstance(int formatType) {
        switch (formatType) {
            case CSV_TYPE:
                return new CSVDataSource();
            case JSON_TYPE:
                return new JSONDataSource();
            case TXT_TYPE:
                return new TXTDataSource(); // Devuelve una instancia de TXTDataSource para el tipo TXT
            default:
                throw new IllegalArgumentException("Invalid format type");
        }
    }
}
