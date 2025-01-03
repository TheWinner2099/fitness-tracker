import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.*;
import java.util.Scanner;
import java.text.ParseException;

/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Menu {
    
    private static WorkoutsByWeek wbw;
    private static Scanner scanner;
     public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        wbw =  new WorkoutsByWeek();

        while (true) {
            System.out.println("\nWorkout Menu:");
            System.out.println("1. Create a new workout");
            System.out.println("2. Search workouts by date");
            System.out.println("3. Display workouts stats");
            System.out.println("4. Exit and save workouts");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // scan newline
            
            switch (choice) {
                case 1:
                    Workout workout = addWorkout();
                    wbw.addWorkout(workout);
                    break;
                case 2:
                    displayWorkouts();
                    break;
                case 3:
                    Date week = getValidDate();
                    displayStats(WorkoutUtils.stringFromDate(week));
                    break;
                case 4:
                    wbw.saveWorkouts();
                    System.out.println("Saved Updates... Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    public static void displayStats(String week) throws ParseException {
        
        // get the week key
        String monStr = wbw.getMondayOfWeek(week);
        
        // get workouts
        List<Workout> weekWorkouts = wbw.getWorkoutByWeek(monStr);
        
        // accumulator variables
        int cardioMins = 0, strengthUpperMins = 0, strengthLowerMins = 0;
        // workout count
        int cardio = 0, strengthUpper = 0, strengthLower = 0;
        
        // in case there are no workouts for that week
        if(weekWorkouts != null)  {
            
            for(Workout wo: weekWorkouts) {
                
                switch(wo.getWorkoutType()) {
                    case WorkoutType.CARDIO:
                        cardio++;
                        cardioMins += wo.getMins();
                        break;
                        
                    case WorkoutType.STRENGTH_TRAINING_UPPER:
                        strengthUpper++;
                        strengthUpperMins += wo.getMins();
                        break;
                        
                    case WorkoutType.STRENGHT_TRAINING_LOWER:
                        strengthLower++;
                        strengthLowerMins += wo.getMins();
                        break;
                    
                }
            }
        
        }
        
        // display results
        System.out.println("Week starting on " + monStr);
        System.out.println("----------------------------");
        System.out.println(String.format("Total Cardio\n\t\tWorkouts:%d\n\t\tMinutes:%d",cardio,cardioMins));
        System.out.println(String.format("Total Upper Body\n\t\tWorkouts:%d\n\t\tMinutes:%d",strengthUpper,strengthUpperMins));
        System.out.println(String.format("Total Lower Body\n\t\tWorkouts:%d\n\t\tMinutes:%d",strengthLower,strengthLowerMins));
        
        
    }
    
    
    public static Workout addWorkout() {
        // implementation to add a workout here
        return null;
    }
    
    public static void displayWorkouts() {
        // implementation to display workotus 1 week at a time
    }
    
    public static Date getValidDate() {
        Date date = null;
        scanner = new Scanner(System.in);
        while( date == null) {
            
            try{
                System.out.println("Date (mm-dd-yyyy):");
                
                date = WorkoutUtils.dateFromString(scanner.nextLine());
            }catch(ParseException exc) {
                System.out.println("Date was not properly formatted...try again.");
            }
        }
        return date;
    }
}
