import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        Scanner scanner = new Scanner(System.in);



        int userChoice;

        do {
            System.out.print("\n1 - Add a new product \n" +
                    "2 - Delete a product \n" +
                    "3 - Print a list of products \n" +
                    "4 - Save in a file\n" +
                    "5 - Load from file\n" +
                    "6 - GUI\n" +
                    "7 - Quit\n" +
                    "Enter your choice (1-7): ");
            // Validate user input
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // Consume the invalid input
            }
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.println("Adding a new product");
                    shoppingManager.addProductToSystem();
                    break;
                case 2:
                    System.out.println("Deleting a product");
                    shoppingManager.deleteProductFromSystem();
                    break;
                case 3:
                    System.out.println("Printing the list of products");
                    System.out.println("-------------------------");
                    shoppingManager.printProductList();
                    break;
                case 4:
                    System.out.println("Saving in a file");
                    shoppingManager.saveToFile();
                    break;
                case 5:
                    System.out.println("Load from file\n");
                    shoppingManager.loadDataFromFile();
                    break;
                case 6:
                    System.out.println("GUI");
                    ShopGUI shopGUI = new ShopGUI();
                    shopGUI.AddTableRows();
                    break;
                case 7:
                    System.out.println("Quitting");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (userChoice != 7);

        scanner.close();
    }





}

