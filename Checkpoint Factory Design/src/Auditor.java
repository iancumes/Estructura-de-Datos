public class Auditor extends User {
    public Auditor() {
        // Constructor predeterminado sin argumentos
        super("", ""); // Puedes proporcionar valores predeterminados para el nombre de usuario y la contraseña si es necesario
    }

    public Auditor(String username, String password) {
        super(username, password);
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
