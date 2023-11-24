import java.util.Scanner;

public class GradeCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the marks obtained in each subject out of 100:");

        System.out.print("Math: ");
        int math = scanner.nextInt();

        System.out.print("Science: ");
        int science = scanner.nextInt();

        System.out.print("Social: ");
        int social = scanner.nextInt();

        System.out.print("English: ");
        int english = scanner.nextInt();

        int totalMarks = math + science + social + english;
        double averagePercentage = (totalMarks / 400.0) * 100;
        String grade = getGrade(averagePercentage);

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }

    private static String getGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}