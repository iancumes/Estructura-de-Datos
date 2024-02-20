import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class UniversityController {
    private static final String DATA_FILE_PATH = "data_prueba.json"; // Ruta del archivo de datos
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "adminpass";
    private static final String STUDENT_USERNAME = "student";
    private static final String STUDENT_PASSWORD = "studentpass";
    private static final String AUDITOR_USERNAME = "auditor";
    private static final String AUDITOR_PASSWORD = "auditorpass";
    private static final String FACULTY_USERNAME = "faculty";
    private static final String FACULTY_PASSWORD = "facultypass";
    private static final List<Faculty> faculties = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Auditor> auditors = new ArrayList<>();
    private static final List<Admin> admins = new ArrayList<>();
    private static User currentUser; // Campo estático para almacenar el usuario actualmente autenticado
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        loadData();
        System.out.println("Bienvenido al sistema universitario");
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
    
        currentUser = authenticate(username, password); // Asignar el usuario autenticado a currentUser
    
        if (currentUser != null) {
            System.out.println("Inicio de sesión exitoso como " + currentUser.getClass().getSimpleName());
            while (true) {
                showOptions(currentUser);
                System.out.print("Seleccione una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                switch (currentUser.getClass().getSimpleName()) {
                    case "Admin":
                        executeAdminOption(option);
                        break;
                    case "Student":
                        executeStudentOption(option);
                        break;
                    case "Faculty":
                        executeFacultyOption(option);
                        break;
                    case "Auditor":
                        executeAuditorOption(option);
                        break;

                    // Agrega casos para otro
                    default:
                        System.out.println("Rol de usuario no reconocido.");
                }
                if (option == 10) {
                    saveData(); // Opción para salir
                    break;
                }
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos");
        }
    }

    private static User authenticate(String username, String password) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            return new Admin(username, password);
        } else if (username.equals(STUDENT_USERNAME) && password.equals(STUDENT_PASSWORD)) {
            return new Student(username, password);
        } else if (username.equals(AUDITOR_USERNAME) && password.equals(AUDITOR_PASSWORD)) {
            return new Auditor(username, password);  
        } else if (username.equals(FACULTY_USERNAME) && password.equals(FACULTY_PASSWORD)) {
            return new Faculty(username, password);  
        }  else {
            return null;
        }
    }

    private static void showOptions(User user) {
        if (user instanceof Admin) {
            showAdminOptions();
        } else if (user instanceof Student) {
            showStudentOptions();
        } else if (user instanceof Auditor) {
            showAuditorOptions();
        } else if (user instanceof Faculty) {
            showFacultyOptions();
        }
    }

    private static void showAdminOptions() {
        System.out.println("Opciones disponibles para el administrador:");
        System.out.println("1. Crear curso");
        System.out.println("2. Crear estudiante");
        System.out.println("3. Crear profesor");
        System.out.println("4. Asignar estudiante a curso");
        System.out.println("5. Asignar profesor a curso");
        System.out.println("6. Asignar pago a profesor");
        System.out.println("7. Generar reporte de notas");
        System.out.println("8. Generar reporte de pagos de estudiantes");
        System.out.println("10. Salir");
        // Aquí puedes agregar más opciones según sea necesario
    }
    
    private static void showStudentOptions() {
        System.out.println("Opciones disponibles para el estudiante:");
        System.out.println("1. Consultar nota de una clase específica");
        System.out.println("2. Realizar pago");
        System.out.println("3. Consultar pagos");
        System.out.println("10. Salir");
        // Aquí puedes agregar más opciones según sea necesario
    }
    
    private static void showAuditorOptions() {
        System.out.println("Opciones disponibles para el auditor:");
        System.out.println("1. Revisar notas");
        System.out.println("2. Revisar cuotas pagadas de estudiantes");
        System.out.println("3. Revisar pagos a profesores");
        System.out.println("10. Salir");
        // Aquí puedes agregar más opciones según sea necesario
    }
    
    private static void showFacultyOptions() {
        System.out.println("Opciones disponibles para el profesor:");
        System.out.println("1. Ingresar notas de los estudiantes en una clase específica");
        System.out.println("2. Cobrar pago");
        System.out.println("3. Consultar historial de pagos");
        System.out.println("10. Salir");
        // Aquí puedes agregar más opciones según sea necesario
    }
    private static void executeAdminOption(int option) {
        Admin admin = (Admin) currentUser; // Suponiendo que currentUser es un campo que almacena el usuario actualmente autenticado como Admin
        switch (option) {
            case 1:
                // Crear curso
                System.out.print("Ingrese el nombre del curso: ");
                String courseName = scanner.nextLine();
                System.out.print("Ingrese el nombre del profesor: ");
                String facultyUsername = scanner.nextLine();
                Faculty faculty = findFacultyByUsername(facultyUsername);
                if (faculty != null) {
                    admin.createCourse(courseName, faculty);
                    System.out.println("Curso creado correctamente.");
                } else {
                    System.out.println("No se encontró el profesor.");
                }
                break;
            case 2:
                // Crear estudiante
                System.out.print("Ingrese el nombre de usuario del estudiante: ");
                String studentUsername = scanner.nextLine();
                System.out.print("Ingrese la contraseña del estudiante: ");
                String studentPassword = scanner.nextLine();
                admin.createStudent(studentUsername, studentPassword);
                System.out.println("Estudiante creado correctamente.");
                break;
            case 3:
            // Crear profesor
            System.out.print("Ingrese el nombre de usuario del profesor: ");
            String professorUsername = scanner.nextLine(); // Cambiado de facultyUsername a professorUsername
            System.out.print("Ingrese la contraseña del profesor: ");
            String facultyPassword = scanner.nextLine();
            admin.createFaculty(professorUsername, facultyPassword);
            System.out.println("Profesor creado correctamente.");
            break;
        
            case 4:
    // Asignar estudiante a curso
    System.out.print("Ingrese el nombre de usuario del estudiante: ");
    String studentUsername2 = scanner.nextLine();
    System.out.print("Ingrese el nombre del curso: ");
    String courseName2 = scanner.nextLine();
    Student student = findStudentByUsername(studentUsername2);
    Course course = findCourseByName(courseName2);
    if (student != null && course != null) {
        admin.assignStudentToCourse(student, course);
        System.out.println("Estudiante asignado correctamente al curso.");
    } else {
        System.out.println("No se encontró el estudiante o el curso.");
    }
    break;
case 5:
    // Asignar profesor a curso
    System.out.print("Ingrese el nombre de usuario del profesor: ");
    String facultyUsername2 = scanner.nextLine();
    System.out.print("Ingrese el nombre del curso: ");
    String courseName3 = scanner.nextLine();
    Faculty faculty2 = findFacultyByUsername(facultyUsername2);
    Course course2 = findCourseByName(courseName3);
    if (faculty2 != null && course2 != null) {
        admin.assignFacultyToCourse(faculty2, course2);
        System.out.println("Profesor asignado correctamente al curso.");
    } else {
        System.out.println("No se encontró el profesor o el curso.");
    }
    break;
case 6:
    // Asignar pago a profesor
    System.out.print("Ingrese el nombre de usuario del profesor: ");
    String facultyUsername4 = scanner.nextLine();
    System.out.print("Ingrese el monto del pago: ");
    double amount = scanner.nextDouble();
    Faculty faculty3 = findFacultyByUsername(facultyUsername4);
    if (faculty3 != null) {
        admin.assignPaymentToFaculty(faculty3, amount);
        System.out.println("Pago asignado correctamente al profesor.");
    } else {
        System.out.println("No se encontró el profesor.");
    }
    break;
case 7:
    // Generar reporte de notas
    System.out.print("Ingrese el nombre de usuario del estudiante: ");
    String studentUsername3 = scanner.nextLine();
    Student student2 = findStudentByUsername(studentUsername3);
    if (student2 != null) {
        admin.generateGradeReport(student2);
        System.out.println("Reporte de notas generado correctamente para el estudiante.");
    } else {
        System.out.println("No se encontró el estudiante.");
    }
    break;
case 8:
    // Generar reporte de pagos de estudiantes
    System.out.print("Ingrese el nombre de usuario del estudiante: ");
    String studentUsername4 = scanner.nextLine();
    Student student3 = findStudentByUsername(studentUsername4);
    if (student3 != null) {
        admin.generatePaymentReport(student3);
        System.out.println("Reporte de pagos generado correctamente para el estudiante.");
    } else {
        System.out.println("No se encontró el estudiante.");
    }
    break;
    case 10:
        saveData();
                System.out.println("Saliendo...");
                break;
    default:
        System.out.println("Opción inválida.");
        }
    }
    private static void executeStudentOption(int option) {
        Student student = (Student) currentUser; // Suponiendo que currentUser es un campo que almacena el usuario actualmente autenticado como Estudiante
        switch (option) {
            case 1:
                // Consultar nota de una clase específica
                System.out.print("Ingrese el nombre de la clase: ");
                String className = scanner.nextLine();
                Course course2 = findCourseByName(className);
                // Implementa la lógica para consultar la nota del estudiante en la clase específica
                double grade = student.getGradeForClass(className);
                if (grade != -1) {
                    System.out.println("La nota en " + className + " es: " + grade);
                } else {
                    System.out.println("No se encontraron notas para la clase " + className);
                }
                break;
            case 2:
                // Realizar pago
                System.out.print("Ingrese el monto a pagar: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consumir la nueva línea después de nextDouble()
                if (student.makePayment(amount)) {
                    System.out.println("Pago realizado correctamente.");
                } else {
                    System.out.println("No se pudo realizar el pago. Fondos insuficientes.");
                }
                break;
            case 3:
                // Consultar pagos
                List<Double> payments = student.viewPaymentHistory();
                if (!payments.isEmpty()) {
                    System.out.println("Historial de pagos:");
                    for (Double payment : payments) {
                        System.out.println("- $" + payment);
                    }
                } else {
                    System.out.println("No se encontraron pagos realizados.");
                }
                break;
            case 10:
            saveData();
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
    
    
    private static void executeFacultyOption(int option) {
    Faculty faculty = (Faculty) currentUser; // Suponiendo que currentUser es un campo que almacena el usuario actualmente autenticado como Faculty
    switch (option) {
        case 1:
            // Ingresar notas de los estudiantes en una clase específica
            System.out.print("Ingrese el nombre de la clase: ");
            String className = scanner.nextLine();
            System.out.print("Ingrese la nota para la clase " + className + ": ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea después de nextDouble()
            // Implementa la lógica para que el profesor ingrese las notas de los estudiantes en la clase específica
            Course course = findCourseByName(className);
            if (course != null) {
                List<Student> students = course.getStudents();
                for (Student student : students) {
                    faculty.enterGrades(student, course, Collections.singletonList(grade));
                }
                System.out.println("Notas ingresadas correctamente para la clase " + className);
            } else {
                System.out.println("No se encontró la clase " + className);
            }
            break;
        case 2:
            // Cobrar pago
            System.out.print("Ingrese el nombre de usuario del estudiante: ");
            String studentUsername = scanner.nextLine();
            Student student = findStudentByUsername(studentUsername);
            if (student != null) {
                System.out.print("Ingrese el monto a cobrar: ");
                double amountToCollect = scanner.nextDouble();
                faculty.collectPayment(student, amountToCollect);
                System.out.println("Pago cobrado correctamente.");
            } else {
                System.out.println("Estudiante no encontrado.");
            }
            break;
        case 3:
            // Consultar historial de pagos
            System.out.println("Historial de pagos:");
            faculty.viewPaymentHistory();
            break;
        case 10:
        saveData();
            System.out.println("Saliendo...");
            break;
        default:
            System.out.println("Opción inválida.");
    }
}

    
private static void executeAuditorOption(int option) {
    Auditor auditor = (Auditor) currentUser; // Suponiendo que currentUser es un campo que almacena el usuario actualmente autenticado como Auditor
    switch (option) {
        case 1:
            // Revisar notas
            auditor.reviewGrades();
            break;
        case 2:
            // Revisar cuotas pagadas de estudiantes
            auditor.reviewStudentPayments();
            break;
        case 3:
            // Revisar pagos a profesores
            auditor.reviewFacultyPayments();
            break;
        case 10:
        saveData();
            System.out.println("Saliendo...");
            break;
        default:
            System.out.println("Opción inválida.");
    }
}

    private static Faculty findFacultyByUsername(String username) {
        // Supongamos que tienes una lista de profesores llamada 'faculties'
        for (Faculty faculty : faculties) {
            if (faculty.getUsername().equals(username)) {
                return faculty;
            }
        }
        return null; // Retorna null si no se encuentra ningún profesor con el nombre de usuario dado
    }
    private static Student findStudentByUsername(String username) {
        // Supongamos que tienes una lista de profesores llamada 'faculties'
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null; // Retorna null si no se encuentra ningún profesor con el nombre de usuario dado
    }
    private static Auditor findAuditorByUsername(String username) {
        // Supongamos que tienes una lista de profesores llamada 'faculties'
        for (Auditor auditor : auditors) {
            if (auditor.getUsername().equals(username)) {
                return auditor;
            }
        }
        return null; // Retorna null si no se encuentra ningún profesor con el nombre de usuario dado
    }
    private static Admin findAdminByUsername(String username) {
        // Supongamos que tienes una lista de profesores llamada 'faculties'
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin ;
            }
        }
        return null; // Retorna null si no se encuentra ningún profesor con el nombre de usuario dado
    }
    private static Course findCourseByName(String name) {
        // Supongamos que tienes una lista de profesores llamada 'faculties'
        for (Course course : courses) {
            if (course.getCourseName().equals(name)) {
                return course ;
            }
        }
        return null; // Retorna null si no se encuentra ningún profesor con el nombre de usuario dado
    }
    private static void exportData() {
        System.out.println("Seleccione el formato de exportación:");
        System.out.println("1. Exportar a XML");
        System.out.println("2. Exportar a CSV");
        System.out.print("Seleccione una opción: ");
        int exportOption = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de nextInt()
    
        IDataSource dataSource;
        switch (exportOption) {
            case 1:
                dataSource = DataSourceFactory.getDataSourceInstance(DataSourceFactory.XML_TYPE);
                dataSource.saveData(collectAllData(), "exported_data.xml");
                System.out.println("Datos exportados a XML correctamente.");
                break;
            case 2:
                dataSource = DataSourceFactory.getDataSourceInstance(DataSourceFactory.CSV_TYPE);
                dataSource.saveData(collectAllData(), "exported_data.csv");
                System.out.println("Datos exportados a CSV correctamente.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
    private static List<String[]> collectAllData() {
        List<String[]> allData = new ArrayList<>();
        allData.addAll(collectUserData());
        allData.addAll(collectCourseData());
        // Añade más listas según sea necesario (por ejemplo, auditors, admins, etc.)
        return allData;
    }
    
    private static List<String[]> collectUserData() {
        List<String[]> userData = new ArrayList<>();
        
        // Estudiantes
        for (Student student : students) {
            userData.add(new String[] {student.getUsername(), student.getPassword()});
        }
        
        // Profesores
        for (Faculty faculty : faculties) {
            userData.add(new String[] {faculty.getUsername(), faculty.getPassword()});
        }
        
        // Auditores
        for (Auditor auditor : auditors) {
            userData.add(new String[] {auditor.getUsername(), auditor.getPassword()});
        }
        
        // Administradores
        for (Admin admin : admins) {
            userData.add(new String[] {admin.getUsername(), admin.getPassword()});
        }
        
        return userData;
    }
    
    private static List<String[]> collectCourseData() {
        List<String[]> courseData = new ArrayList<>();
        for (Course course : courses) {
            courseData.add(new String[] {course.getCourseName()});
        }
        // Añade más información de cursos según sea necesario
        return courseData;
    }
    
    private static void saveData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.createObjectNode();

            // Agregar listas de estudiantes, profesores, cursos, etc. al nodo raíz
            ((ObjectNode) rootNode).putPOJO("students", students);
            ((ObjectNode) rootNode).putPOJO("faculties", faculties);
            ((ObjectNode) rootNode).putPOJO("courses", courses);
            ((ObjectNode) rootNode).putPOJO("auditors", auditors);
            ((ObjectNode) rootNode).putPOJO("admins", admins);

            // Escribir el JSON en el archivo
            objectMapper.writeValue(new File(DATA_FILE_PATH), rootNode);

            System.out.println("Datos guardados correctamente en el archivo: " + DATA_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos.");
        }
    }

    private static void loadData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(DATA_FILE_PATH));

            // Cargar listas de estudiantes, profesores, cursos, etc. desde el archivo
            students.clear();
            faculties.clear();
            courses.clear();
            auditors.clear();
            admins.clear();

            students.addAll(Arrays.asList(objectMapper.treeToValue(rootNode.get("students"), Student[].class)));
            faculties.addAll(Arrays.asList(objectMapper.treeToValue(rootNode.get("faculties"), Faculty[].class)));
            courses.addAll(Arrays.asList(objectMapper.treeToValue(rootNode.get("courses"), Course[].class)));
            auditors.addAll(Arrays.asList(objectMapper.treeToValue(rootNode.get("auditors"), Auditor[].class)));
            admins.addAll(Arrays.asList(objectMapper.treeToValue(rootNode.get("admins"), Admin[].class)));

            System.out.println("Datos cargados correctamente desde el archivo: " + DATA_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos.");
        }
    }
}
    
