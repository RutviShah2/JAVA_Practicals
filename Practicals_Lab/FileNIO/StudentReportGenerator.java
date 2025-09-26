package FileNIO;

import java.io.*;
import java.util.*;

class Student {
    String name;
    List<Integer> marks;
    double average;
    char grade;

    Student(String name, List<Integer> marks) {
        this.name = name;
        this.marks = marks;
        calculateAverage();
        assignGrade();
    }

    private void calculateAverage() {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        this.average = (double) sum / marks.size();
    }

    private void assignGrade() {
        if (average >= 85) grade = 'A';
        else if (average >= 70) grade = 'B';
        else if (average >= 55) grade = 'C';
        else if (average >= 40) grade = 'D';
        else grade = 'F';
    }

    public void printReport() {
        System.out.println("Report for: " + name);
        System.out.println("Marks: " + marks);
        System.out.printf("Average: %.2f\n", average);
        System.out.println("Grade: " + grade);

    }
}

public class StudentReportGenerator {
    public static void main(String[] args) {
        String filename = "students.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String name = parts[0];
                List<Integer> marks = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    marks.add(Integer.parseInt(parts[i]));
                }
                Student s = new Student(name, marks);
                s.printReport();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
