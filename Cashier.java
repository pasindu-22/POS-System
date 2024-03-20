public class Cashier {
    private String name;
    private int employeeId;
    private String email;

    public Cashier(String name, int employeeId, String email) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

}
