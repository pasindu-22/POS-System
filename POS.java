import java.io.*;
import java.util.*;


public class POS implements Serializable{
    static Database db = Database.getInstance();
    private static ArrayList<Cashier> cashiers = new ArrayList<>(List.of(new Cashier("Saman",155,"null"),new Cashier("Osa",156,"null")));
    private static ArrayList<Customer> customers = new ArrayList<>(List.of(new Customer("Pasindu",055,"null"),new Customer("Ravindu",056,"null")));
    private static String branch = "Galle";
    private static ArrayList<Bill> pendingBills = new ArrayList<>();
    private static ArrayList<Bill> completedBills = new ArrayList<>();

    public static GloceryItem getItemDetails() {
        GloceryItem item = null;
        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            String item_code = br.readLine();
            item = db.getItem(Integer.parseInt(item_code));

        } catch (ItemCodeNotFound e) {
            System.out.println("Wrong item!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }



    private static void createBill(Bill b) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter item code: ");
            GloceryItem item = getItemDetails();
            if (item==null) {
                continue;
            }
            System.out.println("Enter quantity:");
            double quantity = sc.nextDouble();
            b.addItem(item,quantity);
            System.out.println("Do you want to add more items? (y/n)");
            String more = sc.next();
            if (more.equals("n")) {
                b.setTotal();
                b.setTotalDiscount();
                break;
            }
        }
        System.out.println("Do you want to print the bill? (y/n)");
        String print = sc.next();
        if (print.equals("y")) {
            try {
                b.printBill();
                completedBills.add(b);
            } catch (NullPointerException e) {
                System.out.println("No items found in the cart");
            }
        }
        System.out.println("Do you want to add this bill to pending list? (y/n)");
        String pending = sc.next();
        if (pending.equals("y")) {
            pendingBills.add(b);
        }
    }

    public static void saveBill(){
        try{
            FileOutputStream fs = new FileOutputStream("Pendings.dat");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(pendingBills);
            os.close();
        } catch (IOException e) {
            System.out.println("unsuccessful");
        }
    }

    public static ArrayList<Bill> getBills() {
        try {
            FileInputStream fs = new FileInputStream("Pendings.dat");
            ObjectInputStream os = new ObjectInputStream(fs);
            pendingBills = (ArrayList<Bill>) os.readObject();
            os.close();
        } catch (IOException e) {
            System.out.println("Pending Bills loading unsuccessfull");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            return pendingBills;
        }
    }

    public static Customer getCustomer(String name) throws CustomerNotFound{
        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        throw new CustomerNotFound();
    }

    public static void main(String[] args) {
        db.createDb();
        System.out.println("Enter Cashier Name:");
        Scanner sc = new Scanner(System.in);
        String cashier = sc.nextLine();
        pendingBills = getBills();
        while (true) {
            System.out.println("Cashier Name: " + cashier);
            System.out.println("1.New bill");
            System.out.println("2.Pending bills");
            System.out.println("3.Exit");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                continue;
            }
            if (choice==1) {
                System.out.println("Enter Customer Name:");
                String customer = sc.next();
                boolean registered = false;
                try {
                    Customer c = getCustomer(customer);
                } catch (CustomerNotFound e) {
                    System.out.println("Customer not found. Do you want to register? (y/n)");
                    String reg = sc.next();
                    if (reg.equals("y")) {
                        customers.add(new Customer(customer, customers.size()+1,"null"));
                    }
                }
                Bill bill = new Bill(cashier,customer);
                createBill(bill);
            } else if (choice==2) {
                Bill bill=null;
                int option=0;
                System.out.println("Bill number" + "    Cashier " + "      Customer Name "+ "      Total " );
                int billNumber = 1;
                for (Bill b : pendingBills) {
                    System.out.println(billNumber + b.cashierName + b.customerName + b.getTotal());
                    billNumber++;
                }
                System.out.println("Enter the Bill number that you want to continue or 0 to exit:" );
                option = sc.nextInt();

                if (0 < option && option <= pendingBills.size()) {
                    bill = pendingBills.get(option-1);
                    try {
                        createBill(bill);
                        completedBills.add(bill);
                        pendingBills.remove(bill);
                    } catch (NullPointerException e) {
                        System.out.println("No items found in the cart");
                    } catch (ConcurrentModificationException e) {
                        System.out.println("Concurrent Modification Exception");
                    }
                } else if (option == 0) {
                    continue;
                }

            } else if (choice==3) {
                saveBill();
                break;
            }

        }
    }
}
