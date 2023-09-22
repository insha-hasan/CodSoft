import java.io.*;
import java.util.Scanner;

class StudentManagementSystem {
    private Student[] students;
    private int maxSize;
    private int currentSize;

    public StudentManagementSystem(int maxSize) {
        this.maxSize = maxSize;
        students = new Student[maxSize];
        currentSize = 0;
    }

    public boolean addStudent(Student student) {
        if (currentSize < maxSize) {
            students[currentSize++] = student;
            return true;
        }
        return false;
    }

    public boolean removeStudent(int rollNumber) {
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getRollNumber() == rollNumber) {
                // Move the last student to the removed student's position
                students[i] = students[currentSize - 1];
                students[currentSize - 1] = null;
                currentSize--;
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int rollNumber) {
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getRollNumber() == rollNumber) {
                return students[i];
            }
        }
        return null;
    }

    public Student[] getAllStudents() {
        return students;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    // Method to save student data to a file
    public void saveDataToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load student data from a file
    public void loadDataFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (Student[]) ois.readObject();
            currentSize = students.length;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
