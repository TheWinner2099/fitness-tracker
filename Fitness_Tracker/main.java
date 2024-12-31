import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.text.*;

/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main {
    
    static WorkoutsByWeek wbw;
    public static void main(String[] args) throws java.text.ParseException {
                        
    }   
    
    // function to prompt user for info to create a new workout
    
    // ask the user for a date 
    
    // exit -> save workouts
    
    public static void displayStats(String week) throws ParseException {
        
        // get the week key
        String monStr = wbw.getMondayOfWeek(week);
        
        // get workouts
        List<Workout> weekWorkouts = wbw.getWorkoutByWeek(monStr);
        
        // accumulator variables
        int cardioMins = 0, strengthUpperMins = 0, strengthLowerMins = 0;
        // workout count
        int cardio = 0, strengthUpper = 0, strengthLower = 0;
        
        // in case there are no workers for that week
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
}
