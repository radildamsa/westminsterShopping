public abstract class Product {

    private String productName;
    private int productCount;
    private String productID;
    private int productPrice;
    String productCategory;


    public Product(String productID, String productName, int productCount, int productPrice, String productCategory) {
        this.productID = productID;
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCount() {
        return productCount;
    }

    public String getProductID() {
        return productID;
    }

    public int getProductPrice() {
        return productPrice;
    }
    public String getProductCategory(){return productCategory;}



    public abstract String displayDetails();
}


