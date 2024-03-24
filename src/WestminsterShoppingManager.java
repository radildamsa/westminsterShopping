import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager{

    public static List<Product> productList = new ArrayList<>();
    public List<String> productIDForSorting = new ArrayList<>();

    private int numberOfProducts;

    List<Product> sortedProductList = new ArrayList<>();



    Scanner scanner = new Scanner(System.in);



    @Override
    public void addProductToSystem() {
        if (!(productList.size() < 50)) {
            System.out.println("Cannot add more products. Maximum limit reached.");
        } else {
            System.out.print("IF U WANT TO ADD AN ELECTRONIC PRODUCT type E, IF U WANT TO ADD AN CLOTHING PRODUCT type C: ");
            String elecOrCloth = scanner.next();
            if (elecOrCloth.toLowerCase().equals("e")){
                addElectronicProduct();
                numberOfProducts++;
            } else if (elecOrCloth.toLowerCase().equals("c")){
                addClothingProduct();
                numberOfProducts++;
            }
            else{
                System.out.println("Wrong input");
            }
        }
    }



    public void addElectronicProduct(){
        // Example: Adding a new electronics product
        System.out.println("Adding a new Electronics product:");
        Electronics newElectronics = createElectronicsProduct(scanner);
        productList.add(newElectronics);
        productIDForSorting.add(newElectronics.getProductID());
    }
    private static Electronics createElectronicsProduct(Scanner scanner) {
        String productCategory = "Electronics";

        System.out.print("Enter product ID: ");
        String productId = scanner.next();
        // Validate product ID (assuming it should not be empty)
        while (productId.isEmpty()) {
            System.out.println("Product ID cannot be empty. Please enter a valid ID.");
            System.out.print("Enter product ID: ");
            productId = scanner.next();
        }

        System.out.print("Enter product name: ");
        String productName = scanner.next();
        // Validate product name (assuming it should not be empty)
        while (productName.isEmpty()) {
            System.out.println("Product name cannot be empty. Please enter a valid name.");
            System.out.print("Enter product name: ");
            productName = scanner.next();
        }

        System.out.print("Enter available items: ");
        // Validate product count (assuming it should be a positive integer)
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for available items.");
            System.out.print("Enter available items: ");
            scanner.next(); // consume invalid input
        }
        int productCount = scanner.nextInt();

        System.out.print("Enter price: ");
        // Validate product price (assuming it should be a positive integer)
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for price.");
            System.out.print("Enter price: ");
            scanner.next(); // consume invalid input
        }
        int productPrice = scanner.nextInt();

        System.out.print("Enter brand: ");
        String brand = scanner.next();
        // Validate brand (assuming it should not be empty)
        while (brand.isEmpty()) {
            System.out.println("Brand cannot be empty. Please enter a valid brand.");
            System.out.print("Enter brand: ");
            brand = scanner.next();
        }

        System.out.print("Enter warranty period (months): ");
        // Validate warranty period (assuming it should be a positive integer)
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for warranty period.");
            System.out.print("Enter warranty period (months): ");
            scanner.next(); // consume invalid input
        }
        int warrantyPeriod = scanner.nextInt();
        return new Electronics(productId, productName, productCount, productPrice,productCategory, brand, warrantyPeriod);
    }

    public void addClothingProduct(){
        // Example: Adding a new clothing product
        System.out.println("Adding a new Clothing product:");
        Clothing newClothing = createClothingProduct(scanner);
        productList.add(newClothing);
        productIDForSorting.add(newClothing.getProductID());

    }
    private static Clothing createClothingProduct(Scanner scanner) {
        String productCategory = "Clothing";
        System.out.print("Enter product ID: ");
        String productId = scanner.next();
        // Validate product ID (assuming it should not be empty)
        while (productId.isEmpty()) {
            System.out.println("Product ID cannot be empty. Please enter a valid ID.");
            System.out.print("Enter product ID: ");
            productId = scanner.next();
        }

        System.out.print("Enter product name: ");
        String productName = scanner.next();
        // Validate product name (assuming it should not be empty)
        while (productName.isEmpty()) {
            System.out.println("Product name cannot be empty. Please enter a valid name.");
            System.out.print("Enter product name: ");
            productName = scanner.next();
        }

        System.out.print("Enter available items: ");
        // Validate product count (assuming it should be a positive integer)
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for available items.");
            System.out.print("Enter available items: ");
            scanner.next(); // consume invalid input
        }
        int productCount = scanner.nextInt();

        System.out.print("Enter price: ");
        // Validate product price (assuming it should be a positive integer)
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for price.");
            System.out.print("Enter price: ");
            scanner.next(); // consume invalid input
        }
        int productPrice = scanner.nextInt();
        System.out.print("Enter size: ");
        String size = scanner.next();
        while (size.isEmpty()) {
            System.out.println("Product size cannot be empty. Please enter a valid name.");
            System.out.print("Enter product size: ");
            size = scanner.next();
        }
        System.out.print("Enter color: ");
        String color = scanner.next();
        while (color.isEmpty()) {
            System.out.println("Product color cannot be empty. Please enter a valid name.");
            System.out.print("Enter product color: ");
            color = scanner.next();
        }

        return new Clothing(productId, productName, productCount, productPrice, productCategory, size, color);
    }





    @Override
    public void deleteProductFromSystem() {
        System.out.print("Enter the product id of the product that has to be removed: ");
        String deletingProductID = scanner.next();
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID().equals(deletingProductID)) {
                iterator.remove();
                numberOfProducts--;
                if (product instanceof Electronics){
                    System.out.println("Deleting Electronic product from the system.");
                }
                else if (product instanceof Clothing) {
                    System.out.println("Deleting Clothing product from the system.");
                }
                System.out.println("Product removed successfully.");
                System.out.println(numberOfProducts + " products left in the system.");
                return;
            }
        }
        System.out.println("Product not available.");
    }


    @Override
    public void printProductList() {
        // Sort product IDs
        Collections.sort(productIDForSorting);

        // Create a new list to store sorted products


        // Populate sortedProductList based on productIDForSorting
        for (String productId : productIDForSorting) {
            for (Product product : productList) {
                if (product.getProductID().equals(productId)) {
                    sortedProductList.add(product);
                    break; // Break after finding the corresponding product for the ID
                }
            }
        }

        // Display details of sorted products
        for (Product product : sortedProductList) {
            System.out.println(product.displayDetails());
        }
    }



    @Override
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt", true))) {
            for (Product product : productList) {
                writer.write(product.displayDetails());
                writer.newLine();
            }
            System.out.println("Product list appended to file: " + "file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadDataFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Product list loaded from file: " + "file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
