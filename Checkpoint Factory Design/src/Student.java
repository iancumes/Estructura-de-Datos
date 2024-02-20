import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Course> enrolledCourses;
    private List<Double> grades;
    private List<Double> paymentHistory;

    // Constructor predeterminado requerido por Jackson
    public Student() {
        super("", ""); // Llama al constructor de la clase base con valores predeterminados o vacíos
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
    }

    public Student(String username, String password) {
        super(username, password);
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        course.addStudent(this);
    }

    public double getGradeForClass(String className) {
        int index = enrolledCourses.indexOf(className);
        if (index != -1) {
            return grades.get(index);
        } else {
            return -1;
        }
    }

    public boolean makePayment(double amount) {
        // Logic to make payment
        // Assuming the payment is successful
        paymentHistory.add(amount);
        return true;
    }

    public List<Double> viewPaymentHistory() {
        return paymentHistory;
    }
}
