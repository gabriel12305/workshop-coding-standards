package lab;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class Student {
    private static final Logger logger = Logger.getLogger(Student.class.getName());

    String id;
    String name;
    List<Double> grades;
    String pass = "unknown";
    boolean honor;

    public Student(String i, String n) {
        id = i;
        name = n;
        grades = new ArrayList<>();
    }

    public void addG(Double grade) {
        if (grade == null || grade < 0 || grade > 100) {
            logger.warning("Error: Grade must be between 0 and 100.");
            return;
        }
        grades.add(grade);
    }

    public double average() {
        if(grades.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (Double g : grades) {
            total += g;
        }
        return total / grades.size();
    }

    public void updateStatus() {
        double currentAverage = average();
        honor = currentAverage >= 90;

        if (currentAverage >= 60) {
            pass = "Passed";
        } else {
            pass = "Failed";
        }
    }

    public String getLetterGrade() {
        double currentAverage = average();
        if (currentAverage >= 90) {
            return "A";
        } else if (currentAverage >= 80) {
            return "B";
        } else if (currentAverage >= 70) {
            return "C";
        } else if (currentAverage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public void removeGradeByIndex(int index) {
        if (index >= 0 && index < grades.size()) {
            grades.remove(index);
        } else {
            logger.warning("Error: Index out of bounds.");
        }
    }

    public void removeGradeByValue(Double value) {
        if (grades.contains(value)) {
            grades.remove(value);
        } else {
            logger.warning("Error: Grade not found.");
        }
    }

    public void reportCard() {
        updateStatus();
        
        logger.log(java.util.logging.Level.INFO, "Student: {0}", name);
        logger.log(java.util.logging.Level.INFO, "ID: {0}", id);
        logger.log(java.util.logging.Level.INFO, "Grades #: {0}", grades.size());
        logger.log(java.util.logging.Level.INFO, "Average: {0}", average());
        logger.log(java.util.logging.Level.INFO, "Letter Grade: {0}", getLetterGrade());
        logger.log(java.util.logging.Level.INFO, "Pass/Fail Status: {0}", pass);
        logger.log(java.util.logging.Level.INFO, "Honor Roll: {0}", honor);
    
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student("0987654321", "Juan Perez");
        s.addG(100.0);
        s.addG(85.0);
        s.addG(95.0);

        s.addG(-15.0);
        s.removeGradeByIndex(10);

        s.reportCard();
    }
}
