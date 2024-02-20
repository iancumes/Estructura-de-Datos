public class Auditor extends User {
    public Auditor(String username, String password) {
        super(username, password);
    }

    @Override
    public void login() {
        // Implementation for auditor login
    }

    // Additional functionalities specific to auditor
    public void reviewGrades() {
        // Implementation for reviewing grades
    }

    public void reviewStudentPayments() {
        // Implementation for reviewing student payments
    }

    public void reviewFacultyPayments() {
        // Implementation for reviewing faculty payments
    }
}
