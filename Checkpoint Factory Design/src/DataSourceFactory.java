public class DataSourceFactory {
    public static final int CSV_TYPE = 0;
    public static final int JSON_TYPE = 1;
    public static final int XML_TYPE = 2; // Agregamos un nuevo tipo para XML

    public static IDataSource getDataSourceInstance(int formatType) {
        switch (formatType) {
            case CSV_TYPE:
                return new CSVDataSource();
            case JSON_TYPE:
                return new JSONDataSource();
            case XML_TYPE:
                return new XMLDataSource(); // Devuelve una instancia de XMLDataSource para el tipo XML
            default:
                throw new IllegalArgumentException("Invalid format type");
        }
    }
}
