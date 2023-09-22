import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class StudentManagementSystemGUI extends JFrame {
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea outputArea;
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField();
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);
        inputPanel.add(addButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
        JButton saveButton = new JButton("Save Data to File");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });
        JButton loadButton = new JButton("Load Data from File");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDataFromFile();
            }
        });
        buttonPanel.add(displayButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String grade = gradeField.getText().trim();
        int rollNumber = 0;

        try {
            rollNumber = Integer.parseInt(rollNumberField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Roll Number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Grade are required fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        outputArea.append("Student added: " + student + "\n");
        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    private void displayStudents() {
        outputArea.setText("");
        if (students.isEmpty()) {
            outputArea.append("No students found.");
        } else {
            for (Student student : students) {
                outputArea.append(student.toString() + "\n");
            }
        }
    }

    private void saveDataToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()))) {
                oos.writeObject(students);
                outputArea.append("Data saved to file: " + fileChooser.getSelectedFile().getName() + "\n");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving data to file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadDataFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))) {
                students = (ArrayList<Student>) ois.readObject();
                outputArea.append("Data loaded from file: " + fileChooser.getSelectedFile().getName() + "\n");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error loading data from file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystemGUI().setVisible(true);
            }
        });
    }
}

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
