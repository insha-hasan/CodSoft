import java.util.Scanner;

public class StudentManagementSystemApp {
    public static void main(String[] args) {
        final int MAX_STUDENTS = 100;
        StudentManagementSystem system = new StudentManagementSystem(MAX_STUDENTS);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Data to File");
            System.out.println("6. Load Data from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Name and Grade are required fields. Please try again.");
                    } else {
                        Student newStudent = new Student(name, grade);
                        if (system.addStudent(newStudent)) {
                            System.out.println("Student added successfully.");
                        } else {
                            System.out.println("Maximum number of students reached. Cannot add more students.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Roll Number of Student to Remove: ");
                    int removeRollNumber = scanner.nextInt();
                    if (system.removeStudent(removeRollNumber)) {
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found or maximum number of students reached.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Roll Number of Student to Search: ");
                    int searchRollNumber = scanner.nextInt();
                    Student foundStudent = system.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student Found:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    Student[] allStudents = system.getAllStudents();
                    int currentSize = system.getCurrentSize();
                    if (currentSize == 0) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("All Students:");
                        for (int i = 0; i < currentSize; i++) {
                            System.out.println(allStudents[i]);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter the file name to save data: ");
                    String saveFileName = scanner.nextLine();
                    system.saveDataToFile(saveFileName);
                    System.out.println("Data saved to file successfully.");
                    break;
                case 6:
                    System.out.print("Enter the file name to load data: ");
                    String loadFileName = scanner.nextLine();
                    system.loadDataFromFile(loadFileName);
                    System.out.println("Data loaded from file successfully.");
                    break;
                case 7:
                    System.out.println("Exiting Student Management System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
