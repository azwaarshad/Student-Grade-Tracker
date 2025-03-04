import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static JFrame frame;
    private static PayrollSystem ps;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Payroll System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ps = new PayrollSystem();

        JPanel panel = new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JButton startButton = new JButton("Start");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(startButton, constraints);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmployeeInput();
            }
        });

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setVisible(true);
    }

    private static void showEmployeeInput() {
        JPanel inputPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        String employeeID = JOptionPane.showInputDialog("Enter Employee ID:");
        String employeeName = JOptionPane.showInputDialog("Enter Employee Name:");
        String[] employeeTypes = {"Hourly", "Salaried"};
        String employeeType = (String) JOptionPane.showInputDialog(frame,
                "Select Employee Type:",
                "Employee Type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                employeeTypes,
                employeeTypes[0]);

        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPanel.add(new JLabel("Employee ID:"), constraints);
        constraints.gridx = 1;
        inputPanel.add(new JLabel(employeeID), constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(new JLabel("Employee Name:"), constraints);
        constraints.gridx = 1;
        inputPanel.add(new JLabel(employeeName), constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        inputPanel.add(new JLabel("Employee Type:"), constraints);
        constraints.gridx = 1;
        inputPanel.add(new JLabel(employeeType), constraints);

        if ("Hourly".equals(employeeType)) {
            double hourlyRate = getDoubleInput("Enter Hourly Rate:");
            int hoursWorked = getIntegerInput("Enter Hours Worked:");

            HourlyEmployee hEmployee = new HourlyEmployee(employeeID, employeeName, hourlyRate, hoursWorked);
            ps.addEmployee(hEmployee);
        } else if ("Salaried".equals(employeeType)) {
            double salary = getDoubleInput("Enter Salary:");

            SalariedEmployee sEmployee = new SalariedEmployee(employeeID, employeeName, salary);
            ps.addEmployee(sEmployee);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
            return;
        }

        int option = JOptionPane.showConfirmDialog(frame, "Do you want to enter information for another employee?", "Continue?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            showEmployeeInput();
        } else {
            showTotalPayroll();
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            try {
                return Double.parseDouble(JOptionPane.showInputDialog(message));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid number.");
            }
        }
    }

    private static int getIntegerInput(String message) {
        while (true) {
            try {
                return Integer.parseInt(JOptionPane.showInputDialog(message));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid integer.");
            }
        }
    }

    private static void showTotalPayroll() {
        JOptionPane.showMessageDialog(frame, "Total Payroll: $" + ps.CalculatePayroll());
        frame.dispose();
    }
}