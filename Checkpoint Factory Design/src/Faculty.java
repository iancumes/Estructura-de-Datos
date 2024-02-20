public class Faculty extends User {
    public Faculty(String username, String password) {
        super(username, password);
    }

    @Override
    public void login() {
        // Implementation for faculty login
    }

    // Additional functionalities specific to faculty
    public void addGrades() {
        // Implementation for adding grades
    }

    public void collectPayment() {
        // Implementation for collecting payments
    }

    public void viewPaymentHistory() {
        // Implementation for viewing payment history
    }
}