import java.io.Serializable;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private static int nextRollNumber = 1; // Static variable to automatically assign roll numbers

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
        this.rollNumber = nextRollNumber++;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
