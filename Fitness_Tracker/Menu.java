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
        scanner = new Scanner(System.in);
        wbw =  new WorkoutsByWeek();

        while (true) {
            System.out.println("\nWorkout Menu:");
            System.out.println("1. Create a new workout");
            System.out.println("2. Search workouts by week");
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
        
        displayTitle("Display Week Statistics");
        
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
        
        displayTitle("Add New Workout");
        
        // get the type
            int choice = -1;
        
        
        while( choice < 1 || choice > 3){
            System.out.println("Input 1 for CARDIO, 2 for STRENGTH_UPPER, 3 for STRENGTH_LOWER:");
    
            if(scanner.hasNextInt()) {
                  choice = scanner.nextInt();  
            }else{
                System.out.println("Not a number");
                scanner.nextLine();
            }
        }
        
        WorkoutType workoutType = null;
        
        switch(choice) {
            case 1:
                workoutType = WorkoutType.CARDIO;
                break;
            case 2:
                workoutType = WorkoutType.STRENGTH_TRAINING_UPPER;
                break;
            case 3:
                workoutType = WorkoutType.STRENGHT_TRAINING_LOWER;
                break;
        }
        
        int mins = -1;
        
        while(mins < 0) {
            System.out.println("Workout Minutes:");
            if(scanner.hasNextInt()) {
                mins = scanner.nextInt();
            }    
        }
        
        // clear the buffer
        scanner.nextLine();
        
        Date workoutDate = getValidDate();
        
        Workout workout = new Workout(workoutType,mins,workoutDate);
        
        return workout;
    }
    
    public static void displayWorkouts() throws ParseException {
        // loop through workouts
 
        displayTitle("Display Workouts by Week");
        List<String> weeks = wbw.getWeeks();
        
        String response = "";
        
        for(String week : weeks ) {
            
            displayTitle("Week starting on " + week);
            for(Workout workout: wbw.getWorkoutByWeek(week)){
                System.out.println(workout);
            }
            System.out.println("-----------------------------");
            System.out.println("Input any key to view next week or \'x\' to return to menu");
            response = scanner.next();
            if(response.contains("x"))
                break;
        }
        
        
    }
    
    public static Date getValidDate() {
        Date date = null;
        
        while( date == null) {
            
            try{
                System.out.println("Date (mm-dd-yyyy) or blank for today:");
                
                String dateStr = scanner.nextLine();
                
                // check if default 
                if(dateStr.isBlank()){
                    return new Date();
                }
                date = WorkoutUtils.dateFromString(dateStr);
            }catch(ParseException exc) {
                System.out.println("Date was not properly formatted...try again.");
            }
        }
        return date;
    }
    
    public static void displayTitle(String title) {
        System.out.println("----------------------------");
        System.out.println(title);
        System.out.println("----------------------------");
        
    }
}
