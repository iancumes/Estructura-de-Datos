public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void login() {
        // Implementation for admin login
    }

    // Additional functionalities specific to admin
    public void createCourse() {
        // Implementation for creating courses
    }

    public void createUser() {
        // Implementation for creating users
    }

    public void assignStudentToCourse() {
        // Implementation for assigning students to courses
    }

    public void assignFacultyToCourse() {
        // Implementation for assigning faculty to courses
    }

    public void assignPaymentToFaculty() {
        // Implementation for assigning payments to faculty
    }

    public void generateReports() {
        // Implementation for generating reports
    }
}
