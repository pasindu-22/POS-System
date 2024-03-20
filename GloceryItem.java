import java.io.Serializable;
import java.util.ArrayList;

public class GloceryItem implements Serializable{
    protected int itemCode;
    private String name;
    private double price;
    private double weight;
    private String dateOfMan;
    private String dateOfExp;
    private String manufacturer;
    private double discount;

    public GloceryItem(int itemCode,String name, double price, double weight, String dateOfMan, String dateOfExp, String manufacturer,double discount) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.dateOfMan = dateOfMan;
        this.dateOfExp = dateOfExp;
        this.manufacturer = manufacturer;
        this.discount = discount;
    }

    public int getItemCode() {
        return itemCode;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return price + " : ";
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }
}



