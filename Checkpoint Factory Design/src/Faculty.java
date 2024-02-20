import java.util.ArrayList;
import java.util.List;

public class Faculty extends User {
    

    public Faculty() {
        super("", ""); // Constructor predeterminado con valores predeterminados o vacíos para el nombre de usuario y la contraseña
    }

    public Faculty(String username, String password ) {
        super(username, password);
    }

    public void enterGrades(Student student, Course course, List<Double> grades) {
        // Implement logic for faculty to enter grades for students
    }

    public void viewPaymentHistory() {
        // Implement logic for faculty to view payment history
    }

    public void collectPayment(Student student, double amount) {
        // Implement logic for faculty to collect payment from students
    }
}
