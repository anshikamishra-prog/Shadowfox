import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Student class to represent a student
class Student {
    private String name;
    private int age;
    private String email;

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Email: " + email;
    }
}

// StudentManager class to manage student records
class StudentManager {
    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void deleteStudent(String name) {
        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
    }
}

// Main class for the Student Information System GUI
public class StudentInformationSystem extends JFrame {
    private StudentManager studentManager;
    private JTextArea textArea;
    private JTextField nameField, ageField, emailField;

    public StudentInformationSystem() {
        studentManager = new StudentManager();
        setTitle("Student Information System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        JButton addButton = new JButton("Add Student");
        JButton deleteButton = new JButton("Delete Student");
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        // Text Area for displaying students
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();
                Student student = new Student(name, age, email);
                studentManager.addStudent(student);
                updateTextArea();
                clearFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                studentManager.deleteStudent(name);
                updateTextArea();
                clearFields();
            }
        });
    }

    private void updateTextArea() {
        textArea.setText("");
        for (Student student : studentManager.getStudents()) {
            textArea.append(student.toString() + "\n");
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentInformationSystem frame = new StudentInformationSystem();
            frame.setVisible(true);
        });
    }
}
