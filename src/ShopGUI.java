import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class ShopGUI implements ActionListener {
    JFrame frame;
    JTable table;
    JLabel label;
    JButton shoppingCart;
    JButton addToCart;
    JComboBox<String> productCategory1;
    DefaultTableModel model;
    DefaultListModel<String> productInfo = new DefaultListModel<>();
    JScrollPane scrollPane;
    String selectedType;
    JList<String> list;
    int selectedRow;
    public static List<ShoppingCart> ShoppingCartProducts = new ArrayList<>();

    public ShopGUI() {
        frame = new JFrame("Westminster Shopping Manager");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shoppingCart = new JButton("Shopping Cart");
        shoppingCart.addActionListener(this);
        shoppingCart.setBounds(450, 10, 200, 50);
        frame.add(shoppingCart);

        label = new JLabel("Select product category");
        label.setBounds(50, 70, 250, 50);
        frame.add(label);

        productCategory1 = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        productCategory1.addActionListener(this);
        productCategory1.setBounds(250, 70, 200, 50);
        selectedType = productCategory1.getSelectedItem().toString();
        frame.add(productCategory1);

        model = new DefaultTableModel(new String[]{"Product_id", "Name", "Available items", "Type", "Price", "Info"}, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 130, 700, 240);
        frame.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateList();
                }
            }
        });

        list = new JList<>(productInfo);
        list.setBounds(70, 380, 700, 252);
        frame.add(list);

        addToCart = new JButton("Add to cart");
        addToCart.setBounds(250, 640, 150, 50);
        addToCart.addActionListener(this);
        frame.add(addToCart);

        frame.setSize(800, 850);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void AddTableRows () {
        model.setRowCount(0);
        for (Product product : WestminsterShoppingManager.productList) {
            model.addRow(new Object[]{
                    product.getProductID(),
                    product.getProductName(),
                    product.getProductCount(),
                    product.getProductCategory(),
                    product.getProductPrice(),
                    product.toString()
            });
            // Highlight rows with product count <= 3
            if (product.getProductCount() <= 3) {
                int rowIndex = model.getRowCount() - 1; // Index of the last added row
                table.setDefaultRenderer(Object.class, new HighlightRenderer(rowIndex));
            }
        }
    }

    // Custom cell renderer to set the background color based on a condition
    private class HighlightRenderer extends DefaultTableCellRenderer {
        private final int rowIndex;

        HighlightRenderer(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Check if the current row should be highlighted
            if (row == rowIndex) {
                component.setBackground(Color.RED);
            } else {
                component.setBackground(table.getBackground());
            }

            return component;
        }
    }
    private void updateList() {
        productInfo.clear(); // Clear the existing items in the list

        selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                String columnName = table.getColumnName(i);
                Object value = table.getValueAt(selectedRow, i);
                productInfo.addElement(columnName + ": " + value);
            }
        }
        list.setModel(productInfo); // Update the JList
    }

    private void filterTable(String selectedType) {
        AddTableRows();
        if (!selectedType.equals("All")){
            for (int j = model.getRowCount() - 1; j >= 0; j--){
                if(!model.getValueAt(j, 3).equals(selectedType)){
                    model.removeRow(j);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == shoppingCart) {
            frame.dispose();
            shoppingCartGUI shoppingCart = new shoppingCartGUI();
            shoppingCart.AddTableRows1();
        } else if (e.getSource() == productCategory1) {
            String type = (String) productCategory1.getSelectedItem();
            filterTable(type);
        }else if (e.getSource() == addToCart) {
            QuantityGUI getQuantity = new QuantityGUI();

            JDialog dialog = new JDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            dialog.getContentPane().add(getQuantity.getContentPane());

            dialog.pack();
            dialog.setModal(true);
            dialog.setVisible(true);

            // Retrieve productName and productPrice from the selected row
            String productName = (String) table.getValueAt(selectedRow, 1);
            int productPrice = (int) table.getValueAt(selectedRow, 4);

            int enteredQuantity = getQuantity.getEnteredQuantity();
            if (enteredQuantity != -1) {
                ShoppingCart newShoppingCart = new ShoppingCart(productName, enteredQuantity, productPrice*enteredQuantity);
                ShoppingCartProducts.add(newShoppingCart);
            }
        }


    }


}
