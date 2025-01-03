import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Collections;
import java.util.List;

/**
 * WorkoutUtils
 * A set of static utility methods
 * for this application used for reading workouts from a csv file,
 * converting a Workout to a csv line, and writing a workout array
 * to a csv file. 
 *
 * @author cee-vance
 * @version 1.0.0
 */
public class WorkoutUtils
{
    // instance variables - replace the example below with your own
    private static final String workoutPath = "workouts.txt";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
    
    /**
     * Method getWorkouts
     * reads workouts from a csv file
     *
     * @return an arraylist of workouts
     */
    public static List<Workout> getWorkouts()  throws java.text.ParseException {
        File workoutFile = new File(workoutPath);
        Scanner scanFile;
        
        // initalize workouts
        List<Workout> workouts = new ArrayList<>();
        try {
            
        scanFile = new Scanner(workoutFile);
        
        }catch(FileNotFoundException exc) {
            System.out.println("File not found");
            return workouts;
        }
        
        // read the workouts
        while(scanFile.hasNextLine()){
            String line = scanFile.nextLine();
            
            // in case this is the last line
            if(line.isBlank())
                break;
                
            String[] tokens = line.split(",");
            
            Workout workout = new Workout( WorkoutType.valueOf(tokens[0]),
                                            Integer.parseInt(tokens[1]),
                                            formatter.parse(tokens[2]));          
            workouts.add(workout);
            
        }
        
        scanFile.close();
        return workouts;
        
    }
    
    /**
     * Method saveWorkouts
     * writes and arraylist of Workouts to a csv file
     * NOTE: overwrites previous contents of workout file
     * @param workouts the arraylist to save
     */
    public static void saveWorkouts(List<Workout> workouts) {
        
        // sort the workouts by date descending
        Collections.sort(workouts);
        
        File workoutFile = new File(workoutPath);
        
        PrintWriter writer;
        
        try {
           writer = new PrintWriter(workoutFile);
        } catch(FileNotFoundException exc) {
            System.out.println("File not found");
            return;
        }
        
        for(Workout workout: workouts) {
            
            writer.println(workoutToCsvLine(workout));
        }
        
        writer.close();
        
    }
    
    /**
     * Method workoutToCsvLine
     * converts a workout to its csv representation:
     * WorkoutType,Workout Duration,Workout Date (month-date-year)
     * 
     * @param workout the workout to convert to csv line
     * @return The return the string representation
     */
    public static String workoutToCsvLine(Workout workout){
       return String.format("%s,%d,%s",workout.getWorkoutType(),
                           workout.getMins(),
                           formatter.format(workout.getWorkoutDate()));
   }
   
   /**
    * Method dateFromString
    * converts a string in format month-date-year to a Date object
    * @param date datestring
    * @return The return a date object
    * @throws ParseException if dateString is not in 'MM-dd-yyyy' format
    */
   public static Date dateFromString(String date)  throws java.text.ParseException {
       
        return formatter.parse(date);
   }
    
}
