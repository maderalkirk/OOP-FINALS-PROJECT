// parent class
public class Food {
    protected String foodName;
    protected double price;

    // constructor
    public Food(String foodName, double price){
        this.foodName = foodName;
        this.price = price;
    }

    public String getName(){
        return foodName;
    }

    public double getPrice(){
        return price;
    }

    public void displayInfo(){
        System.out.println(getName() + " | price: " + getPrice());
    }
}

// subclass for main dish
class MainDish extends Food{
    boolean addRice;

    public MainDish(String foodName, double price, boolean addRice){
        super(foodName, price);
        this.addRice = addRice;
    }

    @Override
    public void displayInfo(){
        System.out.println(getName() + " | price: " + getPrice() + (addRice ? " (with rice)" : ""));
    }
}

// subclass for drinks
class Drinks extends Food{
    protected String size;

    public Drinks(String foodName, double price, String size){
        super(foodName, price);
        this.size = size;
    }

    @Override
    public void displayInfo(){
        System.out.println(getName() + " (" + size + ") | price: " + getPrice());
    }
}
