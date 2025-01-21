import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a Course
class Course {
    String courseCode;
    String courseTitle;
    String description;
    int capacity;
    int enrolledStudents;

    // Constructor to initialize course details
    public Course(String courseCode, String courseTitle, String description, int capacity) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;  // initially no students are enrolled
    }

    // Check if there is space in the course
    public boolean hasSpace() {
        return enrolledStudents < capacity;
    }

    // Enroll a student to the course
    public void enrollStudent() {
        if (hasSpace()) {
            enrolledStudents++;
        } else {
            System.out.println("Course " + courseCode + " is full.");
        }
    }

    // Drop a student from the course
    public void dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        } else {
            System.out.println("No students are enrolled in " + courseCode);
        }
    }

    // Display course information
    public void displayCourseInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + courseTitle);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled: " + enrolledStudents);
        System.out.println("Available Slots: " + (capacity - enrolledStudents));
    }
}

// Class to represent a Student
class Student {
    String studentId;
    String studentName;
    ArrayList<Course> registeredCourses;

    // Constructor to initialize student details
    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registeredCourses = new ArrayList<>();
    }

    // Register for a course
    public void registerForCourse(Course course) {
        if (course.hasSpace() && !registeredCourses.contains(course)) {
            course.enrollStudent();
            registeredCourses.add(course);
            System.out.println(studentName + " successfully registered for " + course.courseCode);
        } else if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for " + course.courseCode);
        } else {
            System.out.println("Course " + course.courseCode + " is full.");
        }
    }

    // Drop a course
    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            course.dropStudent();
            registeredCourses.remove(course);
            System.out.println(studentName + " successfully dropped " + course.courseCode);
        } else {
            System.out.println("You are not registered for " + course.courseCode);
        }
    }

    // Display student details and courses registered
    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        System.out.println("Registered Courses:");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course.courseCode + " - " + course.courseTitle);
            }
        }
    }
}

// Main class to run the system
public class StudentCourseRegistrationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create some sample courses
        ArrayList<Course> courseDatabase = new ArrayList<>();
        courseDatabase.add(new Course("CS101", "Intro to Computer Science", "Basic concepts of Computer Science.", 30));
        courseDatabase.add(new Course("MATH101", "Calculus I", "Introductory course to calculus.", 20));
        courseDatabase.add(new Course("PHY101", "Physics I", "Introductory course to Physics.", 25));

        // Create a sample student
        Student student = new Student("S12345", "John Doe");

        boolean systemRunning = true;

        while (systemRunning) {
            // Show menu options
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Student Information");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Display available courses
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courseDatabase) {
                        course.displayCourseInfo();
                        System.out.println("--------------------------");
                    }
                    break;

                case 2:
                    // Register for a course
                    System.out.println("\nEnter course code to register:");
                    String courseCodeToRegister = scanner.nextLine();
                    Course courseToRegister = findCourse(courseCodeToRegister, courseDatabase);
                    if (courseToRegister != null) {
                        student.registerForCourse(courseToRegister);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 3:
                    // Drop a course
                    System.out.println("\nEnter course code to drop:");
                    String courseCodeToDrop = scanner.nextLine();
                    Course courseToDrop = findCourse(courseCodeToDrop, courseDatabase);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 4:
                    // Display student information
                    student.displayStudentInfo();
                    break;

                case 5:
                    // Exit the system
                    systemRunning = false;
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Utility function to find a course by its code
    private static Course findCourse(String courseCode, ArrayList<Course> courseDatabase) {
        for (Course course : courseDatabase) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null; // Course not found
    }
}
