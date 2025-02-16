import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity, enrolled;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    boolean hasSlot() {
        return enrolled < capacity;
    }

    void enrollStudent() {
        if (hasSlot())
            enrolled++;
    }

    void dropStudent() {
        if (enrolled > 0)
            enrolled--;
    }

    @Override
    public String toString() {
        return code + " - " + title + " (Capacity: " + enrolled + "/" + capacity + ")\nSchedule: " + schedule;
    }
}

class Student {
    String id, name;
    List<Course> registeredCourses;

    Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (course.hasSlot()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println(name + " successfully registered for " + course.title);
        } else {
            System.out.println("Course is full!");
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println(name + " dropped " + course.title);
        } else {
            System.out.println("Not registered in this course!");
        }
    }

    void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            registeredCourses.forEach(System.out::println);
        }
    }
}

public class CourseRegistrationSystem {
    static Map<String, Course> courses = new HashMap<>();
    static Map<String, Student> students = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData(); // Preload courses
        while (true) {
            System.out.println(
                    "\n1. List Courses\n2. Register Student\n3. Register for Course\n4. Drop Course\n5. Show Student Courses\n6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> listCourses();
                case 2 -> registerStudent();
                case 3 -> registerForCourse();
                case 4 -> dropCourse();
                case 5 -> showStudentCourses();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void seedData() {
        courses.put("CS101", new Course("CS101", "Intro to CS", "Basic programming", 3, "Mon-Wed 10 AM"));
        courses.put("MA102", new Course("MA102", "Calculus", "Mathematical concepts", 2, "Tue-Thu 2 PM"));
    }

    static void listCourses() {
        System.out.println("Available Courses:");
        courses.values().forEach(System.out::println);
    }

    static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        students.put(id, new Student(id, name));
        System.out.println("Student Registered Successfully!");
    }

    static void registerForCourse() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Student student = students.get(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found!");
        } else {
            student.registerCourse(course);
        }
    }

    static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Student student = students.get(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found!");
        } else {
            student.dropCourse(course);
        }
    }

    static void showStudentCourses() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        Student student = students.get(id);
        if (student != null) {
            student.listRegisteredCourses();
        } else {
            System.out.println("Student not found!");
        }
    }
}
