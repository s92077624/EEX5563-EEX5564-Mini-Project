package test2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MemoryAllocationUI {

    protected static List<Integer> memorySlots = new ArrayList<>(Arrays.asList(100, 500, 200, 300, 600));
    protected static List<Integer> processSizes = new ArrayList<>();
    protected static List<String> allocationMap = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MemoryAllocationUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Dynamic Memory Allocation Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setForeground(new Color(57, 255, 20)); // Neon Green
        frame.setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0)); // Black

        JLabel titleLabel = new JLabel("Dynamic Memory Allocation Simulator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(57, 255, 20)); // Neon Green
        titleLabel.setBounds(100, 20, 500, 30);
        panel.add(titleLabel);

        JLabel regLabel = new JLabel("Enter Registration Number:");
        regLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        regLabel.setForeground(new Color(57, 255, 20)); // Neon Green
        regLabel.setBounds(150, 60, 200, 30);
        panel.add(regLabel);

        JTextField regInput = new JTextField();
        regInput.setBounds(350, 60, 100, 30);
        panel.add(regInput);

        JLabel memoryLabel = new JLabel("Available Memory Locations: " + memorySlots.toString());
        memoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        memoryLabel.setForeground(new Color(57, 255, 20));
        memoryLabel.setBounds(150, 100, 400, 30);
        panel.add(memoryLabel);

        JLabel processLabel = new JLabel("Process Sizes: " + processSizes.toString());
        processLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        processLabel.setForeground(new Color(57, 255, 20));
        processLabel.setBounds(150, 140, 400, 30);
        panel.add(processLabel);

        JTextField processInput = new JTextField();
        processInput.setBounds(150, 180, 100, 30);
        panel.add(processInput);

        JButton addProcessButton = new JButton("Add Process Size");
        addProcessButton.setBounds(270, 180, 150, 30);
        addProcessButton.setBackground(new Color(0, 0, 0));
        addProcessButton.setForeground(new Color(57, 255, 20));
        addProcessButton.addActionListener(e -> {
            try {
                int size = Integer.parseInt(processInput.getText());
                if (processSizes.size() >= 4) {
                    JOptionPane.showMessageDialog(frame, "You can only add up to 4 process sizes.", "Error", JOptionPane.ERROR_MESSAGE);

                
                //Highlighed Input blocking part due to run some test cases
                
                
                } else if (size < 1 || size > 500) {
                    JOptionPane.showMessageDialog(frame, "Process size must be between 1 and 500.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                 else if (size < 1) {  // Check if the size is less than 1 (i.e., negative or zero)
                    JOptionPane.showMessageDialog(frame, "Process sizes must be positive values.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                
                
                else {
                    processSizes.add(size);
                    processLabel.setText("Process Sizes: " + processSizes.toString());
                    processInput.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(addProcessButton);

        JButton removeProcessButton = new JButton("Remove Last Size");
        removeProcessButton.setBounds(450, 180, 150, 30);
        removeProcessButton.setBackground(new Color(0, 0, 0));
        removeProcessButton.setForeground(new Color(57, 255, 20));
        removeProcessButton.addActionListener(e -> {
            if (!processSizes.isEmpty()) {
                processSizes.remove(processSizes.size() - 1);
                processLabel.setText("Process Sizes: " + processSizes.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "No process sizes to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(removeProcessButton);

        JButton simulateButton = new JButton("Simulate Allocation");
        simulateButton.setBounds(100, 300, 200, 40);
        simulateButton.setBackground(new Color(0, 0, 0));
        simulateButton.setForeground(new Color(255, 19, 240)); // Neon Pink
        simulateButton.addActionListener(e -> {
            if (processSizes.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please add process sizes before simulating.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String regNumber = regInput.getText();
            if (!regNumber.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(frame, "Registration number must be exactly 9 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int remainder = Integer.parseInt(regNumber) % 6;
                    allocationMap.clear();
                    switch (remainder) {
                        case 0:
                            JOptionPane.showMessageDialog(frame, "Selected Algorithm: First Fit");
                            FirstFit.simulateFirstFit();
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(frame, "Selected Algorithm: Next Fit");
                            NextFit.simulateNextFit();
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(frame, "Selected Algorithm: Best Fit");
                            BestFit.simulateBestFit();
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(frame, "Selected Algorithm: Worst Fit");
                            WorstFit.simulateWorstFit();
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(frame, "Selected Algorithm: Buddy System");
                            BuddySystem.simulateBuddySystem();
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(frame, "Selected Algorithm: Quick Fit");
                            QuickFit.simulateQuickFit();
                            break;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid registration number. Please enter numeric values only.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(simulateButton);

        JButton showButton = new JButton("Show Allocation");
        showButton.setBounds(350, 300, 200, 40);
        showButton.setBackground(new Color(0, 0, 0)); // Black
        showButton.setForeground(new Color(255, 19, 240)); // Neon Pink
        showButton.addActionListener(e -> displayAllocation());
        panel.add(showButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void displayAllocation() {
        StringBuilder result = new StringBuilder("<html><h3 style='color: #39FF14;'>Allocation Results:</h3><ul>");
        for (String allocation : allocationMap) {
            result.append("<li style='color: #39FF14;'>").append(allocation).append("</li>");
        }
        result.append("</ul></html>");

        JLabel resultLabel = new JLabel(result.toString());
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setForeground(new Color(57, 255, 20));

        JFrame resultFrame = new JFrame("Allocation Results");
        resultFrame.setSize(400, 300);
        resultLabel.setOpaque(true);
        resultLabel.setBackground(Color.BLACK);
        resultFrame.add(new JScrollPane(resultLabel));
        resultFrame.setVisible(true);
    }
}
