import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {
    private static Database instance;
    private ArrayList<GloceryItem> items = new ArrayList<>();
    private static int itemCount=100;

    private Database() {}
    public static Database getInstance() {
        if (instance==null) {
            instance = new Database();
        }
        return instance;
    }


    public void addGlocery(String name,double price, double weight, String dateOfMan, String dateOfExp, String manufacturer,double discount) {
        items.add(new GloceryItem(itemCount+1,name,price,weight,dateOfMan,dateOfExp,manufacturer,discount));
    }

    public GloceryItem getItem(int itemCode) throws ItemCodeNotFound {
        boolean found = false;
        for (GloceryItem i : items) {
            if (i.getItemCode() == itemCode) {
                found = true;
                return i;
            }
        }
        if (!found) {
            throw new ItemCodeNotFound();
        }
        return null;
    }


    public void createDb() {
        items.add(new GloceryItem(1,"Signal",125,150,"3/11","10/11","Uniliver",0.12));
        items.add(new GloceryItem(2,"Lifebouy",85,50,"1/15","10/15","Uniliver",0.25));
        items.add(new GloceryItem(3,"Sunlight",75,50,"3/11","10/11","Uniliver",0.13));
        items.add(new GloceryItem(4,"Raththi",450,100,"3/11","10/11","Cargills",0.02));
        items.add(new GloceryItem(5,"Maggi",125,100,"3/11","10/11","Nestle",0.3));
        items.add(new GloceryItem(6,"CocaCola",125,100,"3/11","10/11","CocaCola",0.11));
        items.add(new GloceryItem(7,"Pepsi",125,100,"3/11","10/11","Pepsi",0.17));
        items.add(new GloceryItem(8,"Sprite",125,100,"3/11","10/11","CocaCola",0.6));
        items.add(new GloceryItem(9,"7up",125,100,"3/11","10/11","Pepsi",0.01));
        items.add(new GloceryItem(10,"Fanta",125,100,"3/11","10/11","CocaCola",0.09));
        items.add(new GloceryItem(11,"Nescafe",125,100,"3/11","10/11","Nestle",0.41));
        items.add(new GloceryItem(12,"Sup kata",125,100,"3/11","10/11","Uniliver",0.32));
        items.add(new GloceryItem(13,"Nestomalt",125,100,"3/11","10/11","Nestle",0.7));
        items.add(new GloceryItem(14,"Anchor",125,100,"3/11","10/11","Fonterra",0.2));
        items.add(new GloceryItem(15,"Astra",125,100,"3/11","10/11","Fonterra",0.12));
        items.add(new GloceryItem(16,"Seeni",250,1,"3/11","10/11","Cargills",0.15));
    }
}
