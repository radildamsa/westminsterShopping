public class Clothing extends Product{
    private String size;
    private String color;



    public Clothing(String productID, String productName, int productCount, int productPrice,String productCategory, String size, String color) {
        super(productID, productName, productCount, productPrice, productCategory);
        this.size = size;
        this.color = color;
    }


    @Override
    public String toString() {
        return
                "size='" + size + '\'' +
                ", color='" + color + '\'' ;
    }

    @Override
    public String displayDetails() {
        String displayDetails = "Clothing: " + getProductName() + " (ID: " + getProductID() + ")" +
                "\nSize: " + size +
                "\nColor: " + color +
                "\nPrice: $" + getProductPrice() +
                "\nAvailable Items: " + getProductCount() +
                "\n---------------------";
        return displayDetails;
    }
}



