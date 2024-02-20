public class Student extends User {
    public Student(String username, String password) {
        super(username, password);
    }

    @Override
    public void login() {
        // Implementation for student login
    }

    // Additional functionalities specific to students
    public void checkGrades() {
        // Implementation for checking grades
    }

    public void makePayment() {
        // Implementation for making payments
    }

    public void viewPaymentHistory() {
        // Implementation for viewing payment history
    }
}
