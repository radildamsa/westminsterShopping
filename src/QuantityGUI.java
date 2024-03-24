import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuantityGUI extends JFrame {
    private JTextField quantityTextField;
    static int number;

    public QuantityGUI() {
        // Create components
        JLabel label = new JLabel("Enter the quantity:");
        quantityTextField = new JTextField(10);
        JButton submitButton = new JButton("Add");

        // Set layout manager
        setLayout(new FlowLayout());

        // Add components to the frame
        add(label);
        add(quantityTextField);
        add(submitButton);




        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the button click
                try {
                    // Get the quantity entered by the user
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    number = Integer.parseInt(quantityTextField.getText());

                    // Display the quantity
                    JOptionPane.showMessageDialog(QuantityGUI.this,
                            "Entered Quantity: " + quantity, "Quantity Entered", JOptionPane.INFORMATION_MESSAGE);

                    // Close the frame
                    dispose();
                } catch (NumberFormatException ex) {
                    // Handle the case when the input is not a valid integer
                    JOptionPane.showMessageDialog(QuantityGUI.this,
                            "Please enter a valid integer for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        setTitle("Quantity Input");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to get the entered quantity
    public int getEnteredQuantity() {
        try {
            return number;
        } catch (NumberFormatException e) {
            // Handle the case when the input is not a valid integer
            return -1;
        }
    }

}
