import java.util.ArrayList;

public class Customer {
    private String name;
    private int customerId;
    private String email;
    private ArrayList<Bill> bills = new ArrayList<>();

    public Customer(String name, int customerId, String email) {
        this.name = name;
        this.customerId = customerId;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }
}
