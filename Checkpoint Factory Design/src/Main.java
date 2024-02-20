public class Main {
    public static void main(String[] args) {
        // Sample code demonstrating usage
        int formatType = DataSourceFactory.CSV_TYPE;
        IDataSource dataSource = DataSourceFactory.getDataSourceInstance(formatType);

        // Load data
        Object loadedData = dataSource.loadData("data.csv");

        // Perform operations based on user type
        User user = new Student("username", "password");
        user.login();
        if (user instanceof Student) {
            ((Student) user).checkGrades();
        } else if (user instanceof Faculty) {
            ((Faculty) user).addGrades();
        } else if (user instanceof Admin) {
            ((Admin) user).createCourse();
        } else if (user instanceof Auditor) {
            ((Auditor) user).reviewGrades();
        }
    }
}