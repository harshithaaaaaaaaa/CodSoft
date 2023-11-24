import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int availableSlots;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.availableSlots = capacity;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean registerStudent() {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        }
        return false;
    }

    public void removeStudent() {
        if (availableSlots < capacity) {
            availableSlots++;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nAvailable Slots: " + availableSlots + "\nSchedule: " + schedule + "\n";
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (course.registerStudent()) {
            registeredCourses.add(course);
            System.out.println("Registration successful for course: " + course.getTitle());
        } else {
            System.out.println("Course is full. Registration failed for course: " + course.getTitle());
        }
    }

    public void removeCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println("Course removed successfully: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course: " + course.getTitle());
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + " (ID: " + studentID + "):");
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course.getTitle());
            }
        }
    }
}

public class CourseRegistrationSystem {
    private List<Course> courseDatabase;
    private List<Student> studentDatabase;

    public CourseRegistrationSystem() {
        courseDatabase = new ArrayList<>();
        studentDatabase = new ArrayList<>();
        initializeCourses();
        startSystem();
    }

    private void initializeCourses() {
        courseDatabase.add(new Course("CSE101", "Introduction to Computer Science", "Fundamentals of programming", 50, "Mon/Wed 10:00 AM - 11:30 AM"));
        courseDatabase.add(new Course("MAT201", "Calculus I", "Introduction to calculus", 40, "Tue/Thu 1:00 PM - 2:30 PM"));
        courseDatabase.add(new Course("ENG102", "English Composition", "Writing and communication skills", 30, "Mon/Wed/Fri 9:00 AM - 10:30 AM"));
    }

    private void startSystem() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register for a Course");
            System.out.println("3. Remove a Registered Course");
            System.out.println("4. Display Registered Courses for a Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourseListing();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    removeRegisteredCourse();
                    break;
                case 4:
                    displayRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Thank you for using the Course Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void displayCourseListing() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase) {
            System.out.println(course);
        }
    }

    private void registerForCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();
        Student student = findStudent(studentID);

        if (student != null) {
            displayCourseListing();
            System.out.print("Enter course code to register: ");
            String courseCode = scanner.next();
            Course course = findCourse(courseCode);

            if (course != null) {
                student.registerForCourse(course);
            } else {
                System.out.println("Course not found with code: " + courseCode);
            }
        } else {
            System.out.println("Student not found with ID: " + studentID);
        }
    }

    private void removeRegisteredCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();
        Student student = findStudent(studentID);

        if (student != null) {
            student.displayRegisteredCourses();
            System.out.print("Enter course code to remove: ");
            String courseCode = scanner.next();
            Course course = findCourse(courseCode);

            if (course != null) {
                student.removeCourse(course);
            } else {
                System.out.println("Course not found with code: " + courseCode);
            }
        } else {
            System.out.println("Student not found with ID: " + studentID);
        }
    }

    private void displayRegisteredCourses() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentID = scanner.next();
        Student student = findStudent(studentID);

        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student not found with ID: " + studentID);
        }
    }

    private Course findCourse(String courseCode) {
        for (Course course : courseDatabase) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private Student findStudent(String studentID) {
        for (Student student : studentDatabase) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();
    }
}

