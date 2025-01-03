import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Menu for Workout Tracker
 *
 * @author Jacob
 * @version 1.0.0
 */

public class WorkoutMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WorkoutTracker tracker = new WorkoutTracker();

        while (true) {
            System.out.println("\nWorkout Menu:");
            System.out.println("1. Create a new workout");
            System.out.println("2. Search workouts by date");
            System.out.println("3. Exit and save workouts");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // scan newline

            switch (choice) {
                case 1:
                    System.out.print("Enter workout name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter workout date (YYYYMMDD): ");
                    String date = scanner.nextLine();
                    tracker.addWorkout(name, date);
                    break;
                case 2:
                    System.out.print("Enter date to search (YYYYMMDD): ");
                    String searchDate = scanner.nextLine();
                    tracker.listWorkoutsByDate(searchDate);
                    break;
                case 3:
                    tracker.saveWorkouts();
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Workout {
    private String name;
    private String date;

    public Workout(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Workout: " + name + ", Date: " + date;
    }
}

class WorkoutTracker {
    private ArrayList<Workout> workouts;

    public WorkoutTracker() {
        this.workouts = new ArrayList<>();
    }

    public void addWorkout(String name, String date) {
        workouts.add(new Workout(name, date));
        System.out.println("Workout added successfully!");
    }

    public void listWorkoutsByDate(String date) {
        System.out.println("Workouts on " + date + ":");
        boolean found = false;
        for (Workout workout : workouts) {
            if (workout.getDate().equals(date)) {
                System.out.println(workout);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No workouts found for this date.");
        }
    }

    public void saveWorkouts() {
        System.out.println("Saving workouts...");
        // Temp placeholder for saving logic (saving it to a file or something)
        System.out.println("Workouts saved successfully!");
    }
}
