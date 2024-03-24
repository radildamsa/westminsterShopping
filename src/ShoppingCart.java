public class ShoppingCart {
    String productName;
    int productCount;
    int productPrice;
    public ShoppingCart(String productName, int productCount,  int productPrice) {
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }
    public String getName(){
        return productName;
    }
    public int getCount(){
        return productCount;
    }
    public int getPrice(){
        return productPrice;
    }

}


