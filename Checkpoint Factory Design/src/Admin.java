import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<Course> courses;
    private List<Student> students;
    private List<Faculty> faculties;

    // Constructor predeterminado sin argumentos
    public Admin() {
        super("", ""); // Puedes proporcionar valores predeterminados para el nombre de usuario y la contraseña si es necesario
        courses = new ArrayList<>();
        students = new ArrayList<>();
        faculties = new ArrayList<>();
    }

    public Admin(String username, String password) {
        super(username, password);
        courses = new ArrayList<>();
        students = new ArrayList<>();
        faculties = new ArrayList<>();
    }

    public void createCourse(String courseName, Faculty faculty) {
        Course course = new Course(courseName, faculty);
        courses.add(course);
    }

    public void createStudent(String username, String password) {
        Student student = new Student(username, password);
        students.add(student);
    }

    public void createFaculty(String username, String password) {
        Faculty faculty = new Faculty(username, password);
        faculties.add(faculty);
    }

    public void assignStudentToCourse(Student student, Course course) {
        course.addStudent(student);
    }

    public void assignFacultyToCourse(Faculty faculty, Course course) {
        course.setCourseFaculty(faculty);
    }

    public void assignPaymentToFaculty(Faculty faculty, double amount) {
        // Implement logic to assign payment to faculty
    }

    public void generateGradeReport(Student student) {
        // Implement logic to generate a grade report for a student
    }

    public void generatePaymentReport(Student student) {
        // Implement logic to generate a payment report for a student
    }
}
