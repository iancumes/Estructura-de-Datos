import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private Faculty courseFaculty;
    private List<Student> students;

    public Course() {
        // Constructor predeterminado sin argumentos
        this.courseName = "";
        this.courseFaculty = null;
        this.students = new ArrayList<>();
    }

    public Course(String courseName, Faculty courseFaculty) {
        this.courseName = courseName;
        this.courseFaculty = courseFaculty;
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public Faculty getCourseFaculty() {
        return courseFaculty;
    }

    public void setCourseFaculty(Faculty faculty) {
        this.courseFaculty = faculty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
