import java.util.Scanner;

public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;


    public Electronics(String productID, String productName, int productCount, int productPrice, String productCategory, String brand, int warrantyPeriod) {
        super(productID, productName, productCount, productPrice, productCategory);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }



    @Override
    public String toString() {
        return
                "brand='" + brand + '\'' +
                        ", warrantyPeriod='" + warrantyPeriod + '\'';
    }

    @Override
    public String displayDetails() {
        String displayDetails = "Electronics: " + getProductName() + " (ID: " + getProductID() + ")" +
                "\nBrand: " + brand +
                "\nWarranty Period: " + warrantyPeriod + " months" +
                "\nPrice: $" + getProductPrice() +
                "\nAvailable Items: " + getProductCount() +
                "\n---------------------";
        return displayDetails;
    }




}
