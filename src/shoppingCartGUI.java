import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class shoppingCartGUI implements ActionListener {
    JFrame frame;
    JTable table;
    JLabel Total;
    JLabel FirstPurchaseDiscount;
    JLabel ThreeItemDiscount;
    JLabel FinalTotal;
    DefaultTableModel model;
    JScrollPane scrollPane;


    public shoppingCartGUI() {
        frame = new JFrame("Westminster Shopping Manager");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"Product", "Quantity", "Price"}, 0);
        table = new JTable(model);
        this.scrollPane = new JScrollPane(table); // Add the JTable to the JScrollPane
        scrollPane.setBounds(70, 130, 700, 240);
        frame.add(scrollPane); // Add the JScrollPane to the frame

        Total = new JLabel("Total");
        Total.setBounds(50, 400, 350, 50);
        frame.add(Total);

        FirstPurchaseDiscount = new JLabel("First Purchase Discount(10%)");
        FirstPurchaseDiscount.setBounds(50, 430, 350, 50);
        frame.add(FirstPurchaseDiscount);

        ThreeItemDiscount = new JLabel("Three items in same Category Discount(20%)");
        ThreeItemDiscount.setBounds(50, 460, 350, 50);
        frame.add(ThreeItemDiscount);

        FinalTotal = new JLabel("Final Total");
        FinalTotal.setBounds(50, 490, 350, 50);
        frame.add(FinalTotal);

        frame.setSize(850, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void AddTableRows1 () {
        model.setRowCount(0);
        for (ShoppingCart product : ShopGUI.ShoppingCartProducts) {
            model.addRow(new Object[]{
                    product.getName(),
                    product.getCount(),
                    "$" + product.getPrice()
            });
        }
        CreateTotal();
        FirstPurchaseDiscount();
        ThreeProductsSameCategoryDiscount();
        FinalTotal();
    }

    public double CreateTotal() {
        int rowCount = table.getRowCount();
        double total = 0.0;

        for (int i = 0; i < rowCount; i++) {
            String priceString = table.getValueAt(i, table.getColumnCount() - 1).toString();

            // Remove the dollar sign and convert the string to a double
            double price = Double.parseDouble(priceString.substring(1));

            total += price;
        }

        Total.setText("Total: $" + total);
        return total;
    }


    private double ThreeProductsSameCategoryDiscount() {
        ArrayList<Integer> categoryCountList = new ArrayList<>();
        int rowCount = table.getRowCount();
        boolean haveThreeProducts = false;

        // Count the number of products in each category and add to the ArrayList
        for (int i = 0; i < rowCount; i++) {
            // Assuming categoryCount is in the second column
            Integer categoryCount = (Integer) table.getValueAt(i, 1);
            categoryCountList.add(categoryCount);
        }

        // Check if any category has at least three products
        for (int i = 0; i < rowCount; i++){
            if (categoryCountList.contains(1) || categoryCountList.contains(2) || categoryCountList.contains(3)) {
                haveThreeProducts = true;
                break;
            }
        }

        // Apply discount if there are at least three products in the same category
        if (haveThreeProducts) {
            String totalText = Total.getText();
            double total = Double.parseDouble(totalText.substring(totalText.indexOf("$") + 1));

            // Calculate the discount
            double discountAmount = 0.20 * total;

            ThreeItemDiscount.setText("Three items in same Category Discount (20%): -$" + String.format("%.2f", discountAmount));
            return discountAmount;
        } else {
            ThreeItemDiscount.setText("Three items in same Category Discount (20%): N/A");
            return 0.0;
        }
    }


    private double FirstPurchaseDiscount() {
        if (User.purchaseCount == 0) {
            // Calculate total
            CreateTotal();

            // Get the total value from the Total label
            String totalText = Total.getText();
            double total = Double.parseDouble(totalText.substring(totalText.indexOf("$") + 1));

            //User.purchaseCount++;
            // Calculate the discount
            double discountAmount = 0.10 * total;
            // Display the discount in the label
            FirstPurchaseDiscount.setText("First Purchase Discount (10%): -$" + String.format("%.2f", discountAmount));

            return discountAmount;
        } else {
            return 0.0;
        }
    }

    public void FinalTotal() {
        CreateTotal();

        // Get the total value from the Total label
        String totalText = Total.getText();
        double total = Double.parseDouble(totalText.substring(totalText.indexOf("$") + 1));

        // Call the discount methods to get the discount amounts
        double threeProductsDiscount = ThreeProductsSameCategoryDiscount();
        double firstPurchaseDiscount = FirstPurchaseDiscount();

        // Subtract the discounts from the total
        double finalTotal = total - threeProductsDiscount - firstPurchaseDiscount;

        // Display the final total
        FinalTotal.setText("Final Total: $" + String.format("%.2f", finalTotal));
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
