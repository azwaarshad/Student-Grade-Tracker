package mypackage;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class StudentGradeTracker {

    private static final Map<String, Double> GRADE_CONVERSIONS = new HashMap<>();
    
    static {
        GRADE_CONVERSIONS.put("A+", 4.0);
        GRADE_CONVERSIONS.put("A", 3.8);
        GRADE_CONVERSIONS.put("A-", 3.6);
        GRADE_CONVERSIONS.put("B+", 3.3);
        GRADE_CONVERSIONS.put("B", 3.0);
        GRADE_CONVERSIONS.put("B-", 2.7);
        GRADE_CONVERSIONS.put("C+", 2.3);
        GRADE_CONVERSIONS.put("C", 2.0);
        GRADE_CONVERSIONS.put("C-", 1.7);
        GRADE_CONVERSIONS.put("D+", 1.3);
        GRADE_CONVERSIONS.put("D", 1.0);
        GRADE_CONVERSIONS.put("F", 0.0);
    }

    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        ArrayList<String> letterGrades = new ArrayList<>();
        ArrayList<Double> numericGrades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the student's name (or type 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            String letterGrade = "";
            while (!GRADE_CONVERSIONS.containsKey(letterGrade)) {
                System.out.print("Enter the letter grade for " + name + " (e.g., A, B+, C): ");
                letterGrade = scanner.nextLine().toUpperCase();
                if (!GRADE_CONVERSIONS.containsKey(letterGrade)) {
                    System.out.println("Invalid grade. Please enter a valid letter grade (e.g., A, B+, C).");
                }
            }
            
            double numericGrade = GRADE_CONVERSIONS.get(letterGrade);
            students.add(name);
            letterGrades.add(letterGrade);
            numericGrades.add(numericGrade);
        }

        if (!numericGrades.isEmpty()) {
            double total = 0;
            double highest = numericGrades.get(0);
            double lowest = numericGrades.get(0);

            for (double g : numericGrades) {
                total += g;
                if (g > highest) {
                    highest = g;
                }
                if (g < lowest) {
                    lowest = g;
                }
            }

            double average = total / numericGrades.size();

            System.out.println("\nGrade Report");
            System.out.println("-------------");
            System.out.println("Number of students: " + students.size());
            System.out.println("Average grade: " + String.format("%.2f", average));
            System.out.println("Highest grade: " + String.format("%.2f", highest));
            System.out.println("Lowest grade: " + String.format("%.2f", lowest));
            System.out.println("\nStudent Grades:");

            for (int i = 0; i < students.size(); i++) {
                System.out.println(students.get(i) + ": " + letterGrades.get(i));
            }
        } else {
            System.out.println("No grades were entered.");
        }

        scanner.close();
    }
}























































































