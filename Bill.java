import java.io.Serializable;
import java.util.HashMap;

class Bill implements Serializable {
    String cashierName;
    String customerName;
    private HashMap<GloceryItem,Integer> itemList = new HashMap<>();
    private double total=0;
    private double totalDiscount=0;

    public Bill() {
    }

    public Bill(String cashierName, String customerName) {
        this.cashierName = cashierName;
        this.customerName = customerName;
    }

    public void addItem(GloceryItem item,int quantity) {
        itemList.put(item,quantity);
    }

    public void printBill() throws NullPointerException{
        if (itemList.isEmpty()) {
            throw new NullPointerException();
        }
        System.out.println("---------------Super Saving Super Market---------------");
        System.out.println("Cashier: " + cashierName + "      Customer Name:" + customerName);
        System.out.println("ItemCode     Name      Price      Quantity      Price");
        for (GloceryItem i: itemList.keySet()) {
            System.out.printf("%3d   %12s   %8.2f    %6d      %8.2f\n",i.getItemCode(),i.getName(),i.getPrice(),itemList.get(i),i.getPrice()*itemList.get(i));
        }
        System.out.println();
        System.out.println("Total: " + getTotal());
        System.out.println("Total Discount: " + getTotalDiscount());
        System.out.println("Net Total: " + (getTotal()-getTotalDiscount()));
        System.out.println(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Thank you, Come Again.");
    }
        // Set the total
    public void setTotal() {
        for (GloceryItem i: itemList.keySet()) {
            total += i.getPrice()*itemList.get(i);
        }

    }
    public void setTotalDiscount() {
        for (GloceryItem i: itemList.keySet()) {
            totalDiscount += i.getDiscount()*i.getPrice()*itemList.get(i);
        }
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotal() {
        return total;
    }
}